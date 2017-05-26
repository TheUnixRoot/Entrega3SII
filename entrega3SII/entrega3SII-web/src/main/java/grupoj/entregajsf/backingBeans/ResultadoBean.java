/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author juanp 
 * Platano de canarias
 */
@Named(value = "resultadoBean")
@SessionScoped
public class ResultadoBean implements Serializable {
//    @Inject
//    private PersistenceMock persistencia;

    private Semaphore mutex;
    private List<Evento> listaEventos;

    public ResultadoBean() {
        mutex = new Semaphore(1);
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) throws InterruptedException {
        for (Evento e : listaEventos) {
            System.out.println(e.getNombre());
        }
        mutex.acquire();
        this.listaEventos = listaEventos;
        mutex.release();
    }

    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        return "ver_Evento.xhtml?id=" + params.get("id");
    }

    public String viajarn() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println("pepe: " + params.get("id"));
        return "enviarNotificacion.xhtml?id=" + params.get("id");
    }
}
