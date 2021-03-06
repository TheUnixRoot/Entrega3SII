/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
import grupoj.prentrega1.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//import mockingBeans.PersistenceMock;

/**
 *
 * @author anaes
 */
@Named(value = "buscarEvento")
@RequestScoped
public class buscarEvento {

    private String[] selectedTipoEvento;
    private List<String> tiposEvento;
    private String[] selectedLugares;
    private List<String> lugares;
    private Date fecha1;
    private Date fecha2;
    private int precio1;
    private int precio2;
    @EJB
    private PersistenceMock persistencia;
    @Inject
    private ResultadoBean res;
    private List<Evento> listaEventos;
    private Set<Evento> listaCoincidencias;

    @PostConstruct
    public void init() {
        List<Tag> l = persistencia.getListaTags();

        tiposEvento = new ArrayList<>();
        for (Tag g : l) {
            tiposEvento.add(g.getTexto());
        }

        List<Lugar> lg = persistencia.getListaLugares();

        Set<String> lgSet = new HashSet<>();
        for (Lugar g : lg) {
            lgSet.add(g.getGeolocalizacion().getCiudad());
        }
        lugares = new ArrayList<>();
        for (String g : lgSet) {
            lugares.add(g);
        }
        selectedTipoEvento = new String[50];
        selectedLugares = new String[50];
        fecha1 = null;
        fecha2 = null;
        precio1 = 0;
        precio2 = 0;

        listaEventos = persistencia.getListaEventos();
    }

    public String[] getSelectedTipoEvento() {
        return selectedTipoEvento;
    }

    public void setSelectedTipoEvento(String[] selectedTipoEvento) {
        this.selectedTipoEvento = selectedTipoEvento;
    }

    public List<String> getTiposEvento() {
        return tiposEvento;
    }

    public List<String> getLugares() {
        return lugares;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setSelectedLugares(String[] lugar) {
        this.selectedLugares = lugar;
    }

    public String[] getSelectedLugares() {
        return selectedLugares;
    }

    public int getPrecio1() {
        return precio1;
    }

    public void setPrecio1(int precio1) {
        this.precio1 = precio1;
    }

    public int getPrecio2() {
        return precio2;
    }

    public void setPrecio2(int precio2) {
        this.precio2 = precio2;
    }

    public List<Evento> getListaCoincidencias() {
        return new ArrayList<>(listaCoincidencias);
    }

    public void setListaCoincidencias(List<Evento> listaCoincidencias) {
        this.listaCoincidencias = new HashSet<>(listaCoincidencias);
    }

    /**
     * Realiza una busqueda de los eventos que cumplen alguna de las
     * condiciones, funciona estilo OR, incluyendo todos, en lugar de realizar
     * la interseccion de los requisitos
     *
     * @return Devuelve la pagina resultadoBuscarEvento.xhtml siempre
     */
    public String buscar() {
        boolean tip = this.getSelectedTipoEvento() != null && getSelectedTipoEvento().length > 0,
                // hay tags seleccionados?
                lug = this.getSelectedLugares() != null && getSelectedLugares().length > 0,
                // hay ciudades seleccionadas?
                f1 = fecha1 != null,
                // hay fecha de comienzo fijada?
                f2 = fecha2 != null,
                // hay fecha de fin fijada?
                p2 = precio2 != 0;
        // hay un precio introducido?

//        if (this.getSelectedTipoEvento() != null) {
//            tip = true;
//        }
//        if (this.getSelectedLugares() != null
//                && getSelectedLugares().length > 0) {
//            lug = true;
//        }
//        if (fecha1 != null) {
//            f1 = true;
//        }
//        if (fecha2 != null) {
//            f2 = true;
//        }
//        if (precio2 != 0) {
//            p2 = true;
//        }
        Set<Evento> tipSet = null,
                lugSet = null,
                f1Set = null,
                f2Set = null,
                p2Set = null;
        // Cojo los que estan taggeados por los tags seleccionados
        if (tip) {
//            System.out.println("Tag!");
            tipSet = new HashSet<>();
            for (Evento e : listaEventos) {
                if (e.getTagged_by() != null && !e.getTagged_by().isEmpty()) {
                    int i = 0;
                    boolean coincide = false;
                    while (!coincide && i < selectedTipoEvento.length) {
                        for (Tag t : e.getTagged_by()) {
                            if (t != null && t.getTexto() != null
                                    && t.getTexto().equalsIgnoreCase(selectedTipoEvento[i])) {
                                coincide = true;
                                tipSet.add(e);
                                //System.out.println(e.getNombre() + " coincide con " + selectedTipoEvento[i]);
                            }
                        }
                        i++;
                    }
                }
            }
        }
        if (lug) {
//            System.out.println("Lug!");
            lugSet = new HashSet<>();
            for (Evento e : listaEventos) {
                int i = 0;
                boolean coincide = false;
                while (!coincide && i < selectedLugares.length) {
                    if (e.getOcurre_in().getGeolocalizacion().getCiudad()
                            .equalsIgnoreCase(selectedLugares[i])) {
                        coincide = true;
                        lugSet.add(e);
                    }
                    i++;
                }
            }
        }
        if (f1) {
//            System.out.println("F1!");
            f1Set = new HashSet<>();
            for (Evento e : listaEventos) {
                if (e.getFecha_inicio().before(fecha1) || e.getFecha_inicio().equals(fecha1)) {
                    f1Set.add(e);
                }
            }
        }
        if (f2) {
//            System.out.println("f2!");
            f2Set = new HashSet<>();
            for (Evento e : listaEventos) {
                if (e.getFecha_fin().after(fecha2) || e.getFecha_fin().equals(fecha2)) {
                    f2Set.add(e);
                }
            }
        }
        if (p2) {
//            System.out.println("p2!");
            p2Set = new HashSet<>();
            for (Evento e : listaEventos) {
                if ((e.getPrecio() >= precio1) && (e.getPrecio() <= precio2)) {
                    p2Set.add(e);
                }
            }
        }
        listaCoincidencias = new HashSet<>(persistencia.getListaEventos());

        if (tip) {
            listaCoincidencias.retainAll(tipSet);
        }
        if (lug) {
            listaCoincidencias.retainAll(lugSet);
        }
        if (f1) {
            listaCoincidencias.retainAll(f1Set);
        }
        if (lug) {
            listaCoincidencias.retainAll(lugSet);
        }

        List<Evento> l = new ArrayList<>();
        for (Evento e : listaCoincidencias) {
            if (!e.isBorrado() && e.isValidado()) {
                l.add(e);
            }
        }

        try {
            res.setListaEventos(l);
        } catch (InterruptedException ex) {
            Logger.getLogger(buscarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "resultadoBuscarEvento.xhtml";
    }

    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return "ver_Evento.xhtml?id=" + params.get("id");
    }
}
