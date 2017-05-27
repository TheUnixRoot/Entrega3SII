/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Anuncio;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jesus
 */
@Named(value = "edit_imgcorpBean")
@RequestScoped
public class Edit_imgcorpBean {

    
    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion ca;
    private Anuncio adv;
    private UploadedFile file;
    
    /**
     * Creates a new instance of Edit_imgcorpBean
     */
    public Edit_imgcorpBean() {
        
         adv = new Anuncio();
         adv.setLugar("self");
         adv.setEmpresa("Diario Sur");
         adv.setDias_contratados(Integer.MAX_VALUE);
    }
    
 

    public long getId() {
        return this.adv.getId();
    }

    public void setId(long id) {
        this.adv.setId(id);
    }

    public Date getFecha_public() {
        return this.adv.getFecha_public();
    }

    public void setFecha_public(Date fecha_publicacion) {
        this.adv.setFecha_public(fecha_publicacion);
    }

    public UploadedFile getMultimedia() {
        return file;
    }

    public void setMultimedia(UploadedFile multimedia) {
        if (multimedia.getContents() != null && multimedia.getContents().length > 0) {
            if (multimedia.getContents().length < 4194300 && multimedia.getContents().length > 0) {
                adv.setMultimedia(multimedia.getContents());
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage("messages",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imagen demasiado grande",
                                        "Debe pesar menos de 4Mb"));
                adv.setMultimedia(new byte[1]);
            }
        } else {
            adv.setMultimedia(new byte[1]);
        }
        this.file = multimedia;
    }

    public boolean isOnline() {
        return this.adv.isOnline();
    }

    public void setOnline(boolean online) {
        this.adv.setOnline(online);
    }

    /**
     * Dada la persistencia y los datos para el anuncio, lo crea y almacena en
     * persistencia.
     *
     * @return Vuelve a gestion_anuncios.xhtml siempre
     */
    public String grabar() {
        List<Anuncio> lista = persistencia.getListaAnuncios();
        
        if(adv.getMultimedia().length < 3)
            return null;
        
        if (adv.isOnline()) {
            for (Anuncio a : lista) {
                if (a.getLugar().equals(adv.getLugar())) {
                    a.setOnline(false);
                    persistencia.setAnuncio(a);                 
                }
            }
        }
        
        adv.setAdmin((Administrador)ca.getUsuario());
        persistencia.setAnuncio(adv);    
        return "gestion_anuncios.xhtml";  
    }
}
