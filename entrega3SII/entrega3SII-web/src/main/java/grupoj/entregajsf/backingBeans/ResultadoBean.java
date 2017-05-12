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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author juanp
 */
@Named(value = "resultadoBean")
@SessionScoped
public class ResultadoBean implements Serializable {
    @Inject
    private PersistenceMock persistencia;
    private Semaphore mutex;
    private List<Evento> listaEventos;
    
    public ResultadoBean() {
        mutex = new Semaphore(1);
    }
    
    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) throws InterruptedException{
        for(Evento e :listaEventos) {
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
    
}
