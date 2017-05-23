/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entrega3ejb.implementaciones;

import grupoj.entrega3ejb.interfaces.PersistenceMock;
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
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanp
 */
@Local(PersistenceMock.class)
@Stateless
public class NegocioEJB implements Serializable, PersistenceMock {
    
    @PersistenceContext(unitName = "grupoj_pu1.0")
    private EntityManager em;
    
    public NegocioEJB() {
        
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Usuario> q = em.createQuery("select u from Usuario u", Usuario.class);
        return q.getResultList();
    }

    @Override
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaUsuarios.forEach((u) -> {
            if(em.find(Usuario.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Periodista> getListaPeriodistas() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Periodista> q = em.createQuery("select u from Periodista u", Periodista.class);
        return q.getResultList();
    }

    @Override
    public void setListaPeriodistas(List<Periodista> listaPeriodistas) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaPeriodistas.forEach((u) -> {
            if(em.find(Periodista.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Administrador> getListaAdministradores() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Administrador> q = em.createQuery("select u from Administrador u", Administrador.class);
        return q.getResultList();
    }

    @Override
    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaAdministradores.forEach((u) -> {
            if(em.find(Administrador.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Evento> getListaEventos() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Evento> q = em.createQuery("select u from Evento u", Evento.class);
        return q.getResultList();
    }

    @Override
    public void setListaEventos(List<Evento> listaEventos) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaEventos.forEach((u) -> {
            if(em.find(Evento.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Lugar> getListaLugares() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Lugar> q = em.createQuery("select u from Lugar u", Lugar.class);
        return q.getResultList();
    }

    @Override
    public void setListaLugares(List<Lugar> listaLugares) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaLugares.forEach((u) -> {
            if(em.find(Lugar.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Tag> getListaTags() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Tag> q = em.createQuery("select u from Tag u", Tag.class);
        return q.getResultList();
    }

    @Override
    public void setListaTags(List<Tag> listaTags) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaTags.forEach((u) -> {
            if(em.find(Tag.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Anuncio> getListaAnuncios() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Anuncio> q = em.createQuery("select u from Anuncio u", Anuncio.class);
        return q.getResultList();
    }

    @Override
    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaAnuncios.forEach((u) -> {
            if(em.find(Anuncio.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Mensaje> getListaMensajes() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Mensaje> q = em.createQuery("select u from Mensaje u", Mensaje.class);
        return q.getResultList();
    }

    @Override
    public void setListaMensajes(List<Mensaje> listaMensajes) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaMensajes.forEach((u) -> {
            if(em.find(Mensaje.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Valoracion_eve> getListaValoracion_eves() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Valoracion_eve> q = em.createQuery("select u from Valoracion_eve u", Valoracion_eve.class);
        return q.getResultList();
    }

    @Override
    public void setListaValoracion_eves(List<Valoracion_eve> listaValoracion_eves) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaValoracion_eves.forEach((u) -> {
            if(em.find(Valoracion_eve.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Valoracion_lug> getListaValoracion_lugs() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Valoracion_lug> q = em.createQuery("select u from Valoracion_lug u", Valoracion_lug.class);
        return q.getResultList();
    }

    @Override
    public void setListaValoracion_lugs(List<Valoracion_lug> listaValoracion_lugs) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaValoracion_lugs.forEach((u) -> {
            if(em.find(Valoracion_lug.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Notificacion> getListaNotificaciones() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Notificacion> q = em.createQuery("select u from Notificacion u", Notificacion.class);
        return q.getResultList();
    }

    @Override
    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaNotificaciones.forEach((u) -> {
            if(em.find(Notificacion.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Geolocalizacion> getListaGeolocalizaciones() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Geolocalizacion> q = em.createQuery("select u from Geolocalizacion u", Geolocalizacion.class);
        return q.getResultList();
    }

    @Override
    public void setListaGeolocalizaciones(List<Geolocalizacion> listaGeolocalizaciones) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaGeolocalizaciones.forEach((u) -> {
            if(em.find(Geolocalizacion.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public List<Formulario> getListaFormularios() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TypedQuery<Formulario> q = em.createQuery("select u from Formulario u", Formulario.class);
        return q.getResultList();
    }

    @Override
    public void setListaFormularios(List<Formulario> listaFormularios) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        listaFormularios.forEach((u) -> {
            if(em.find(Formulario.class, u.getId()) != null) {
                em.merge(u);
            } else {
                em.persist(u);
            }
        });
    }

    @Override
    public void setUsuario(Usuario usr) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaUsuarios().contains(usr))
            em.merge(usr);
        else
            em.persist(usr);
    }

    @Override
    public Usuario getUsuario(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Usuario.class, id);
    }

    @Override
    public void setPeriodista(Periodista per) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaPeriodistas().contains(per))
            em.merge(per);
        else
            em.persist(per);
    }

    @Override
    public Periodista getPeriodista(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Periodista.class, id);
    }

    @Override
    public void setAdministrador(Administrador adm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaAdministradores().contains(adm))
            em.merge(adm);
        else
            em.persist(adm);
    }

    @Override
    public Administrador getAdministrador(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Administrador.class, id);
    }

    @Override
    public void setEvento(Evento eve) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaEventos().contains(eve))
            em.merge(eve);
        else
            em.persist(eve);
    }

    @Override
    public Evento getEvento(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Evento.class, id);
    }

    @Override
    public void setLugar(Lugar lug) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaLugares().contains(lug))
            em.merge(lug);
        else
            em.persist(lug);
    }

    @Override
    public Lugar getLugar(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Lugar.class, id);
    }

    @Override
    public void setTag(Tag tg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaTags().contains(tg))
            em.merge(tg);
        else
            em.persist(tg);
    }

    @Override
    public Tag getTag(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Tag.class, id);
    }

    @Override
    public void setValoracion_eve(Valoracion_eve veve) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaValoracion_eves().contains(veve))
            em.merge(veve);
        else
            em.persist(veve);
    }

    @Override
    public Valoracion_eve getValoracion_eve(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Valoracion_eve.class, id);
    }

    @Override
    public void setValoracion_lug(Valoracion_lug vlug) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaValoracion_lugs().contains(vlug))
            em.merge(vlug);
        else
            em.persist(vlug);
    }

    @Override
    public Valoracion_lug getValoracion_lug(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Valoracion_lug.class, id);
    }

    @Override
    public void setMensaje(Mensaje msg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaMensajes().contains(msg))
            em.merge(msg);
        else
            em.persist(msg);
    }

    @Override
    public Mensaje getMensaje(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Mensaje.class, id);
    }

    @Override
    public void setNotificacion(Notificacion ntf) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaNotificaciones().contains(ntf))
            em.merge(ntf);
        else
            em.persist(ntf);
    }

    @Override
    public Notificacion getNotificacion(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Notificacion.class, id);
    }

    @Override
    public void setGeolocaclizacion(Geolocalizacion geo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaGeolocalizaciones().contains(geo))
            em.merge(geo);
        else
            em.persist(geo);
    }

    @Override
    public Geolocalizacion getGeolocalizacion(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Geolocalizacion.class, id);
    }

    @Override
    public void setFormulario(Formulario frm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaFormularios().contains(frm))
            em.merge(frm);
        else
            em.persist(frm);
    }

    @Override
    public Formulario getFormulario(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Formulario.class, id);
    }

    @Override
    public void setAnuncio(Anuncio adv) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(getListaAnuncios().contains(adv))
            em.merge(adv);
        else
            em.persist(adv);
    }

    @Override
    public Anuncio getAnuncio(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Anuncio.class, id);
    }

}
