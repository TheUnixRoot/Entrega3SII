/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author JesusAlberto
 */
@ManagedBean
public class formularioGustosBean {

    //private PersistenceMock persistencia;
    private String[] selectedGustos;
    private List<String> gustos;
    private Usuario user;
    private List<Tag> tags;
    private Formulario formulario;

    @Inject
    private ControlAutorizacion control;
    @EJB
    private PersistenceMock persistencia;

    public formularioGustosBean() {

        // this.persistencia = new PersistenceMock();
    }

    @PostConstruct
    public void init() {
        this.user = control.getUsuario();
        Formulario f = this.user.getForm();
        if (f == null) {
            f = new Formulario();
            f.setId(System.currentTimeMillis());
            List<Evento> h = new ArrayList<>();
            f.setHistorialEventos(h);
            f.setUsuario(user);
            this.user.setForm(f);
        }
        this.formulario = this.user.getForm();
        this.tags = formulario.getForm_tags();
        if (tags == null) {
            tags = new ArrayList<>();
            this.formulario.setForm_tags(tags);
        }

//        try {
//            // Actualizar formulario y usuario
            persistencia.setFormulario(this.formulario);
            persistencia.setUsuario(this.user);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(formularioGustosBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

        this.selectedGustos = new String[10];
        int i = 0;
        for (Tag g : tags) {
            this.selectedGustos[i] = g.getTexto();
            i++;
        }
        gustos = new ArrayList<>();
        List<Tag> lgustos = persistencia.getListaTags();
        for (Tag g : lgustos) {
            gustos.add(g.getTexto());
        }
    }

    public String[] getSelectedGustos() {
        return selectedGustos;
    }

    public void setSelectedGustos(String[] selectedGustos) {
        this.selectedGustos = selectedGustos;
    }

    public List<String> getGustos() {
        return gustos;
    }

    public String saveGustos() {

//        long i = 0;
        tags = new ArrayList<>();
        // borro el formulario de los tags que contiene y los tags del formulario
        for (Tag tagg : formulario.getForm_tags()) {
            List<Formulario> formlst = tagg.getForm();
            if(formlst != null) 
                formlst.remove(formulario);
            else
                tagg.setForm(new ArrayList<Formulario>());
        }
        formulario.setForm_tags(null);
        
        // relleno el formulario con los nuevos tags
        for (String gusto : this.selectedGustos) {
            // buscas el tag con el mismo text que gusto
            // todo 
            List<Tag> lts = persistencia.getListaTags();
            Tag tg = lts.get(0);
            int j = 1;
            while (!tg.getTexto().equals(gusto)) {
                tg = lts.get(j);
                j++;
            }
            tg = persistencia.getTag(tg.getId()); // where tg es ek tag buscado con el mismo texto
//            Tag tag= new Tag();
//            tag.setId(System.currentTimeMillis());
//            tag.setTexto(gusto);
//            tag.setForm(this.formulario);
            List<Formulario> fl = tg.getForm();
            if (fl == null) {
                //todo
                // la creas nueva y la metes en el tag
                fl = new ArrayList();
            }
            if(fl.contains(formulario)) {
                
            } else 
                fl.add(formulario);
            tg.setForm(fl);
//            try {
                persistencia.setTag(tg);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(formularioGustosBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            i++;
            tags.add(tg);
        }
        this.formulario.setForm_tags(tags);
        formulario.setUsuario(this.user);
        this.user.setForm(this.formulario);
//        try {
            persistencia.setFormulario(this.formulario);
            persistencia.setUsuario(user);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(formularioGustosBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        FacesContext.getCurrentInstance()
                .addMessage("login:growlmensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos enviados correctamente", "Datos enviados correctamente"));
        return null;
    }
}
