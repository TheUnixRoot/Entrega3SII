/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;


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
    @Inject
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
        if(tags == null) {
            tags = new ArrayList<>();
            this.formulario.setForm_tags(tags);
        }
        
        // Actualizar formulario y usuario
        persistencia.setFormulario(this.formulario);
        persistencia.setUsuario(this.user);
        
        this.selectedGustos = new String[10];
        int i = 0;
        for(Tag g : tags) {
            this.selectedGustos[i] = g.getTexto();
            i++;
        }
        gustos = new ArrayList<>();
        List<Tag> lgustos = persistencia.getListaTags();
        for(Tag g : lgustos) {
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
    
    public String saveGustos(){       
        
//        long i = 0;
        tags = new ArrayList<>();
        for(String gusto : this.selectedGustos){
            // buscas el tag con el mismo text que gusto
            // todo 
            List<Tag> lts = persistencia.getListaTags();
            Tag tg = new Tag();
            int j = 0;
            while (tg.getTexto().equals(gusto)) {
                tg = lts.get(j);
                j ++;
            }
            Tag tag = persistencia.getTag(tg.getId()); // where tg es ek tag buscado con el mismo texto
//            Tag tag= new Tag();
//            tag.setId(System.currentTimeMillis());
//            tag.setTexto(gusto);
//            tag.setForm(this.formulario);
            List<Formulario> fl = tag.getForm();
            if(fl == null) {
                //todo
                // la creas nueva y la metes en el tag
                fl = new ArrayList();
                tag.setForm(fl);
            }
            fl.add(formulario);
            tags.add(tag);
            persistencia.setTag(tag);
//            i++;
        }
        this.formulario.setForm_tags(tags);
        formulario.setUsuario(this.user);
        this.user.setForm(this.formulario);
        persistencia.setFormulario(this.formulario);
        persistencia.setUsuario(user);
        FacesContext.getCurrentInstance()
            .addMessage("login:growlmensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos enviados correctamente", "Datos enviados correctamente"));
         return null;
    }
}