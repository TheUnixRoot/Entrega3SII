/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "mod_anunciosBean")
@Dependent
public class Mod_anunciosBean implements Serializable {

    @Inject
    PersistenceMock persistencia;
    Anuncio adv;
    UploadedFile file;
    StreamedContent mul;

    /**
     * Crea un bean que contiene un anuncio valido, pasado como parametro
     */
    @PostConstruct
    public void init() {
        Map<String, String> mapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        adv = persistencia.getAnuncio(Long.parseLong(mapa.get("id")));
//        adv = persistencia.getListaAnuncios()
//                .get(
//                    persistencia.getListaAnuncios()
//                            .indexOf(adv));
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Anuncio getAdv() {
        return adv;
    }

    public void setAdv(Anuncio adv) {
        this.adv = adv;
    }

    /**
     * Getter por el cual se obtiene la imagen del anuncio para ser mostrada
     *
     * @return Imagen del anuncio
     */
    public StreamedContent getMultimedia() {
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }

    /**
     * Metodo necesario pero no utilizar, es inefectivo
     *
     * @param multimedia No se utilizara
     */
    public void setMultimedia(StreamedContent multimedia) {
        multimedia = null;
    }

    /**
     * Metodo necesario pero no utilizar, es inefectivo
     *
     * @return
     */
    public UploadedFile getMultimedia2() {
        return file;
    }

    /**
     * Setter que encapsula el guardado de una nueva imagen en el anuncio del
     * bean
     *
     * @param multimedia Imagen que reemplazara la que ya tiene el anuncio
     */
    public void setMultimedia2(UploadedFile multimedia) {
        if (multimedia.getContents().length > 0) {
            adv.setMultimedia(multimedia.getContents());
        }
        this.file = multimedia;
    }

    public void setLugar(boolean isArriba) {
        adv.setLugar(isArriba ? "top" : "bot");
    }

    public boolean getLugar() {
        return adv.getLugar().equals("top");
    }

    /**
     * Almacena el anuncio tratado y actualiza el estado de la aplicación,
     * poniendo en linea el anuncio si así se indicase en la vista y sacando de
     * linea el que hubiera en su posicion. No permite sacar de linea a un
     * anuncio, solo si este se reemplaza.
     *
     * @return Vuelve siempre a la pagina gestion_anuncios.xhtml
     */
    public String grabar() {

        List<Anuncio> lista = persistencia.getListaAnuncios();

        if (adv.isOnline()) {
            for (Anuncio a : lista) {
                if (a.getLugar().equals(adv.getLugar())) {
                    a.setOnline(false);
                    try {
                        persistencia.setAnuncio(a);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Mod_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            boolean find = false;
            for (Anuncio a : lista) {
                if (a.isOnline()) {
                    find = true;
                }
            }
            if (!find) {
                adv.setOnline(true);
                FacesContext.getCurrentInstance().addMessage("formu:panel:messages",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede desactivar el anuncio, pruebe activando otro del mismo lugar",
                                "No se puede desactivar el anuncio, pruebe activando otro del mismo lugar"));
            }
        }
//        lista.set((lista.indexOf(adv)), adv);
//        try {
//            System.out.println("Como esta la imagen??? " + adv.getMultimedia().length);
//            persistencia.setListaAnuncios(lista);
//        } catch (InterruptedException ex) {
//            System.err.println("Error al crear anuncio en persistencia " + ex.getMessage());
//        }
        try {
            persistencia.setAnuncio(adv);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mod_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "gestion_anuncios.xhtml";
    }

}
