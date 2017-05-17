/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "mod_usuariosBean")
@Dependent
public class Mod_usuariosBean implements Serializable {

    @Inject
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion controlAutorizacion;
    private Usuario usr;
    long id;
    /**
     * Crea un nuevo bean que contine al usuario pasado por parámetros
     * o null si no existiera
     */
    @PostConstruct
    public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        usr = persistencia.getUsuario(Long.parseLong(req.get("id")));
//        Usuario uuu = new Usuario();
//        uuu.setId(id);
//        //System.out.println(id);
//        if ( this.persistencia.getListaUsuarios().contains(uuu) ) 
//            this.usr = this.persistencia.getListaUsuarios()
//                            .get(
//                                this.persistencia.getListaUsuarios().indexOf(uuu)
//                            );
//            
//        else
//            this.usr = null;
//        uuu = null;
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
    }

//    public ControlAutorizacion getControlAutorizacion() {
//        return controlAutorizacion;
//    }
//
//    public void setControlAutorizacion(ControlAutorizacion controlAutorizacion) {
//        this.controlAutorizacion = controlAutorizacion;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Cuando la instancia posee un usuario correcto, se extrae su byte[] 
     * multimedia y se genera la imagen para ser mostrada.
     * @return Imagen del usuario o null en su defecto.
     */
    public StreamedContent generar() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
//            Usuario uu = new Usuario();
//            uu.setId(Long.parseLong(params.get("id")));
            byte[] mul = persistencia.getUsuario(Long.parseLong(params.get("id")))
//                    persistencia
//                    .getListaUsuarios()
//                    .get(persistencia
//                            .getListaUsuarios()
//                            .indexOf(uu)
//                    )
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
     * Método que modifica al usuario de persistencia contenido en el bean
     * por los atributos nuevos.
     * @return Vuelve a gestion_usuarios.xhtml siempre
     */
//    public String change() {
//        //System.out.println("Changeeeeeeeee");
//        this.usr.getId();
//        Iterator<Usuario> it = persistencia.getListaUsuarios().iterator();
//        boolean find = false;
//        Usuario uu = null;
//        while(it.hasNext() && !find) {
//            uu = it.next();
//            if (uu.equals(usr)) {
//                int l = persistencia.getListaUsuarios().indexOf(uu);
//                List<Usuario> list = persistencia.getListaUsuarios();
//                list.set(l, usr);
//                try {
//                    persistencia.setListaUsuarios(list);
//                } catch (InterruptedException ex) {
//                    System.err.println("Error al modificar el usuario");
//                }
//                find = true;
//            }
//        }
//        return "gestion_usuarios.xhtml";
//    }
}