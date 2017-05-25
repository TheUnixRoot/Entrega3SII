/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;

/**
 *
 * @author Migue
 */
@Named(value = "crearNotificacionBean")
@RequestScoped
public class crearNotificacionBean {

    @Inject
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
        listaNotificaciones = persistencia.getListaNotificaciones();
        
            
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

    
    
    
    
    
   public void crearNotificacion() throws InterruptedException{
       
       
       n.setId(System.currentTimeMillis());
       
       
       n.setFecha(new Date());
       n.setFormato(evento.getNombre());
       List<Usuario> listaUsuarios =evento.getInteresados_at();
      n.setUsuarios(listaUsuarios);
       for (Usuario u : listaUsuarios){
           
           List<Notificacion> lnoti = u.getNotificaciones();
           if(lnoti == null) {
               lnoti = new ArrayList<>();
               u.setNotificaciones(lnoti);
           }
               lnoti.add(n);
           persistencia.setUsuario(u);
           
         }
       
       
       persistencia.setNotificacion(n);
       
       FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación enviada con éxito", "Notificación enviada con éxito");
            FacesContext.getCurrentInstance().addMessage("growlmensaje", fm);
       
       
       
       
       
       
   }

}
