/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Migue
 */
@Named(value = "crearEventoBean")
@RequestScoped
public class CrearEventoBean {

    @Inject
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion cr;
    private List<Lugar> lugares;
    private List<Evento> eventos;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date hora;
    private boolean validado;
    private String descripcion;
    private double precio;
    private String donde_comprar;
    private boolean borrado;
    private String ocurre_in;
    private Usuario subido_by;
    private String borrad;
    private UIComponent enviar;
    private byte[] foto;
    private String tags;

    @PostConstruct
    public void init() {

//        lugares = persistencia.getListaLugares();
//        eventos = persistencia.getListaEventos();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDonde_comprar() {
        return donde_comprar;
    }

    public void setDonde_comprar(String donde_comprar) {
        this.donde_comprar = donde_comprar;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getOcurre_in() {
        return ocurre_in;
    }

    public void setOcurre_in(String ocurre_in) {
        this.ocurre_in = ocurre_in;
    }

    public Usuario getSubido_by() {
        return subido_by;
    }

    public void setSubido_by(Usuario subido_by) {
        this.subido_by = subido_by;
    }
//
//    public List<Lugar> getLugares() {
//        return lugares;
//    }
//
//    public void setLugares(List<Lugar> lugares) {
//        this.lugares = lugares;
//    }

    public UploadedFile getFile() {
        return null;
    }

    public void setFile(UploadedFile file) {
        if (file.getContents().length > 0) {
            this.foto = file.getContents();
        } else {
            this.foto = new byte[1];
        }
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
//
//    public List<Evento> getEventos() {
//        return eventos;
//    }
//
//    public void setEventos(List<Evento> eventos) {
//        this.eventos = eventos;
//    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public UIComponent getEnviar() {
        return enviar;
    }

    public void setEnviar(UIComponent enviar) {
        this.enviar = enviar;
    }

    public String getBorrad() {
        return borrad;
    }

    public void setBorrad(String borrad) {
        this.borrad = borrad;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private boolean existeEvento(String nombre) {

        boolean b = false;
        int i = 0;
        while (i < persistencia.getListaEventos().size() && !b) {
            if (persistencia.getListaEventos().get(i).getNombre().equals(nombre)) {
                b = true;
            }
        }
        return b;
    }

    private Lugar buscarLugar(String o) {

        boolean b = false;
        int i = 0;
        Lugar lg = null;
        while (i < persistencia.getListaLugares().size() && !b) {
            if (persistencia.getListaLugares().get(i).getNombre().equals(o)) {
                b = true;
                lg = persistencia.getListaLugares().get(i);
            }
        }
        return lg;
    }

    public String insertarEvento() throws InterruptedException {
        if (existeEvento(nombre)) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Evento ya existente en la base de datos", "Evento ya existente en la base de datos");
            FacesContext.getCurrentInstance().addMessage("mensaje", fm);

            return null;
        }
        Evento e = new Evento();
        fecha_inicio.setHours(hora.getHours());
        fecha_inicio.setMinutes(hora.getMinutes());
//        e.setId(System.currentTimeMillis());
        e.setNombre(nombre);
        e.setPrecio(precio);
        e.setDonde_comprar(donde_comprar);
        e.setFecha_inicio(fecha_inicio);
        e.setFecha_fin(fecha_fin);
        e.setDescripcion(descripcion);
        e.setOcurre_in(buscarLugar(ocurre_in));

        Lugar lugarEnCuestion = buscarLugar(ocurre_in);
        List<Evento> lugListEv = lugarEnCuestion.getOcurren_at();
        lugListEv.add(e);
        lugarEnCuestion.setOcurren_at(lugListEv);
        
        persistencia.setLugar(lugarEnCuestion);

        e.setBorrado(false);

        List<Tag> tle = new ArrayList<>();
        List<Tag> tln = new ArrayList<>();
        List<Tag> tp = persistencia.getListaTags();

        try (Scanner sc = new Scanner(tags)) {
            sc.useDelimiter("(,( )*)+");
            while (sc.hasNext()) {
                String st = sc.next();
                boolean find = false;
                for (Tag tin : tp) {
                    if (tin.getTexto().equalsIgnoreCase(st)) {
                        tle.add(tin);
                        tin.getEventos().add(e);
                        persistencia.setTag(tin);
                        find = true;
                    }
                }
                if (!find) {
//                    System.out.println(st);
                    Tag t = new Tag();
//                    t.setId(System.currentTimeMillis());
                    t.setTexto(st);
                    List<Evento> let = new ArrayList();
                    let.add(e);
                    t.setEventos(let);
                    tle.add(t);
                    tln.add(t);
                }
            }
            for (Tag g : tln) {
                persistencia.setTag(g);
//                tp.add(g);
            }
        }
        e.setTagged_by(tle);
        e.setMultimedia(foto);
        e.setInteresados_at(new ArrayList<Usuario>());
        e.setValoraciones_sobre(new ArrayList<Valoracion_eve>());
        if (cr.getUsuario() != null) {
            if (cr.isAdministrador() || cr.isPeriodista()) {
                e.setValidado(true);
            } else {
                e.setValidado(false);
            }
            e.setSubido_by(cr.getUsuario());
            List<Evento> l = cr.getUsuario().getSubidas();
            if (l == null) {
                l = new ArrayList<>();
            }
            l.add(e);
            cr.getUsuario().setSubidas(l);
        }
        persistencia.setEvento(e);
        persistencia.setUsuario(cr.getUsuario());
//        eventos.add(e);

//        persistencia.setListaEventos(eventos);
        return "index.xhtml";
    }

}
