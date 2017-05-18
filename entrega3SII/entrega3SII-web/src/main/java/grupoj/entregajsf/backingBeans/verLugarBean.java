/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author migue
 */
@Named(value = "verLugarBean")
@Dependent
public class verLugarBean implements Serializable {

    @Inject
    private PersistenceMock persistencia;

//    No hace falta
//    private List<Lugar> listaLugares;
    @PostConstruct
    public void init() {
        // Se borra
//        listaLugares = persistencia.getListaLugares();
    }

//    Se borran
//    public List<Lugar> getListaLugares() {
//        return listaLugares;
//    }
//    Se borran (Quitando subcontrataciones)
//    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException {
//        //this.listaLugares = listaLugares;
//        persistencia.setListaLugares(listaLugares);
//    }
    /**
     * Develve la imagen del lugar.
     *
     * @return imagen en formato StreamedContent
     */
    public StreamedContent generar() {

        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        try {
//            Lugar lu = new Lugar();
//            lu.setId();
            byte[] fo = persistencia.getLugar(Long.parseLong(params.get("id"))).getFotos();
//          byte[] fo = persistencia.getListaLugares().get(persistencia.getListaLugares().indexOf(lu)).getFotos();
            con = new DefaultStreamedContent(new ByteArrayInputStream(fo));

        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println("error en generar foto lugar");
        } catch (NumberFormatException ne) {
            System.err.println("error en generar foto lugar");
        }

        return con;
    }

    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        return "edit_lugar.xhtml?id=" + params.get("id");
    }

    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        return "verLugar.xhtml?id=" + params.get("id");
    }

    public String viajarE() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        return "eliminarLugar.xhtml?id=" + params.get("id");
    }

}
