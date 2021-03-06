/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Lugar;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author migue
 */
@Named(value = "eliminarLugar")
@RequestScoped
public class EliminarLugar {

    @EJB
    private PersistenceMock persistencia;
//    private String valor;
//    private List<Lugar> listaLugares;
    private Lugar adv;
//    private Lugar a;

    @PostConstruct
    public void init() {

        Map<String, String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        listaLugares = persistencia.getListaLugares();
        adv = persistencia.getLugar(Long.parseLong(p.get("id")));
//        adv = listaLugares.get(persistencia.getListaLugares().indexOf(adv));

    }
//
//    public List<Lugar> getListaLugares() {
//        return listaLugares;
//    }
//
//    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException {
//        //this.listaLugares = listaLugares;
//        persistencia.setListaLugares(listaLugares);
//    }

    public String getValor() {
        return adv.isBorrado() ? "si" : "no";
    }

    public void setValor(String valor) {
        adv.setBorrado(valor.equalsIgnoreCase("si"));
//        this.valor = valor;
    }

    public Lugar getAdv() {
        return adv;
    }

    public void setAdv(Lugar adv) {
        this.adv = adv;
    }

    public String eliminarLugar() {
        persistencia.setLugar(adv);
        FacesContext.getCurrentInstance().addMessage("formula:mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Lugar borrado", "Lugar borrado"));
        return null;
    }

}
