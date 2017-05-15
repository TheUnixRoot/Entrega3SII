/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entrega3ejb.interfaces;

import grupoj.prentrega1.Anuncio;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author juanp
 */
@Local
public interface PersistenceMock {
    
    public List<Usuario> getListaUsuarios();

    public void setListaUsuarios(List<Usuario> listaUsuarios);

    public List<Evento> getListaEventos();

    public void setListaEventos(List<Evento> listaEventos);

    public List<Mensaje> getListaMensajes();

    public void setListaMensajes(List<Mensaje> listaMensajes);
    
    public List<Anuncio> getListaAnuncios();

    public void setListaAnuncios(List<Anuncio> listaAnuncios);

    public List<Lugar> getListaLugares();

    public void setListaLugares(List<Lugar> listaLugares);

    public List<Tag> getListaTags();

    public void setListaTags(List<Tag> listaTags);
    
}
