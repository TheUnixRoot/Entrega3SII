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
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

    private List<Lugar> listaLugares;

    @PostConstruct
    public void init() {
        listaLugares = persistencia.getListaLugares();
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException {
        //this.listaLugares = listaLugares;
        persistencia.setListaLugares(listaLugares);
    }

    public StreamedContent generar() {

        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        try {
            Lugar lu = new Lugar();
            lu.setId(Long.parseLong(params.get("id")));
            byte[] fo = persistencia.getListaLugares().get(persistencia.getListaLugares().indexOf(lu)).getFotos();
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
