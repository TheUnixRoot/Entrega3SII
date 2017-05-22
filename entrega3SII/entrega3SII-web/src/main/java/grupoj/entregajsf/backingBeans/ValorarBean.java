/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import grupoj.prentrega1.Valoracion_lug;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "valorarBean")
@RequestScoped
public class ValorarBean {

    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion control;

    private Evento evento;
    private Usuario usu;
    // En las clases ya los tengo
    private Integer valEvento;
    private String comEvento;
    private UploadedFile fotoValEvento;
    private List<Valoracion_eve> listEve;

    private Integer valLugar;
    private String comLugar;
    private UploadedFile fotoValLugar;
    private List<Valoracion_lug> listLug;

    /**
     * Creates a new instance of ValorarBean
     */
    @PostConstruct
    public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        usu = control.getUsuario();
//        Evento ev = new Evento();
//        ev.setId((Long.parseLong(req.get("id"))));
//        if (this.persistencia.getListaEventos().contains(ev)) {
        this.evento = persistencia.getEvento(Long.parseLong(req.get("id")));
//                    this.persistencia.getListaEventos()
//                    .get(
//                            this.persistencia.getListaEventos().indexOf(ev)
//                    );
//        } else {
//            this.evento = null;
//        }
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getValEvento() {
        return valEvento;
    }

    public void setValEvento(Integer valEvento) {
        this.valEvento = valEvento;
    }

    public String getComEvento() {
        return comEvento;
    }

    public void setComEvento(String comEvento) {
        this.comEvento = comEvento;
    }

    public UploadedFile getFotoValEvento() {
        return fotoValEvento;
    }

    public void setFotoValEvento(UploadedFile fotoValEvento) {
        this.fotoValEvento = fotoValEvento;
    }

    public List<Valoracion_eve> getListEve() {
        return listEve;
    }

    public void setListEve(List<Valoracion_eve> listEve) {
        this.listEve = listEve;
    }

    public Integer getValLugar() {
        return valLugar;
    }

    public void setValLugar(Integer valLugar) {
        this.valLugar = valLugar;
    }

    public String getComLugar() {
        return comLugar;
    }

    public void setComLugar(String comLugar) {
        this.comLugar = comLugar;
    }

    public UploadedFile getFotoValLugar() {
        return fotoValLugar;
    }

    public void setFotoValLugar(UploadedFile fotoValLugar) {
        this.fotoValLugar = fotoValLugar;
    }

    public List<Valoracion_lug> getListLug() {
        return listLug;
    }

    public void setListLug(List<Valoracion_lug> listLug) {
        this.listLug = listLug;
    }

    public String añadirComentarioEvento() {

        listEve = evento.getValoraciones_sobre();

        if (listEve == null) {
            listEve = new ArrayList<>();
        }

        if (comEvento == null) {
            comEvento = " ";
        }
        if (valEvento == null) {
            valEvento = 3;
        }

        Valoracion_eve valEve = new Valoracion_eve();
//        valEve.setId(System.currentTimeMillis());
        valEve.setCalificacion(valEvento);
        valEve.setComentario(comEvento);
        if (fotoValEvento == null) {
            valEve.setFotos(new byte[1]);
        } else {
            valEve.setFotos(fotoValEvento.getContents());
        }
        valEve.setRealizado_por(usu);
        List<Valoracion_eve> lveu = usu.getVal_eve();
        if (lveu == null) {
            lveu = new ArrayList<>();
            lveu.add(valEve);
            usu.setVal_eve(lveu);
        }
        valEve.setValoracion_sobre(evento);

        listEve.add(valEve);
        evento.setValoraciones_sobre(listEve);
//        try {
            persistencia.setUsuario(usu);
            persistencia.setEvento(evento);
            persistencia.setValoracion_eve(valEve);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ValorarBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        valEvento = null;
//        valEvento = null;
//        fotoValEvento = null;
//        
        FacesContext.getCurrentInstance()
                .addMessage("imgEvento",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Valoracion añadida", "Valoracion del evento subida"));
        return null;
    }

    public String añadirComentarioLugar() {
        Lugar lug = evento.getOcurre_in(); // Lo cojo de la página que ya tiene uno

        listLug = lug.getValoraciones_sobre();

        if (listLug == null) {
            listLug = new ArrayList<>();
        }
        if (comLugar == null) {
            comLugar = " ";
        }
        if (valLugar == null) {
            valLugar = 3;
        }

        Valoracion_lug valLug = new Valoracion_lug();
//        valLug.setId(System.currentTimeMillis());
        valLug.setCalificacion(valLugar);
        valLug.setComentario(comLugar);
        if (fotoValLugar == null) {
            valLug.setFotos(new byte[1]);
        } else {
            valLug.setFotos(fotoValLugar.getContents());
        }
        valLug.setRealizado_por(usu);
        List<Valoracion_lug> lvlu = usu.getVal_lug();
        if (lvlu == null) {
            lvlu = new ArrayList<>();
            lvlu.add(valLug);
            usu.setVal_lug(lvlu);
        }
        valLug.setValoracion_sobre(lug);
        listLug.add(valLug);
        lug.setValoraciones_sobre(listLug);

//        try {
            persistencia.setUsuario(usu);
            persistencia.setLugar(lug);
            persistencia.setValoracion_lug(valLug);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ValorarBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        valLugar = null;
//        comLugar = null;
//        fotoValLugar = null;
        FacesContext.getCurrentInstance()
                .addMessage("imgLugar",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Valoracion añadida", "Valoracion del lugar subida"));
        return null;
    }

}
