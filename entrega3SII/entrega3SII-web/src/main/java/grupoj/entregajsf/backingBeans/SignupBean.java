/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "signupBean")
@RequestScoped
public class SignupBean {
    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private PersistenceMock persistencia;
    private Usuario usuario;
    
    /**
     * Creates a new instance of SignupBean
     */
    public SignupBean() {
        usuario = new Usuario();
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return usuario.getPassword();
    }

    public void setPass(String pass) {
        this.usuario.setPassword(pass);
    }

    public String getNombre() {
        return usuario.getNombre();
    }

    public void setNombre(String nombre) {
        this.usuario.setNombre(nombre);
    }
    
    public String getApellidos() {
        return usuario.getApellidos();
    }

    public void setApellidos(String apellidos) {
        this.usuario.setApellidos(apellidos);
    }
    
    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        this.usuario.setEmail(email);
    }
    
    public String getTelefono() {
        return usuario.getTelefono();
    }

    public void setTelefono(String telefono) {
        this.usuario.setTelefono(telefono);
    }
    
    public Date getFechaNacimiento() {
        return usuario.getFechaNacimiento();
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.usuario.setFechaNacimiento(fechaNacimiento);
    }
    
    public void setFoto(UploadedFile foto) {
        if(foto.getContents().length > 0)
            this.usuario.setMultimedia(foto.getContents());
        else 
            this.usuario.setMultimedia(new byte[1]);
    }
    
    public UploadedFile getFoto() {
        return null;
    }
    
    /**
     * Da de alta un usuario con los campos previamente rellenados como atributos
     * @return Vuelve a index.xhtml siempre
     */
    public String submit() {
        List<Usuario> list = persistencia.getListaUsuarios();
        usuario.setId(System.currentTimeMillis());
        usuario.setBorrado(false);
        usuario.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usuario.setNotificaciones(new ArrayList<Notificacion>());
        usuario.setMeInteresa(new ArrayList<Evento>());
        usuario.setMsg_send(new ArrayList<Mensaje>());
        list.add(usuario);
        try {
            persistencia.setListaUsuarios(list);
            ctrl.setUsuario(usuario);
        } catch (InterruptedException ex) {
            System.err.println("Error al insertar usuario en persistencia");
        }
        return "index.xhtml";
    }
}

