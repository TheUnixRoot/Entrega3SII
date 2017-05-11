/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author juanp
 */
@Named(value = "navigationBean")
@RequestScoped
public class NavigationBean {

    /**
     * Creates a new instance of NavigationBean
     */
    public NavigationBean() {
    }
    
    /**
     * Metodo que completa la navegabilidad del menu lateral para 
     * optimizar la vista
     * @param str
     * @return 
     */
    public String goTo(String str) {
        return str;
    }
}
