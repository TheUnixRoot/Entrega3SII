/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "mod_usuariosBean")
@RequestScoped
public class Mod_usuariosBean implements Serializable {

    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion controlAutorizacion;
    private Usuario usr;
    long id;

    /**
     * Crea un nuevo bean que contine al usuario pasado por parámetros o null si
     * no existiera
     */
    @PostConstruct
    public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        usr = persistencia.getUsuario(Long.parseLong(req.get("id")));
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
        persistencia.setUsuario(usr);
    }

    /**
     * Cuando la instancia posee un usuario correcto, se extrae su byte[]
     * multimedia y se genera la imagen para ser mostrada.
     *
     * @return Imagen del usuario o null en su defecto.
     */
    public StreamedContent generar() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            byte[] mul = persistencia.getUsuario(Long.parseLong(params.get("id")))
                    .getMultimedia();
            con = new DefaultStreamedContent(new ByteArrayInputStream(mul));

        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + params.get("id"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("id") + " excep: " + ne.getMessage());
        }
        return con;
    }

    /**
     * Método que modifica al usuario de persistencia contenido en el bean por
     * los atributos nuevos.
     *
     * @return Vuelve a gestion_usuarios.xhtml siempre
     */
    public String change() {
        System.out.println("Changeeeeeeeee " + usr.getNombre());
        persistencia.setUsuario(usr);
        return "gestion_usuarios.xhtml";
    }
}
