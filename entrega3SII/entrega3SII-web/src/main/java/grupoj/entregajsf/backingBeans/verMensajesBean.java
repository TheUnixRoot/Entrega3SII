/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Administrador;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author jesus
 */
@Named(value = "verMensajesBean")
@Dependent
public class verMensajesBean {

    @Inject
    private ControlAutorizacion controlAutorizacion;
    @EJB
    private PersistenceMock persistencia;

//
//    /*
//     * Creates a new instance of verMensajesBean
//     */
//    public verMensajesBean() {
//    }
//    
    
    public ControlAutorizacion getControlAutorizacion() {
        return controlAutorizacion;
    }

    public void setControlAutorizacion(ControlAutorizacion controlAutorizacion) {
        this.controlAutorizacion = controlAutorizacion;
    }


    public List<Mensaje> getMensajes() {
        
        Administrador adm = persistencia.getAdministrador(controlAutorizacion.getUsuario().getId());
        
        List<Mensaje> list = adm.getRecibirMensaje();
        System.out.println(list.get(0));
        return list;
    }

}
