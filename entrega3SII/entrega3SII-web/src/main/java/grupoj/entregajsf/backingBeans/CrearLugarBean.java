

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import java.util.List;
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
@Named(value = "crearLugarBean")
@RequestScoped
public class CrearLugarBean {

    /**
     * Creates a new instance 
     * 
     * 
     */
    @Inject
    private PersistenceMock persistencia;
    private List<Lugar> lugares;
    
    private String nombre;
    private String descripcion;
    private boolean borrado;
    private byte[] fotos;
    private String direccion;
    private String ciudad;
    UploadedFile file;
    private UIComponent enviar;
    
    //inicializa la lista de lugares.
    @PostConstruct
    public void init() {
      
        lugares = persistencia.getListaLugares();

    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Iterable<Lugar> getLugares() {
        return lugares;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
    }

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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

        //inserta un lugar
    
    public String insertarLugar() throws InterruptedException{
      if(existeLugar(nombre)){

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Lugar ya existente en la base de datos","Lugar ya existente en la base de datos");
        FacesContext.getCurrentInstance().addMessage("mensaje", fm);
      
        return null;
      }
      else{
      
        Lugar l=new Lugar();
        l.setNombre(nombre);
        l.setDescripcion(descripcion);
        l.setBorrado(false);

        Geolocalizacion g = new Geolocalizacion();
        g.setDireccion(direccion);
        g.setCiudad(ciudad);
        l.setGeolocalizacion(g);

       /* if(file == null){
        l.setFotos(new byte[1]);
        }else{
        l.setFotos(file.getContents());
        }*/
        lugares.add(l);
        persistencia.setListaLugares(lugares);

        return "gestion_lugar.xhtml";
      }
     
    }
    
    // comprueba si existe un lugar en la lista.
    private boolean existeLugar(String nombre){
    
    boolean b=false;
    for(Lugar i : lugares){
        if(i.getNombre().equals(nombre)){
        b=true;
        }
    }
    return b;
    }
    


    
    
    
}
