package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import grupoj.prentrega1.Valoracion_lug;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author anaes
 */
@Named(value = "verEvento")
@Dependent
public class verEvento implements Serializable {

    @Inject
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion control;
    private Evento evento;
    private long id;
    private Usuario usu;
    private Map<String, String> req;
    private PdfCreator pdf;

    // En las clases ya los tengo
    private Integer valEvento;
    private String comEvento;
    private UploadedFile fotoValEvento;
    private List<Valoracion_eve> listEve;

    private Integer valLugar;
    private String comLugar;
    private UploadedFile fotoValLugar;
    private List<Valoracion_lug> listLug;

    @PostConstruct
    public void init() {
        usu = control.getUsuario();
        req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.setId(Long.parseLong(req.get("id")));
        Evento ev = new Evento();
        ev.setId(id);
        System.out.println(id);
        if (this.persistencia.getListaEventos().contains(ev)) {
            this.evento = this.persistencia.getListaEventos()
                    .get(
                            this.persistencia.getListaEventos().indexOf(ev)
                    );
        } else {
            this.evento = null;
        }
        // Aniadir al historial del usuario
        if (usu != null) {

            Formulario formu = this.usu.getForm();
            if(formu == null) {
                formu = new Formulario();
                formu.setId(System.currentTimeMillis());
                formu.setHistorialEventos(new ArrayList<Evento>());
                formu.setSobre_by(null);
                formu.setUsuario(usu);
                this.usu.setForm(formu);
            }
            List<Evento> leu = formu.getHistorialEventos();
            if (leu == null) {
                leu = new ArrayList<>();
                this.usu.getForm().setHistorialEventos(leu);
            }
            if (!leu.contains(this.evento)) {
                leu.add(this.evento);
                List<Formulario> lhe = this.evento.getHistoriado_by();

                if (lhe == null) {
                    lhe = new ArrayList<>();
                    this.evento.setHistoriado_by(lhe);
                }
            }
        }

        ev = null;
    }

    /**
     * Creates a new instance of verEvento
     */
    public verEvento() {
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StreamedContent getMultimedia() {
        if (evento.getMultimedia() == null) {
            return null;
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(evento.getMultimedia()));
    }

    public void setMultimedia(StreamedContent mult) {

    }

    public PdfCreator getPdf() {
        return pdf;
    }

    public void setPdf(PdfCreator pdf) {
        this.pdf = pdf;
    }

    public ControlAutorizacion getControl() {
        return control;
    }

    public void setControl(ControlAutorizacion control) {
        this.control = control;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Map<String, String> getReq() {
        return req;
    }

    public void setReq(Map<String, String> req) {
        this.req = req;
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

    public void meInteresa() {
        usu = control.getUsuario();
        if (usu == null) {
            FacesContext.getCurrentInstance()
                    .addMessage("growlmsg",
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Inicia sesión",
                                    "Para añadir me interesa, inicia sesión"));
        } else {
            Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            Evento ev = new Evento();
            ev.setId(Long.parseLong(map.get("id")));
            ev = persistencia.getListaEventos()
                    .get(
                            persistencia.getListaEventos()
                                    .indexOf(ev)
                    );
            if (!usu.getMeInteresa().contains(ev)) {
                FacesContext.getCurrentInstance()
                        .addMessage("growlmsg",
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Me interesa!",
                                        "Guardado con éxito!"));
                usu.getMeInteresa().add(ev);
                ev.getInteresados_at().add(usu);
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage("growlmsg",
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Ya no me interesa",
                                        "Guardado con éxito"));
                usu.getMeInteresa().remove(ev);
                ev.getInteresados_at().remove(usu);
            }
        }
    }

    public StreamedContent getFile() {
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Evento evprima = new Evento();
//        evprima.setId(Long.parseLong(params.get("id")));
//        setEv(persistencia.getListaEventos().get(
//            persistencia.getListaEventos().indexOf(evprima)));
        pdf = new PdfCreator(this.evento);

        StreamedContent stc = new DefaultStreamedContent(
                new ByteArrayInputStream(pdf.getStream()),
                "application/pdf", evento.getNombre() + ".pdf");
        return stc;
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
        valEve.setId(System.currentTimeMillis());
        valEve.setCalificacion(valEvento);
        valEve.setComentario(comEvento);
        if (fotoValEvento == null) {
            valEve.setFotos(new byte[1]);
        } else {
            valEve.setFotos(fotoValEvento.getContents());
        }
        valEve.setRealizado_por(usu);
        valEve.setValoracion_sobre(evento);

        listEve.add(valEve);
        evento.setValoraciones_sobre(listEve);

        valEvento = null;
        valEvento = null;
        fotoValEvento = null;

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
        valLug.setId(System.currentTimeMillis());
        valLug.setCalificacion(valLugar);
        valLug.setComentario(comLugar);
        if (fotoValLugar == null) {
            valLug.setFotos(new byte[1]);
        } else {
            valLug.setFotos(fotoValLugar.getContents());
        }
        valLug.setRealizado_por(usu);
        valLug.setValoracion_sobre(lug);
        listLug.add(valLug);
        lug.setValoraciones_sobre(listLug);

        valLugar = null;
        comLugar = null;
        fotoValLugar = null;

        return null;
    }

}
