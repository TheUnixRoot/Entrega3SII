/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Notificacion;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "ver_notificacionesBean")
@RequestScoped
public class Ver_notificacionesBean {

    @Inject
    private ControlAutorizacion controlAutorizacion;
    @EJB
    private PersistenceMock persistencia;

    /**
     * Creates a new instance of Ver_notificacionesBean contains a list of
     * notificaciones for current user
     */
    public Ver_notificacionesBean() {
    }

    public ControlAutorizacion getControlAutorizacion() {
        return controlAutorizacion;
    }

    public void setControlAutorizacion(ControlAutorizacion controlAutorizacion) {
        this.controlAutorizacion = controlAutorizacion;
    }
//
//    public PersistenceMock getPersistencia() {
//        return persistencia;
//    }
//
//    public void setPersistencia(PersistenceMock persistencia) {
//        this.persistencia = persistencia;
//    }

    public List<Notificacion> getNotificaciones() {
        List<Notificacion> list = persistencia
                .getUsuario(
                        controlAutorizacion
                                .getUsuario().getId()
                ).getNotificaciones();
        return list;
    }

}
