/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Evento;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author migue
 */
@Named(value = "mod_eventoBean")
@RequestScoped
public class Mod_eventoBean implements Serializable {

    @EJB
    private PersistenceMock persistencia;
    private Evento adv;
    private Long ids;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public UploadedFile getFoto() {
        return null;
    }

    public void setFoto(UploadedFile foto) {
        if (foto.getContents().length > 0) {
            this.adv.setMultimedia(foto.getContents());
        }
    }

    public StreamedContent getFoto2() {
        if (adv.getMultimedia() == null) {
            adv.setMultimedia(new byte[1]);
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }

    public void setFoto2(StreamedContent foto) {

    }

    public void setHora(Date hora) {
        this.adv.getFecha_inicio().setHours(hora.getHours());
        this.adv.getFecha_inicio().setMinutes(hora.getMinutes());
    }

    public Date getHora() {
        return adv.getHora();
    }

    @PostConstruct
    public void init() {
        adv = persistencia.getEvento(Long.parseLong(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap().get("id")
        ));
//        adv = persistencia
//                .getListaEventos()
//                .get(
//                persistencia
//                .getListaEventos()
//                .indexOf(add)
//                );

    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Evento getAdv() {
        return adv;
    }

    public void setAdv(Evento adv) {
        this.adv = adv;
    }

    public String modificarEvento() {
//        try {
            persistencia.setEvento(adv);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Mod_eventoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//       List<Evento> lista = persistencia.getListaEventos();
//       lista.set(
//               persistencia.getListaEventos().indexOf(adv),
//               adv);
//        try {
//            persistencia.setListaEventos(lista);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Mod_eventoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return "gestion_evento.xhtml";

    }
}
