/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Lugar;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;



/**
 *
 * @author migue
 */
@Named(value = "mod_LugarBean")
@Dependent
public class Mod_LugarBean implements Serializable {

    @Inject
    PersistenceMock persistencia;
    private Lugar adv;
    private Long ids;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }
    
    public UploadedFile getFoto(){
        return null;
    }
    
    public void setFoto(UploadedFile foto){
    
        if(foto.getContents().length > 0){
            this.adv.setFotos(foto.getContents());
        }
    }
    
    public StreamedContent getFoto2(){
    
        if(adv.getFotos() == null){
        adv.setFotos(new byte[1]);
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getFotos()));
    }
    
    public void setFoto2(StreamedContent foto){
    
    }
    
    
    @PostConstruct
    public void init() {
//        Lugar add = new Lugar();
//        add.setId(Long.parseLong(
//                FacesContext.getCurrentInstance()
//                        .getExternalContext()
//                .getRequestParameterMap().get("id")
//        ));
        adv = persistencia
                .getLugar(Long.parseLong(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                .getRequestParameterMap().get("id"))
                );
        
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Lugar getAdv() {
        return adv;
    }

    public void setAdv(Lugar adv) {
        this.adv = adv;
    }
    
    
    public String modificarLugar(){

//        List<Lugar> lista =persistencia.getListaLugares();
//        
//        lista.set(persistencia.getListaLugares().indexOf(adv), adv);
//        
        persistencia.setLugar(adv);
//        try{
//        
//            persistencia.setListaLugares(lista);
//        } catch (InterruptedException ex){
//        
//            Logger.getLogger(Mod_LugarBean.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        
        return "gestion_lugar.xhtml";

    }
}
