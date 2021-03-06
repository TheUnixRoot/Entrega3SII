package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import grupoj.prentrega1.Valoracion_lug;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Faces;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author anaes
 */
@Named(value = "verEvento")
@RequestScoped
public class verEvento implements Serializable {

    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion control;
    private Evento evento;
    private long id;
    private Usuario usu;
    private Map<String, String> req;
    private PdfCreator pdf;
    private Date fecha_inicio;

    @PostConstruct
    public void init() {
        usu = control.getUsuario();
        req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = Long.parseLong(req.get("id"));
        Evento ev = persistencia.getEvento(id);
//        ev.setId(id);
//        System.out.println(id);
//        if (ev != null) {
        this.evento = ev;
//            this.persistencia.getListaEventos()
//                    .get(
//                            this.persistencia.getListaEventos().indexOf(ev)
//                    );
//        } else {
//            this.evento = null;
//        }
        // Aniadir al historial del usuario
        if (usu != null) {

            Formulario formu = this.usu.getForm();
            if (formu == null) {
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

//            try {
//                // Actualizar cambios en usuario y eventos
            persistencia.setUsuario(this.usu);
            persistencia.setEvento(this.evento);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(verEvento.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }

        //       ev = null;
    }

    /*
     * Creates a new instance of verEvento
     */
//    public verEvento() {
//    }
//
//    public PersistenceMock getPersistencia() {
//        return persistencia;
//    }
//
//    public void setPersistencia(PersistenceMock persistencia) {
//        this.persistencia = persistencia;
//    }
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

    public void setFecha_inicio(Date fechaI) {
        Date fecha = new Date(fechaI.getTime());
        evento.setFecha_inicio(fecha);
        Time hora = new Time(fechaI.getHours(), fechaI.getMinutes(), 0);
        evento.setHora(hora);
    }

    public Date getFecha_inicio() {
        Date date = new Date(evento.getFecha_inicio().getTime());
        date.setHours(evento.getHora().getHours());
        date.setMinutes(evento.getHora().getMinutes());
        return date;
    }

    public Map<String, String> getReq() {
        return req;
    }

    public void setReq(Map<String, String> req) {
        this.req = req;
    }

    public void facebook() throws IOException {
        Faces.getExternalContext().redirect("https://www.facebook.com/sharer/sharer.php?u="
                + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString()
                + "?id=" + this.evento.getId());
    }

    public void twitter() throws IOException {
        Faces.getExternalContext().redirect("https://twitter.com/intent/tweet?url="
                + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString()
                + "?id=" + this.evento.getId()
                + "&text=Mirad%20este%20evento%21%20");
    }

    public void meInteresa() {
        try {
            usu = control.getUsuario();
            if (usu == null) {
                FacesContext.getCurrentInstance()
                        .addMessage("growlmsg",
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Inicia sesión",
                                        "Para añadir me interesa, inicia sesión"));
            } else {
                Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                Evento ev = persistencia.getEvento(Long.parseLong(map.get("id")));
//            ev = persistencia.getListaEventos()
//                    .get(
//                            persistencia.getListaEventos()
//                                    .indexOf(ev)
//                    );
                if (!(usu.getMeInteresa().contains(ev)) && !(ev.getInteresados_at().contains(usu))) {
                    FacesContext.getCurrentInstance()
                            .addMessage("growlmsg",
                                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                                            "Me interesa!",
                                            "Guardado con éxito!"));
                    usu.getMeInteresa().add(ev);
                    // Lo añadimos a ambos 
                    ev.getInteresados_at().add(usu);
//                ev.getInteresados_at().add(usu);
                } else {
                    FacesContext.getCurrentInstance()
                            .addMessage("growlmsg",
                                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                                            "Ya no me interesa",
                                            "Guardado con éxito"));
                    usu.getMeInteresa().remove(ev);
                    // Borramos al usuario de los interesados en el evento
                    ev.getInteresados_at().remove(usu);
//                ev.getInteresados_at().remove(usu);
                }
//            try {
                persistencia.setUsuario(usu);
                persistencia.setEvento(ev);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(verEvento.class.getName()).log(Level.SEVERE, null, ex);
//            }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage("growlmsg",
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Ya no me interesa",
                                    "Guardado con éxito"));
            usu = persistencia.getUsuario(this.control.getUsuario().getId());
            usu.getMeInteresa().remove(this.evento);
            persistencia.setUsuario(usu);
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

    /**
     * Genera el StreamedContent de la valoracion de un evento
     *
     * @return
     */
    public StreamedContent generarEve() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            Valoracion_eve val = persistencia.getValoracion_eve(Long.parseLong(params.get("ide")));
//            val.setId(Long.parseLong(params.get("ide")));
            byte[] mul = val.getFotos();

//                    evento.getValoraciones_sobre()
//                    .get(
//                            evento.getValoraciones_sobre().indexOf(val)
//                    ).getFotos();
            con = new DefaultStreamedContent(new ByteArrayInputStream(mul));

        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id valoracion recibido " + params.get("ide"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("ide") + " excep: " + ne.getMessage());
        }
        return con;
    }

    /**
     * Genera el StreamedContent de la valoracion de un lugar
     *
     * @return
     */
    public StreamedContent generarLug() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            Valoracion_lug val = persistencia.getValoracion_lug(Long.parseLong(params.get("idl")));
//            val.setId(Long.parseLong(params.get("idl")));
            byte[] mul = val.getFotos();
//                    evento.getOcurre_in().getValoraciones_sobre()
//                    .get(
//                            evento.getOcurre_in().getValoraciones_sobre().indexOf(val)
//                    ).getFotos();
            con = new DefaultStreamedContent(new ByteArrayInputStream(mul));

        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id valoracion recibido " + params.get("idl"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("idl") + " excep: " + ne.getMessage());
        }
        return con;
    }
}
