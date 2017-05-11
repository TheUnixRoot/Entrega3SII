/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "crud_usuariosBean")
@Dependent
public class Crud_usuariosBean implements Serializable{
    
    @Inject
    private PersistenceMock persistencia;
    
    public List<Usuario> getUsuarios() {
        return persistencia.getListaUsuarios();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        try {
            this.persistencia.setListaUsuarios(usuarios);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Obtiene la url destino tras comprobar los parametros del contexto
     * Segun la id y el valor de editar.
     * @return Devuelve una url de vista o de vista y escritura para el usuario
     * solicitado.
     */
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return params.get("editar").equals("true") ?("edit_usuario.xhtml?id=" + params.get("id")) : ("read_usuario.xhtml?id=" + params.get("id"));
    }
    
    /**
     * Dado un usuario correcto, se extrae su byte[] multimedia y 
     * se genera la imagen para ser mostrada.
     * @return Imagen del usuario
     */
    public StreamedContent generar() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            Usuario uu = new Usuario();
            uu.setId(Long.parseLong(params.get("id")));
            byte[] mul = persistencia
                    .getListaUsuarios()
                    .get(persistencia
                            .getListaUsuarios()
                            .indexOf(uu)
                    )
                    .getMultimedia();
            con = new DefaultStreamedContent(new ByteArrayInputStream(mul)); 
            
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + params.get("id"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("id") + " excep: " + ne.getMessage());
        }
        return con;
    }  
}

