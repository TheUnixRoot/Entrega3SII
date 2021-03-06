/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Anuncio;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
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
@Named(value = "signupAdminBean")
@RequestScoped
public class SignupAdminBean {

    @Inject
    private ControlAutorizacion ctrl;
    @EJB
    private PersistenceMock persistencia;
    private Administrador admin;
    private byte[] flag;

    /**
     * Creates a new instance of SignupBean
     */
    public SignupAdminBean() {
        admin = new Administrador();
    }
//
//    public PersistenceMock getPersistencia() {
//        return persistencia;
//    }
//
//    public void setPersistencia(PersistenceMock persistencia) {
//        this.persistencia = persistencia;
//    }

    public Periodista getAdministrador() {
        return admin;
    }

    public void setAdiministrador(Administrador admin) {
        this.admin = admin;
    }

    public String getPass() {
        return admin.getPassword();
    }

    public void setPass(String pass) {
        this.admin.setPassword(pass);
    }

    public String getNombre() {
        return admin.getNombre();
    }

    public void setNombre(String nombre) {
        this.admin.setNombre(nombre);
    }

    public String getApellidos() {
        return admin.getApellidos();
    }

    public void setApellidos(String apellidos) {
        this.admin.setApellidos(apellidos);
    }

    public String getEmail() {
        return admin.getEmail();
    }

    public void setEmail(String email) {
        this.admin.setEmail(email);
    }

    public String getTelefono() {
        return admin.getTelefono();
    }

    public void setTelefono(String telefono) {
        this.admin.setTelefono(telefono);
    }

    public Date getFechaNacimiento() {
        return admin.getFechaNacimiento();
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.admin.setFechaNacimiento(fechaNacimiento);
    }

    public void setFoto(UploadedFile foto) {
        if (foto.getContents().length > 0) {
            if (foto.getContents().length < 4194300 && foto.getContents().length > 0) {
                this.admin.setMultimedia(foto.getContents());
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage("messages",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imagen demasiado grande",
                                        "Debe pesar menos de 4Mb"));
                flag = foto.getContents();
            }
        } else {
            this.admin.setMultimedia(new byte[1]);
        }
    }

    public String getSeccion() {
        return admin.getSeccion();
    }

    public void setSeccion(String seccion) {
        this.admin.setSeccion(seccion);
    }

    public String getPuesto() {
        return admin.getPuesto();
    }

    public void setPuesto(String puesto) {
        this.admin.setPuesto(puesto);
    }

    public void setIdentificador(Long id) {
        admin.setIdentificador(id);
    }

    public Long getIdentificador() {
        return admin.getIdentificador();
    }

    public UploadedFile getFoto() {
        return null;
    }

    /**
     * Da de alta un usuario con los campos previamente rellenados como
     * atributos
     *
     * @return Vuelve a gestion_usuarios.xhtml siempre
     */
    public String submit() {
        
        if(flag != null)
            return null;
        
//        List<Usuario> list = persistencia.getListaUsuarios();
//        admin.setId(System.currentTimeMillis());
        admin.setBorrado(false);
        admin.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        admin.setNotificaciones(new ArrayList<Notificacion>());
        admin.setMeInteresa(new ArrayList<Evento>());
        admin.setMsg_send(new ArrayList<Mensaje>());
        admin.setGestionarNotificacion(new ArrayList<Notificacion>());
        admin.setGestionarLugar(new ArrayList<Lugar>());
        admin.setGestionarEvento(new ArrayList<Evento>());
        admin.setRecibirMensaje(new ArrayList<Mensaje>());
        admin.setEsGestionado(new ArrayList<Usuario>());
        admin.setAnuncios_by(new ArrayList<Anuncio>());
//        try {
            persistencia.setAdministrador(admin);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SignupAdminBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        list.add(admin);
//        try {
//            persistencia.setListaUsuarios(list);
//            
//        } catch (InterruptedException ex) {
//            System.err.println("Error al insertar administrador en persistencia");
//        }
        return "gestion_usuarios.xhtml";
    }
}
