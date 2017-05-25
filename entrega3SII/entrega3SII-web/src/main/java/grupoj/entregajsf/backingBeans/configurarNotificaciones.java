/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author David Ahora mismo falta de todo.... Mirar la configuración que tiene
 * el usuario, cargarla y despues enviar en el caso de que haya cambios (¿Eso es
 * para mi?)
 */
@Named(value = "configurarNotificaciones")
@RequestScoped
public class configurarNotificaciones {

    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion ctrAut;
    private boolean notificacionesActivas;
    private Usuario usuLogueado;    // Recoger el usuario que se está logueado
    private TipoNotificacion tipoNotUsuario;
    private String notificacion;
    private List<String> listaNotifMostrar;

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public boolean isNotificacionesActivas() {
        return this.notificacionesActivas;
    }

    public void setNotificacionesActivas(boolean notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }

    public Usuario getUsuLogueado() {
        return usuLogueado;
    }

    public void setUsuLogueado(Usuario usuLogueado) {
        this.usuLogueado = usuLogueado;
    }

    public TipoNotificacion getTipoNotUsuario() {
        return tipoNotUsuario;
    }

    public void setTipoNotUsuario(TipoNotificacion tipoNotUsuario) {
        this.tipoNotUsuario = tipoNotUsuario;
    }

    public List<String> getlistaNotifMostrar() {
        return listaNotifMostrar;
    }

    public void setlistaNotifMostrar(List<String> listaNotifMostrar) {
        this.listaNotifMostrar = listaNotifMostrar;
    }

    @PostConstruct
    public void init() {
        usuLogueado = ctrAut.getUsuario(); // Usuario que se ha logueado, ahora esta el de persistencia.
        if (usuLogueado != null) {
            tipoNotUsuario = usuLogueado.getTipoNotificacionesRecibir();
            
            System.out.println("Notificacion: " + tipoNotUsuario);
            listaNotifMostrar = new ArrayList<>();
            // Activa y comprueba si estan o no, más normal que esten activas
            notificacionesActivas = true;
            if (tipoNotUsuario == TipoNotificacion.Desactivado) {
                notificacionesActivas = false;
            }
            rellenaLista();
        }
    }

    private void insertaLista(TipoNotificacion n) {
        switch (n) {
            case Email:
                listaNotifMostrar.add("Email");
                break;

            case Cuenta:
                listaNotifMostrar.add("Cuenta");
                break;

            case Ambos:
                listaNotifMostrar.add("Ambos");
                break;

            default:
                break;
        }
    }

    /**
     * Rellena la lista de notificaciones a mostrar siendo la primera opción la
     * que tinene configurada el usuario.
     */
    private void rellenaLista() {
        // Inserta la primera en la lista que va a mostrar
        insertaLista(tipoNotUsuario);
        // Despues relleno con las que quedan.
        if (tipoNotUsuario != TipoNotificacion.Email) {
            insertaLista(TipoNotificacion.Email);
        }

        if (tipoNotUsuario != TipoNotificacion.Cuenta) {
            insertaLista(TipoNotificacion.Cuenta);
        }

        if (tipoNotUsuario != TipoNotificacion.Ambos) {
            insertaLista(TipoNotificacion.Ambos);
        }

    }

    /**
     * Recoge los datos de la configuración de las notificaciones del usuario y
     * modifica las opciones de configuración de él.
     *
     * @return null, recarga la página actualizando la información.
     * @throws InterruptedException
     */
    public String tratarInformacion() throws InterruptedException {
        if (notificacion == null) {
            notificacion = "Desactivado";
        }
        switch (notificacion) {
            case "Email":
                tipoNotUsuario = TipoNotificacion.Email;
                break;
            case "Cuenta":
                tipoNotUsuario = TipoNotificacion.Cuenta;
                break;
            case "Ambos":
                tipoNotUsuario = TipoNotificacion.Ambos;
                break;
            default:
                break;
        }

        // Si se han desactivado las notificiaciones.
        if (!notificacionesActivas) {
            this.tipoNotUsuario = TipoNotificacion.Desactivado;
        }

        usuLogueado.setTipoNotificacionesRecibir(tipoNotUsuario);
//        usuPrima.setTipoNotificacionesRecibir(tipoNotUsuario);
//        List<Usuario> listaUsu = this.persistencia.getListaUsuarios();
//        listaUsu.remove(usuLogueado);
//        listaUsu.add(usuPrima);
//        this.persistencia.setListaUsuarios(listaUsu);
//        ctrAut.setUsuario(usuPrima);

//      Actualizar usuario en persistencia
        persistencia.setUsuario(usuLogueado);

        FacesContext.getCurrentInstance()
                .addMessage("confNotificaciones:mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Actualizado"));

        return null;
    }

}
