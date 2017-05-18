/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;

/**
 *
 * @author migue
 */
@Named(value = "eliminarEvento")
@RequestScoped
public class EliminarEvento {

    @Inject
    private PersistenceMock persistencia;
//    private List<Evento> listaEventos;
    private Evento ev;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//       listaEventos = persistencia.getListaEventos();
        ev = persistencia.getEvento(Long.parseLong(params.get("id")));
//       ev = listaEventos.get(
//                persistencia.getListaEventos().indexOf(ev)
//                );
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public String getValor() {
        return ev.isBorrado() ? "si" : "no";
    }

    public void setValor(String valor) {
        ev.setBorrado(valor.equalsIgnoreCase("si"));
    }
//
//    public List<Evento> getListaEventos() {
//        return listaEventos;
//    }
//
//    public void setListaEventos(List<Evento> listaEventos) {
//        this.listaEventos = listaEventos;
//    }

    public Evento getEv() {
        return ev;
    }

    public void setEv(Evento ev) {
        this.ev = ev;
    }

    public String eliminarEvento() {
        FacesContext.getCurrentInstance()
                .addMessage("formu:mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento actualizado", "Evento actualizado"));
        return null;
    }

}
