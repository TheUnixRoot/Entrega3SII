/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Valoracion_lug;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Migue
 */
@Named(value = "crearLugarBean")
@RequestScoped
public class CrearLugarBean {

    @EJB
    private PersistenceMock persistencia;
    private Lugar l;
    private Geolocalizacion g;
    private UploadedFile file;

    public CrearLugarBean() {
        l = new Lugar();
        g = new Geolocalizacion();
    }

    public long getId() {
        return l.getId();
    }

    public void setId(long id) {
        this.l.setId(id);
    }

    public String getNombre() {
        return l.getNombre();
    }

    public void setNombre(String nombre) {
        l.setNombre(nombre);
    }

    public String getDescripcion() {
        return l.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
        l.setDescripcion(descripcion);
    }

    public boolean isBorrado() {
        return l.isBorrado();
    }

    public void setBorrado(boolean borrado) {
        l.setBorrado(borrado);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        if (file.getContents() != null && file.getContents().length > 0) {
            l.setFotos(file.getContents());
        } else {
            l.setFotos(new byte[1]);
        }
        this.file = file;
    }

    public String getDireccion() {
        return g.getDireccion();
    }

    public void setDireccion(String direccion) {
        g.setDireccion(direccion);
    }

    public String getCiudad() {
        return g.getCiudad();
    }

    public void setCiudad(String ciudad) {
        g.setCiudad(ciudad);
    }

    private boolean existeLugar(String nombre) {

        List<Lugar> lugares = persistencia.getListaLugares();
        boolean b = false;
        int i = 0;
        while (i < lugares.size() && !b) {
            if (lugares.get(i).getNombre().equals(nombre)) {
                b = true;
            }
            i++;
        }
        return b;
    }

    public String insertarLugar() {
//        List<Lugar> lugares = persistencia.getListaLugares();

        if (existeLugar(l.getNombre())) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lugar ya existente en la base de datos", "Lugar ya existente en la base de datos");
            FacesContext.getCurrentInstance().addMessage("mensaje", fm);

            return null;
        }
//        g.setId(System.currentTimeMillis()-4);
//        l.setId(System.currentTimeMillis());
        l.setBorrado(false);

        l.setGeolocalizacion(g);
        g.setLugar(l);
        l.setOcurren_at(new ArrayList<Evento>());
        l.setValoraciones_sobre(new ArrayList<Valoracion_lug>());
        //        lugares.add(l);
//        try {
            persistencia.setLugar(l);
            persistencia.setGeolocaclizacion(g);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CrearLugarBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            persistencia.setListaLugares(lugares);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CrearLugarBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return "index.xhtml";
    }

}
