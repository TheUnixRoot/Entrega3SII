/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;



import grupoj.prentrega1.Lugar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;


/**
 *
 * @author migue
 */
@Named(value = "verLugarBean")
@RequestScoped
public class verLugarBean {

    @Inject
    private PersistenceMock persistencia;
    
    private List<Lugar> listaLugares;
    
    //inicializamos la lista de lugares
    @PostConstruct
    public void init() {
        listaLugares = persistencia.getListaLugares();
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException{
        
        persistencia.setListaLugares(listaLugares);
    }
    
    // navegamos a la pagina edit_lugar con el parametro "id" que vamos a utilizar
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "edit_lugar.xhtml?id=" + params.get("id");
    }
    
    // navegamos a la pagina verLugar con el parametro "id" que vamos a utilizar
    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "verLugar.xhtml?id=" + params.get("id");
    }
    
    // navegamos a la pagina eliminarLugar con el parametro "id" que vamos a utilizar
    public String viajarE() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "eliminarLugar.xhtml?id=" + params.get("id");
    }
    
    
}