/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.emailSender.SendEmail;
import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Migue
 */
@Named(value = "crearNotificacionBean")
@RequestScoped
public class crearNotificacionBean {

    @EJB
    private PersistenceMock persistencia;
    private List<Notificacion> listaNotificaciones;
    private Notificacion n;
    private Evento evento;
    private Long id;
    private Map<String, String> req;

    @PostConstruct
    public void init() {

        req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        id = Long.parseLong(req.get("id"));
        this.evento = persistencia.getEvento(id);
        n = new Notificacion();
        // listaNotificaciones = persistencia.getListaNotificaciones();

    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Notificacion getN() {
        return n;
    }

    public void setN(Notificacion n) {
        this.n = n;
    }

    public String getContenido() {
        return this.n.getContenido();
    }

    public void setContenido(String contenido) {
        this.n.setContenido(contenido);
    }

    public Long getId() {
        return this.evento.getId();
    }

    public void setId(Long id) {
        this.evento.setId(id);
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    public void crearNotificacion() throws InterruptedException {

        //n.setId(System.currentTimeMillis());
        n.setFecha(new Date());
        n.setEv(evento);
        List<Usuario> listaUsuarios = new ArrayList<>();
        List<Usuario> listaEmail = new ArrayList<>();
        
        for(Usuario u : evento.getInteresados_at()) {
            if(u.getTipoNotificacionesRecibir().equals(TipoNotificacion.Ambos) 
                    || u.getTipoNotificacionesRecibir().equals(TipoNotificacion.Cuenta)) {
                listaUsuarios.add(u);
            }
            if(u.getTipoNotificacionesRecibir().equals(TipoNotificacion.Ambos)
                    || u.getTipoNotificacionesRecibir().equals(TipoNotificacion.Email)) {
                listaEmail.add(u);
            }
        }
        
        
        //n.setUsuarios(listaUsuarios);
        for (Usuario u : listaUsuarios) {

            List<Notificacion> lnoti = u.getNotificaciones();
            if (lnoti == null) {
                lnoti = new ArrayList<>();
                u.setNotificaciones(lnoti);
            }
            lnoti.add(n);
            persistencia.setUsuario(u);

        }
        
        // Sending emails
        for(Usuario u : listaEmail) {
            SendEmail.sendNotificacion(n, u);
        }

        //persistencia.setNotificacion(n);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación enviada con éxito", "Notificación enviada con éxito");
        FacesContext.getCurrentInstance().addMessage("growlmensaje", fm);

    }

}
