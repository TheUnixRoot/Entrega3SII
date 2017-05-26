package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.*;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author anaes
 */
@Named(value = "configurarPerfil")
@RequestScoped
public class configurarPerfil {

    @Inject
    private ControlAutorizacion control;

    @EJB
    private PersistenceMock persistencia;
    /*
    private List<Usuario> listaUsuario;
    private byte[] foto;
     */
    private Usuario usuario;

    /*
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenia;
    private String telefono;
    private Date fechaNacimiento;
     */
    /**
     * Creates a new instance of configurarPerfil
     */
    @PostConstruct
    public void init() {
        /*
        listaUsuario = persistencia.getListaUsuarios();
         */
        usuario = control.getUsuario();
        /*
        listaUsuario.remove(usuario);
        foto = usuario.getMultimedia();
        nombre = usuario.getNombre();
        apellidos = usuario.getApellidos();
        email = usuario.getEmail();
        contrasenia = usuario.getPassword();
        telefono = usuario.getTelefono();
        fechaNacimiento = usuario.getFechaNacimiento();
         */
    }

    public StreamedContent getFoto() {
        return new DefaultStreamedContent(new ByteArrayInputStream(this.usuario.getMultimedia()));
    }

    public void setFoto(StreamedContent foto) {

    }

    public UploadedFile getFoto2() {
        return null;
    }

    public void setFoto2(UploadedFile foto) {
        //System.out.println("jummm");
        if (foto.getContents().length > 0) {
            this.usuario.setMultimedia(foto.getContents());
        }
    }

    public ControlAutorizacion getControl() {
        return control;
    }

    public void setControl(ControlAutorizacion control) {
        this.control = control;
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

    public String configurar() {
        /*
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(contrasenia);
        usuario.setTelefono(telefono);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setMultimedia(foto);
         */
        /*List<Usuario> listaUsuario = persistencia.getListaUsuarios();
        listaUsuario.set(
                listaUsuario.indexOf(usuario), usuario);
//        try {
            persistencia.setListaUsuarios(listaUsuario);*/
            persistencia.setUsuario(usuario);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(configurarPerfil.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return "index.xhtml";

    }

}
