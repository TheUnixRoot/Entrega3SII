/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "signupPeriodistaBean")
@RequestScoped
public class SignupPeriodistaBean {

    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private PersistenceMock persistencia;
    private Periodista periodista;

    /**
     * Creates a new instance of SignupBean
     */
    public SignupPeriodistaBean() {
        periodista = new Periodista();
    }
//
//    public PersistenceMock getPersistencia() {
//        return persistencia;
//    }
//
//    public void setPersistencia(PersistenceMock persistencia) {
//        this.persistencia = persistencia;
//    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    public String getPass() {
        return periodista.getPassword();
    }

    public void setPass(String pass) {
        this.periodista.setPassword(pass);
    }

    public String getNombre() {
        return periodista.getNombre();
    }

    public void setNombre(String nombre) {
        this.periodista.setNombre(nombre);
    }

    public String getApellidos() {
        return periodista.getApellidos();
    }

    public void setApellidos(String apellidos) {
        this.periodista.setApellidos(apellidos);
    }

    public String getEmail() {
        return periodista.getEmail();
    }

    public void setEmail(String email) {
        this.periodista.setEmail(email);
    }

    public String getTelefono() {
        return periodista.getTelefono();
    }

    public void setTelefono(String telefono) {
        this.periodista.setTelefono(telefono);
    }

    public Date getFechaNacimiento() {
        return periodista.getFechaNacimiento();
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.periodista.setFechaNacimiento(fechaNacimiento);
    }

    public void setFoto(UploadedFile foto) {
        if (foto.getContents().length > 0) {
            this.periodista.setMultimedia(foto.getContents());
        } else {
            this.periodista.setMultimedia(new byte[1]);
        }
    }

    public String getSeccion() {
        return periodista.getSeccion();
    }

    public void setSeccion(String seccion) {
        this.periodista.setSeccion(seccion);
    }

    public String getPuesto() {
        return periodista.getPuesto();
    }

    public void setPuesto(String puesto) {
        this.periodista.setPuesto(puesto);
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
//        List<Usuario> list = persistencia.getListaUsuarios();
//        periodista.setId(System.currentTimeMillis());
        periodista.setBorrado(false);
        periodista.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        periodista.setNotificaciones(new ArrayList<Notificacion>());
        periodista.setMeInteresa(new ArrayList<Evento>());
        periodista.setMsg_send(new ArrayList<Mensaje>());
        periodista.setGestionarNotificacion(new ArrayList<Notificacion>());
        periodista.setGestionarLugar(new ArrayList<Lugar>());
        periodista.setGestionarEvento(new ArrayList<Evento>());
        try {
            persistencia.setPeriodista(periodista);
        } catch (InterruptedException ex) {
            Logger.getLogger(SignupPeriodistaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

//        list.add(periodista);
//        try {
//            persistencia.setListaUsuarios(list);
//            
//        } catch (InterruptedException ex) {
//            System.err.println("Error al insertar usuario en persistencia");
//        }
        return "gestion_usuarios.xhtml";
    }
}
