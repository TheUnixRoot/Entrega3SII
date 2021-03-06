/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "show_anunciosBean")
@RequestScoped
public class Show_anunciosBean {

    @EJB
    private PersistenceMock persistencia;

    /**
     * Create a new instance of Show_anunciosBean
     */
    public Show_anunciosBean() {

    }

    /**
     * Obtiene de persistencia el anuncion online que se ubicará en top
     *
     * @return Imagen del anuncio o null en su defecto
     */
    public StreamedContent getTop() {

        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while (it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("top") && adv.isOnline()) {
                find = true;
            }
        }
        if (!find) {
            System.out.println("NO encuentra anuncio en top");
            return null;
        } else {
            try {
                return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
            } catch (NullPointerException nex) {
                return null;
            }
        }

    }

    /**
     * Obtiene de persistencia el anuncion online que se ubicará en bottom
     *
     * @return Imagen del anuncio o null en su defecto
     */
    public StreamedContent getBottom() {

        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while (it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("bot") && adv.isOnline()) {
                find = true;
            }
        }
        if (!find) {
            return null;
        } else {
            try {
                return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
            } catch (NullPointerException nex) {
                return null;
            }
        }

    }

    /**
     * Obtiene de persistencia el logotipo online que se ubicará en self,
     * tratado como anuncio propio de la aplicacion
     *
     * @return Imagen del logotipo empresarial o null en su defecto
     */
    public StreamedContent getSelf() {

        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while (it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("self") && adv.isOnline()) {
                find = true;
            }
        }
        if (!find) {
            return null;
        } else {
            try {
                return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
            } catch (NullPointerException nex) {
                return null;
            }
        }

    }
}
