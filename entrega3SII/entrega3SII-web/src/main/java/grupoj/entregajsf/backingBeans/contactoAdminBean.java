/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;

/**
 *
 * @author JesusAlberto
 */
@RequestScoped
@Named(value = "contactoAdminBean")
public class contactoAdminBean {

    @Inject
    private PersistenceMock persistencia;
    private Mensaje message;
    private String texto;
    private String asunto;
    private Usuario user;
    private List<Usuario> listUsers;
    private List<Administrador> admins;
    @Inject
    private ControlAutorizacion control;

    /**
     * Creates a new instance of contactoAdminBean
     */
    @PostConstruct
    public void init() {
//        persistencia = new PersistenceMock();
        listUsers = persistencia.getListaUsuarios();
        user = control.getUsuario();
        message = new Mensaje();
        admins = new ArrayList();

        for (Usuario u : listUsers) {
            if (u instanceof Administrador) {
                admins.add((Administrador) u);
            }
        }

    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String crearMensaje() {
        message.setTexto(this.texto);
        message.setAsunto(this.asunto);
        message.setEnviadoPor(user);
        message.setRecibidoPor(admins);
        for (Administrador admin : admins) {
            if (admin.getRecibirMensaje() == null) {
                List<Mensaje> listaMensajes = new ArrayList<>();
                admin.setRecibirMensaje(listaMensajes);
            }
            admin.getRecibirMensaje().add(message);
            try {
                // Actualizamos los administradores, que esten enlazados con el msg
                persistencia.setAdministrador(admin);
            } catch (InterruptedException ex) {
                Logger.getLogger(contactoAdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (user != null) {
            if (user.getMsg_send() == null) {
                List<Mensaje> listaMensajes = new ArrayList<>();
                user.setMsg_send(listaMensajes);
            }
            user.getMsg_send().add(message);
            try {
                //Actualizamos usuario que envia el mensaje
                persistencia.setUsuario(user);
            } catch (InterruptedException ex) {
                Logger.getLogger(contactoAdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            // Metemos el mensaje en la persistencia
            persistencia.setMensaje(message);
        } catch (InterruptedException ex) {
            Logger.getLogger(contactoAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage("formulario:panel:growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje enviado correctamente", "Mensaje enviado correctamente"));
        return null;
    }

}
