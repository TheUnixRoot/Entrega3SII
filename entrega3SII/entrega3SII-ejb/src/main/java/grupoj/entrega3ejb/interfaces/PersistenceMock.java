/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entrega3ejb.interfaces;

<<<<<<< HEAD
import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Anuncio;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import grupoj.prentrega1.Valoracion_lug;
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

    public List<Periodista> getListaPeriodistas();

    public void setListaPeriodistas(List<Periodista> listaPeriodistas);

    public List<Administrador> getListaAdministradores();

    public void setListaAdministradores(List<Administrador> listaAdministradores);

    public List<Evento> getListaEventos();

    public void setListaEventos(List<Evento> listaEventos);

    public List<Lugar> getListaLugares();

    public void setListaLugares(List<Lugar> listaLugares);

    public List<Tag> getListaTags();

    public void setListaTags(List<Tag> listaTags);

    public List<Anuncio> getListaAnuncios();

    public void setListaAnuncios(List<Anuncio> listaAnuncios);

    public List<Mensaje> getListaMensajes();

    public void setListaMensajes(List<Mensaje> listaMensajes);

    public List<Valoracion_eve> getListaValoracion_eves();

    public void setListaValoracion_eves(List<Valoracion_eve> listaValoracion_eves);

    public List<Valoracion_lug> getListaValoracion_lugs();

    public void setListaValoracion_lugs(List<Valoracion_lug> listaValoracion_lugs);

    public List<Notificacion> getListaNotificaciones();

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones);

    public List<Geolocalizacion> getListaGeolocalizaciones();

    public void setListaGeolocalizaciones(List<Geolocalizacion> listaGeolocalizaciones);

    public List<Formulario> getListaFormularios();

    public void setListaFormularios(List<Formulario> listaFormularios);

    /**
     * Inserta o actualiza un usuario en el contexto de persistencia.
     *
     * @param usr Entidad Usuario en cuestion
     */
    public void setUsuario(Usuario usr);

    /**
     * Devuelve del contexto de persistencia el usuario pedido
     *
     * @param id Id del Usuario requerido
     * @return Usuario solicitado o null si no existiese
     */
    public Usuario getUsuario(Long id);

    /**
     * Inserta o actualiza un periodista en el contexto de persistencia.
     *
     * @param per Entidad Periodista en cuestion
     */
    public void setPeriodista(Periodista per);

    /**
     * Devuelve del contexto de persistencia el periodista pedido
     *
     * @param id Id del periodista requerido
     * @return periodista solicitado o null si no existiese
     */
    public Periodista getPeriodista(Long id);

    /**
     * Inserta o actualiza un administrador en el contexto de persistencia.
     *
     * @param adm Entidad Administrador en cuestion
     */
    public void setAdministrador(Administrador adm);

    /**
     * Devuelve del contexto de persistencia el administrador pedido
     *
     * @param id Id del administrador requerido
     * @return administrador solicitado o null si no existiese
     */
    public Administrador getAdministrador(Long id);

    /**
     * Inserta o actualiza un evento en el contexto de persistencia.
     *
     * @param eve Entidad Evento en cuestion
     */
    public void setEvento(Evento eve);

    /**
     * Devuelve del contexto de persistencia el evento pedido
     *
     * @param id Id del Evento requerido
     * @return Evento solicitado o null si no existiese
     */
    public Evento getEvento(Long id);

    /**
     * Inserta o actualiza un lugar en el contexto de persistencia.
     *
     * @param lug Entidad Lugar en cuestion
     */
    public void setLugar(Lugar lug);

    /**
     * Devuelve del contexto de persistencia el lugar pedido
     *
     * @param id Id del Lugar requerido
     * @return Lugar solicitado o null si no existiese
     */
    public Lugar getLugar(Long id);

    /**
     * Inserta o actualiza un tag en el contexto de persistencia.
     *
     * @param tg Entidad Tag en cuestion
     */
    public void setTag(Tag tg);

    /**
     * Devuelve del contexto de persistencia el tag pedido
     *
     * @param id Id del Tag requerido
     * @return Tag solicitado o null si no existiese
     */
    public Tag getTag(Long id);

    /**
     * Inserta o actualiza una valoracion_eve en el contexto de persistencia.
     *
     * @param veve Entidad Valoracion_eve en cuestion
     */
    public void setValoracion_eve(Valoracion_eve veve);

    /**
     * Devuelve del contexto de persistencia la Valoracion_eve pedido
     *
     * @param id Id de la Valoracion_eve requerido
     * @return Valoracion_eve solicitada o null si no existiese
     */
    public Valoracion_eve getValoracion_eve(Long id);

    /**
     * Inserta o actualiza una valoracion_lug en el contexto de persistencia.
     *
     * @param vlug Entidad Valoracion_lug en cuestion
     */
    public void setValoracion_lug(Valoracion_lug vlug);

    /**
     * Devuelve del contexto de persistencia la Valoracion_lug pedido
     *
     * @param id Id de la Valoracion_lug requerido
     * @return Valoracion_lug solicitada o null si no existiese
     */
    public Valoracion_lug getValoracion_lug(Long id);

    /**
     * Inserta o actualiza un mensaje en el contexto de persistencia.
     *
     * @param msg Entidad Mensaje en cuestion
     */
    public void setMensaje(Mensaje msg);

    /**
     * Devuelve del contexto de persistencia el mensaje pedido
     *
     * @param id Id del mensaje requerido
     * @return mensaje solicitado o null si no existiese
     */
    public Mensaje getMensaje(Long id);

    /**
     * Inserta o actualiza una notificacion en el contexto de persistencia.
     *
     * @param ntf Entidad Notificacion en cuestion
     */
    public void setNotificacion(Notificacion ntf);

    /**
     * Devuelve del contexto de persistencia la notificacion pedida
     *
     * @param id Id de la notificacion requerida
     * @return notificacion solicitada o null si no existiese
     */
    public Notificacion getNotificacion(Long id);

    /**
     * Inserta o actualiza una geolocalizacion en el contexto de persistencia.
     *
     * @param geo Entidad Geolocalizacion en cuestion
     */
    public void setGeolocaclizacion(Geolocalizacion geo);

    /**
     * Devuelve del contexto de persistencia la geolocalizacion pedida
     *
     * @param id Id de la geolocalizacion requerida
     * @return geolocalizacion solicitada o null si no existiese
     */
    public Geolocalizacion getGeolocalizacion(Long id);

    /**
     * Inserta o actualiza un formulario en el contexto de persistencia.
     *
     * @param frm Entidad Formulario en cuestion
     */
    public void setFormulario(Formulario frm);

    /**
     * Devuelve del contexto de persistencia el formulario pedido
     *
     * @param id Id del formulario requerido
     * @return formulario solicitado o null si no existiese
     */
    public Formulario getFormulario(Long id);

    /**
     * Inserta o actualiza un anuncio en el contexto de persistencia.
     *
     * @param adv Entidad Anuncio en cuestion
     */
    public void setAnuncio(Anuncio adv);

    /**
     * Devuelve del contexto de persistencia el anuncio pedido
     *
     * @param id Id del anuncio requerido
     * @return anuncio solicitado o null si no existiese
     */
    public Anuncio getAnuncio(Long id);

}
