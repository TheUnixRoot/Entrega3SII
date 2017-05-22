/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @deprecated No se utiliza en el proyecto
 * @author juanp
 */
@Named(value = "downloaderFotoBean")
@ViewScoped
public class DownloaderFotoBean implements Serializable {

    @EJB
    private PersistenceMock persistencia;
    private Iterator<Usuario> it;

    public DownloaderFotoBean() {
    }

    /**
     * Itera por la lista de usuarios obtenidas desde la persistencia para
     * generar las diversas imagenes, el iterador es propio del bean, por lo que
     * debe ser llamada siempre la misma instancia del mismo para avanzar
     *
     * @return Imagen de un usuario o null si no hubiera
     */
    public StreamedContent generar() {
        StreamedContent con = null;
        try {
            if (it == null) {
                it = persistencia.getListaUsuarios().iterator();
            } else if (it.hasNext()) {
                byte[] mul = it.next().getMultimedia();
                con = new DefaultStreamedContent(new ByteArrayInputStream(mul));
            }
        } catch (IndexOutOfBoundsException ie) {
            Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ie);
        }
        return con;
    }

}
