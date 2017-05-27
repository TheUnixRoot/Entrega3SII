/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Lugar;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author migue
 */
@Named(value = "mod_LugarBean")
@RequestScoped
public class Mod_LugarBean implements Serializable {

    @EJB
    private PersistenceMock persistencia;
    private Lugar adv;
    private Long ids;
    private byte[] img;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public UploadedFile getFoto() {
        return null;
    }

    public void setFoto(UploadedFile foto) {
        if (foto.getContents().length > 0) {

            if (foto.getContents().length < 4194300 && foto.getContents().length > 0) {
                this.adv.setFotos(foto.getContents());
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage("messages",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imagen demasiado grande",
                                        "Debe pesar menos de 4Mb"));
                img = foto.getContents();
            }
        }
    }

    public StreamedContent getFoto2() {

        if (adv.getFotos() == null) {
            adv.setFotos(new byte[1]);
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getFotos()));
    }

    public void setFoto2(StreamedContent foto) {

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

    public String modificarLugar() {

//        try {
        //        List<Lugar> lista =persistencia.getListaLugares();
//
//        lista.set(persistencia.getListaLugares().indexOf(adv), adv);
//
        if (img != null) {
            return null;
        }
        persistencia.setLugar(adv);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Mod_LugarBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
