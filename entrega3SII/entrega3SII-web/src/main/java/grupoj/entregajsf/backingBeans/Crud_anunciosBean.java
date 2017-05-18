/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "crud_anunciosBean")
@RequestScoped
public class Crud_anunciosBean {

    @Inject
    private PersistenceMock persistencia;

    public List<Anuncio> getAnuncios() {
        return persistencia.getListaAnuncios();
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        try {
            this.persistencia.setListaAnuncios(anuncios);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crud_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene la url destino tras comprobar los parametros del contexto, segun
     * la id del anuncio.
     *
     * @return Devuelve una url de vista y escritura para el anuncio solicitado.
     */
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        return "edit_anuncio.xhtml?id=" + params.get("id");
    }

    /**
     * Dado un anuncio correcto, se extrae su byte[] multimedia y se genera la
     * imagen para ser mostrada.
     *
     * @param adv Anuncio del que generar la imagen
     * @return Imagen del anuncio
     */
    public StreamedContent generar(Anuncio adv) {
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }

    /**
     * Publica en la web el anuncio proporcionado por el parametro de contexto
     * id, si hubiera uno, lo pone offline y el clickeado en online
     *
     * @throws NumberFormatException si el id de los parametros no es un numero
     * @return null, para permanecer en la pagina actual
     */
    public String publicar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Anuncio anun = persistencia.getAnuncio(Long.parseLong(params.get("id")));
//                new Anuncio();
//        anun.setId(Long.parseLong(params.get("id")));
        List<Anuncio> lista = persistencia.getListaAnuncios();
//        int idx = lista.indexOf(anun);
//        anun = lista.get(idx);
//        System.out.println(anun.getId());
        for (Anuncio a : lista) {
            if (a.getLugar().equals(anun.getLugar())) {
                a.setOnline(false);
                try {
                    persistencia.setAnuncio(a);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crud_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        anun.setOnline(true);
        try {
            persistencia.setAnuncio(anun);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crud_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        lista.set(idx, anun);
//        try {
//            persistencia.setListaAnuncios(lista);
//        } catch (InterruptedException ex) {
//            System.err.println("Error al publicar el anuncio " + ex.getMessage());
//        }
        return null;
    }

}
