/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import grupoj.prentrega1.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author juanp
 */
@Named(value = "persistenceMock")
@ApplicationScoped
public class PersistenceMock implements Serializable {

    private List<Usuario> listaUsuarios;
    private List<Periodista> listaPeriodistas;
    private List<Administrador> listaAdministradores;
    private List<Mensaje> listaMensajes;
    private List<Evento> listaEventos;
    private List<Lugar> listaLugares;
    private List<Tag> listaTags;
    private List<Anuncio> listaAnuncios;
    private List<Valoracion_eve> listaValoracion_eves;
    private List<Valoracion_lug> listaValoracion_lugs;
    private List<Notificacion> listaNotificaciones;
    private List<Geolocalizacion> listaGeolocalizaciones;
    private List<Formulario> listaFormularios;

    private Formulario formulario;
    private List<Tag> listaTags2;

    private Semaphore mutexUsuarios;
    private Semaphore mutexPeriodistas;
    private Semaphore mutexAdministradores;
    private Semaphore mutexEventos;
    private Semaphore mutexLugares;
    private Semaphore mutexTags;
    private Semaphore mutexAnuncios;
    private Semaphore mutexMensajes;
    private Semaphore mutexValoracion_eves;
    private Semaphore mutexValoracion_lugs;
    private Semaphore mutexNotificaciones;
    private Semaphore mutexGeolocalizaciones;
    private Semaphore mutexFormularios;

    /**
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
        listaPeriodistas = new ArrayList<>();
        listaAdministradores = new ArrayList<>();
        listaMensajes = new ArrayList<>();
        listaEventos = new ArrayList<>();
        listaLugares = new ArrayList<>();
        listaTags = new ArrayList<>();
        listaAnuncios = new ArrayList<>();
        listaValoracion_eves = new ArrayList<>();
        listaValoracion_lugs = new ArrayList<>();
        listaGeolocalizaciones = new ArrayList<>();
        listaFormularios = new ArrayList<>();
        listaNotificaciones = new ArrayList<>();

        listaTags2 = new ArrayList<>();

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setTexto("Música");
        tag1.setEventos(new ArrayList<Evento>());
        listaTags.add(tag1);

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setTexto("Teatro");
        tag2.setEventos(new ArrayList<Evento>());
        listaTags.add(tag2);

        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setTexto("Arte");
        tag3.setEventos(new ArrayList<Evento>());
        listaTags.add(tag3);

        Tag tag4 = new Tag();
        tag4.setId(4L);
        tag4.setTexto("Ópera");
        tag4.setEventos(new ArrayList<Evento>());
        listaTags.add(tag4);

        Tag tag5 = new Tag();
        tag5.setId(5L);
        tag5.setTexto("Cine");
        tag5.setEventos(new ArrayList<Evento>());
        listaTags.add(tag5);

        Tag tag6 = new Tag();
        tag6.setId(6L);
        tag6.setTexto("Deportes");
        tag6.setEventos(new ArrayList<Evento>());
        listaTags.add(tag6);

        listaTags2.add(tag1);
        listaTags2.add(tag3);

        formulario = new Formulario();
        formulario.setForm_tags(listaTags);
        formulario.setId(System.currentTimeMillis());
        formulario.setHistorialEventos(new ArrayList<Evento>());
        listaFormularios.add(formulario);

        Usuario usr = new Usuario();
        usr.setId(1L);
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMeInteresa(new ArrayList<Evento>());
        usr.setMsg_send(new ArrayList<Mensaje>());
        try {
            usr.setMultimedia(
                    DropboxController.downloadFile("/usuario.jpeg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        usr.setForm(formulario);
        formulario.setUsuario(usr);
        //usr.setForm(this.formulario);
        usr.setMsg_send(listaMensajes);

        listaUsuarios.add(usr);

        Periodista per = new Periodista();
        per.setId(2L);
        per.setSeccion("Cultura");
        per.setPuesto("Freelance");
        per.setEmail("usuario@periodista.com");
        per.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        per.setPassword("periodista");
        per.setBorrado(false);
        per.setNombre("periodisto");
        per.setMeInteresa(new ArrayList<Evento>());
        per.setMsg_send(new ArrayList<Mensaje>());
        try {
            per.setMultimedia(
                    DropboxController.downloadFile("/peri.jpeg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaUsuarios.add(per);
        listaPeriodistas.add(per);

        Administrador adm = new Administrador();
        adm.setId(3L);
        adm.setIdentificador(1L);
        adm.setSeccion("Cultura");
        adm.setPuesto("Administrador");
        adm.setEmail("usuario@administrador.com");
        adm.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        adm.setPassword("administrador");
        adm.setBorrado(false);
        adm.setNombre("administradorcito");
        adm.setMeInteresa(new ArrayList<Evento>());
        adm.setMsg_send(new ArrayList<Mensaje>());
        try {
            adm.setMultimedia(
                    DropboxController.downloadFile("/lisa.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaUsuarios.add(adm);
        listaPeriodistas.add(adm);
        listaPeriodistas.add(adm);

        Lugar lugar1 = new Lugar();
        lugar1.setId(1L);
        lugar1.setNombre("plazuela");
        lugar1.setDescripcion("Un sitio muy chu-chuli");
        lugar1.setValoraciones_sobre(new ArrayList<Valoracion_lug>());
        Geolocalizacion geo2 = new Geolocalizacion();
        geo2.setId(System.currentTimeMillis() - 1);
        geo2.setCiudad("Torremolinos");
        geo2.setDireccion("Plaza de tol pueblo");
        geo2.setLugar(lugar1);
        lugar1.setGeolocalizacion(geo2);
        listaLugares.add(lugar1);

        Lugar lugar2 = new Lugar();
        lugar2.setId(2L);
        lugar2.setNombre("campo futbol");
        lugar2.setDescripcion("estadio grande");
        lugar2.setValoraciones_sobre(new ArrayList<Valoracion_lug>());
        Geolocalizacion geo1 = new Geolocalizacion();
        geo1.setId(System.currentTimeMillis());
        geo1.setCiudad("Malaga");
        geo1.setDireccion("Bulevar Luis Pasteur, 35, campus de Teatinos, 29071, Malaga");
        geo1.setLugar(lugar2);
        lugar2.setGeolocalizacion(geo1);
        listaLugares.add(lugar2);

        Evento e = new Evento();
        e.setNombre("Feria Málaga");
        e.setBorrado(false);
        e.setValidado(true);
        e.setDescripcion("Feria de malaga 2017");
        e.setPrecio(20);
        e.setDonde_comprar("www.malaga.com");
        List<Tag> lte = new ArrayList<>();
        e.setTagged_by(lte);
        for (Tag tg : listaTags) {
            tg.getEventos().add(e);
            lte.add(tg);
        }
        e.setOcurre_in(lugar1);
        List<Evento> lein = new ArrayList<>();
        lein.add(e);
        lugar1.setOcurren_at(lein);
        e.setId(25L);
        e.setFecha_inicio(new Date());

        e.setFecha_fin(new Date());
        e.setInteresados_at(new ArrayList<Usuario>());
        e.setValoraciones_sobre(new ArrayList<Valoracion_eve>());
        listaEventos.add(e);

        Evento e2 = new Evento();
        e2.setNombre("Concierto de alguien");
        e2.setBorrado(false);
        e2.setValidado(true);
        e2.setDescripcion("Concierto de alguien muy muy famoso");
        e2.setPrecio(50);
        e2.setDonde_comprar("www.antequera.com");
        List<Tag> lte2 = new ArrayList<>();
        e2.setTagged_by(lte2);
        for (Tag tg : listaTags2) {
            tg.getEventos().add(e2);
            lte2.add(tg);
        }
        e2.setOcurre_in(lugar2);
        List<Evento> lein2 = new ArrayList<>();
        lein2.add(e2);
        lugar2.setOcurren_at(lein2);
        e2.setId(5L);
        e2.setFecha_inicio(new Date());

        e2.setFecha_fin(new Date());
        e2.setInteresados_at(new ArrayList<Usuario>());
        e2.setValoraciones_sobre(new ArrayList<Valoracion_eve>());
        listaEventos.add(e2);

        Notificacion ne = new Notificacion();
        ne.setId(System.currentTimeMillis());
        ne.setContenido("Holi");

        adm.setNotificaciones(new ArrayList<Notificacion>());
        adm.getNotificaciones().add(ne);

        /*
        Evento ev = new Evento();
        ev.setId(1L);
        ev.setNombre("Evento 1");
        ev.setBorrado(false);
        ev.setValidado(true);
        ev.setFecha(new Date());
        ev.setOcurre_in(lug);
        listaEventos.add(ev);*/
        Anuncio adv = new Anuncio();
        adv.setId(10L);
        adv.setOnline(true);
        adv.setLugar("top");
        adv.setEmpresa("Aliexpress");
        adv.setAdmin(adm);
        adv.setFecha_public(new Date());
        adv.setDias_contratados(100);
        try {
            adv.setMultimedia(
                    DropboxController.downloadFile("/amazon.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv);

        Anuncio adv2 = new Anuncio();
        adv2.setId(11L);
        adv2.setOnline(true);
        adv2.setLugar("bot");
        adv2.setEmpresa("Aliexpress");
        adv2.setAdmin(adm);
        adv2.setFecha_public(new Date());
        adv2.setDias_contratados(100);
        try {
            adv2.setMultimedia(
                    DropboxController.downloadFile("/imagen1.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }

        listaAnuncios.add(adv2);

        Anuncio adv3 = new Anuncio();
        adv3.setId(13L);
        adv3.setOnline(true);
        adv3.setLugar("self");
        adv3.setEmpresa("SUR");
        adv3.setAdmin(adm);
        adv3.setFecha_public(new Date());
        adv3.setDias_contratados(100);
        try {
            adv3.setMultimedia(
                    DropboxController.downloadFile("/default.jpg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv3);

        Anuncio adv4 = new Anuncio();
        adv4.setId(14L);
        adv4.setOnline(false);
        adv4.setLugar("top");
        adv4.setEmpresa("Razer");
        adv4.setAdmin(adm);
        adv4.setFecha_public(new Date());
        adv4.setDias_contratados(100);
        try {
            adv4.setMultimedia(
                    DropboxController.downloadFile("/Razer1.jpg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv4);

        mutexUsuarios = new Semaphore(1);
        mutexEventos = new Semaphore(1);
        mutexLugares = new Semaphore(1);
        mutexTags = new Semaphore(1);
        mutexAnuncios = new Semaphore(1);
        mutexMensajes = new Semaphore(1);
        mutexValoracion_eves = new Semaphore(1);
        mutexValoracion_lugs = new Semaphore(1);
        mutexPeriodistas = new Semaphore(1);
        mutexAdministradores = new Semaphore(1);
        mutexNotificaciones = new Semaphore(1);
        mutexGeolocalizaciones = new Semaphore(1);
        mutexFormularios = new Semaphore(1);

        System.out.println("Persistencia creada en Singleton");
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) throws InterruptedException {
        mutexUsuarios.acquire();
        this.listaUsuarios = listaUsuarios;
        mutexUsuarios.release();
    }

    public List<Periodista> getListaPeriodistas() {
        return listaPeriodistas;
    }

    public void setListaPeriodistas(List<Periodista> listaPeriodistas) throws InterruptedException {
        mutexPeriodistas.acquire();
        this.listaPeriodistas = listaPeriodistas;
        mutexPeriodistas.release();
    }

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Administrador> listaAdministradores) throws InterruptedException {
        mutexAdministradores.acquire();
        this.listaAdministradores = listaAdministradores;
        mutexAdministradores.release();
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) throws InterruptedException {
        mutexEventos.acquire();
        this.listaEventos = listaEventos;
        mutexEventos.release();
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException {
        mutexLugares.acquire();
        this.listaLugares = listaLugares;
        mutexLugares.release();
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) throws InterruptedException {
        mutexTags.acquire();
        this.listaTags = listaTags;
        mutexTags.release();
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) throws InterruptedException {
        mutexAnuncios.acquire();
        this.listaAnuncios = listaAnuncios;
        mutexAnuncios.release();
    }

    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) throws InterruptedException {
        mutexMensajes.acquire();
        this.listaMensajes = listaMensajes;
        mutexMensajes.release();
    }

    public List<Valoracion_eve> getListaValoracion_eves() {
        return listaValoracion_eves;
    }

    public void setListaValoracion_eves(List<Valoracion_eve> listaValoracion_eves) throws InterruptedException {
        mutexValoracion_eves.acquire();
        this.listaValoracion_eves = listaValoracion_eves;
        mutexValoracion_eves.release();
    }

    public List<Valoracion_lug> getListaValoracion_lugs() {
        return listaValoracion_lugs;
    }

    public void setListaValoracion_lugs(List<Valoracion_lug> listaValoracion_lugs) throws InterruptedException {
        mutexValoracion_lugs.acquire();
        this.listaValoracion_lugs = listaValoracion_lugs;
        mutexValoracion_lugs.release();
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) throws InterruptedException {
        mutexNotificaciones.acquire();
        this.listaNotificaciones = listaNotificaciones;
        mutexNotificaciones.release();
    }

    public List<Geolocalizacion> getListaGeolocalizaciones() {
        return listaGeolocalizaciones;
    }

    public void setListaGeolocalizaciones(List<Geolocalizacion> listaGeolocalizaciones) throws InterruptedException {
        mutexGeolocalizaciones.acquire();
        this.listaGeolocalizaciones = listaGeolocalizaciones;
        mutexGeolocalizaciones.release();
    }

    public List<Formulario> getListaFormularios() {
        return listaFormularios;
    }

    public void setListaFormularios(List<Formulario> listaFormularios) throws InterruptedException {
        mutexFormularios.acquire();
        this.listaFormularios = listaFormularios;
        mutexFormularios.release();
    }

    /**
     * Inserta o actualiza un usuario en el contexto de persistencia.
     *
     * @param usr Entidad Usuario en cuestion
     */
    public void setUsuario(Usuario usr) throws InterruptedException {
        if (this.listaUsuarios.contains(usr)) {
            mutexUsuarios.acquire();
            this.listaUsuarios
                    .set(this.listaUsuarios.indexOf(usr), usr);
            mutexUsuarios.release();
        } else {
            usr.setId(System.currentTimeMillis());
            mutexUsuarios.acquire();
            this.listaUsuarios.add(usr);
            mutexUsuarios.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el usuario pedido
     *
     * @param id Id del Usuario requerido
     * @return Usuario solicitado o null si no existiese
     */
    public Usuario getUsuario(Long id) {
        Usuario usr = new Usuario();
        usr.setId(id);
        if (this.listaUsuarios.contains(usr)) {
            usr = this.listaUsuarios
                    .get(
                            this.listaUsuarios.indexOf(usr)
                    );

            return usr;
        }
        return null;
    }

    /**
     * Inserta o actualiza un periodista en el contexto de persistencia.
     *
     * @param per Entidad Periodista en cuestion
     */
    public void setPeriodista(Periodista per) throws InterruptedException {
        if (this.listaUsuarios.contains(per)) {
            mutexUsuarios.acquire();
            this.listaUsuarios
                    .set(this.listaUsuarios.indexOf(per), per);
            mutexUsuarios.release();
            mutexPeriodistas.acquire();
            this.listaPeriodistas
                    .set(this.listaPeriodistas.indexOf(per), per);
            mutexPeriodistas.release();
        } else {
            per.setId(System.currentTimeMillis());
            mutexUsuarios.acquire();
            this.listaUsuarios.add(per);
            mutexUsuarios.release();
            mutexPeriodistas.acquire();
            this.listaPeriodistas.add(per);
            mutexPeriodistas.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el periodista pedido
     *
     * @param id Id del periodista requerido
     * @return periodista solicitado o null si no existiese
     */
    public Periodista getPeriodista(Long id) {
        Periodista per = new Periodista();
        per.setId(id);
        if (this.listaPeriodistas.contains(per)) {
            per = this.listaPeriodistas
                    .get(
                            this.listaPeriodistas.indexOf(per)
                    );

            return per;
        }
        return null;
    }

    /**
     * Inserta o actualiza un administrador en el contexto de persistencia.
     *
     * @param adm Entidad Administrador en cuestion
     */
    public void setAdministrador(Administrador adm) throws InterruptedException {
        if (this.listaUsuarios.contains(adm)) {
            mutexUsuarios.acquire();
            this.listaUsuarios
                    .set(this.listaUsuarios.indexOf(adm), adm);
            mutexUsuarios.release();
            mutexPeriodistas.acquire();
            this.listaPeriodistas
                    .set(this.listaPeriodistas.indexOf(adm), adm);
            mutexPeriodistas.release();
            mutexAdministradores.acquire();
            this.listaAdministradores
                    .set(this.listaAdministradores.indexOf(adm), adm);
            mutexAdministradores.release();
        } else {
            adm.setId(System.currentTimeMillis());
            mutexUsuarios.acquire();
            this.listaUsuarios.add(adm);
            mutexUsuarios.release();
            mutexPeriodistas.acquire();
            this.listaPeriodistas.add(adm);
            mutexPeriodistas.release();
            mutexAdministradores.acquire();
            this.listaAdministradores.add(adm);
            mutexAdministradores.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el administrador pedido
     *
     * @param id Id del administrador requerido
     * @return administrador solicitado o null si no existiese
     */
    public Administrador getAdministrador(Long id) {
        Administrador adm = new Administrador();
        adm.setId(id);
        if (this.listaAdministradores.contains(adm)) {
            adm = this.listaAdministradores
                    .get(
                            this.listaAdministradores.indexOf(adm)
                    );

            return adm;
        }
        return null;
    }

    /**
     * Inserta o actualiza un evento en el contexto de persistencia.
     *
     * @param eve Entidad Evento en cuestion
     */
    public void setEvento(Evento eve) throws InterruptedException {
        if (this.listaEventos.contains(eve)) {
            mutexEventos.acquire();
            this.listaEventos
                    .set(this.listaEventos.indexOf(eve), eve);
            mutexEventos.release();
        } else {
            eve.setId(System.currentTimeMillis());
            mutexEventos.acquire();
            this.listaEventos.add(eve);
            mutexEventos.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el evento pedido
     *
     * @param id Id del Evento requerido
     * @return Evento solicitado o null si no existiese
     */
    public Evento getEvento(Long id) {
        Evento eve = new Evento();
        eve.setId(id);
        if (this.listaEventos.contains(eve)) {
            eve = this.listaEventos
                    .get(
                            this.listaEventos.indexOf(eve)
                    );

            return eve;
        }
        return null;
    }

    /**
     * Inserta o actualiza un lugar en el contexto de persistencia.
     *
     * @param lug Entidad Lugar en cuestion
     */
    public void setLugar(Lugar lug) throws InterruptedException {
        if (this.listaLugares.contains(lug)) {
            mutexLugares.acquire();
            this.listaLugares
                    .set(this.listaLugares.indexOf(lug), lug);
            mutexLugares.release();
        } else {
            lug.setId(System.currentTimeMillis());
            mutexLugares.acquire();
            this.listaLugares.add(lug);
            mutexLugares.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el lugar pedido
     *
     * @param id Id del Lugar requerido
     * @return Lugar solicitado o null si no existiese
     */
    public Lugar getLugar(Long id) {
        Lugar lug = new Lugar();
        lug.setId(id);
        if (this.listaLugares.contains(lug)) {
            lug = this.listaLugares
                    .get(
                            this.listaLugares.indexOf(lug)
                    );

            return lug;
        }
        return null;
    }

    /**
     * Inserta o actualiza un tag en el contexto de persistencia.
     *
     * @param tg Entidad Tag en cuestion
     */
    public void setTag(Tag tg) throws InterruptedException {
        if (this.listaTags.contains(tg)) {
            mutexTags.acquire();
            this.listaTags
                    .set(this.listaTags.indexOf(tg), tg);
            mutexTags.release();
        } else {
            tg.setId(System.currentTimeMillis());
            mutexTags.acquire();
            this.listaTags.add(tg);
            mutexTags.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el tag pedido
     *
     * @param id Id del Tag requerido
     * @return Tag solicitado o null si no existiese
     */
    public Tag getTag(Long id) {
        Tag tg = new Tag();
        tg.setId(id);
        if (this.listaTags.contains(tg)) {
            tg = this.listaTags
                    .get(
                            this.listaTags.indexOf(tg)
                    );

            return tg;
        }
        return null;
    }

    /**
     * Inserta o actualiza una valoracion_eve en el contexto de persistencia.
     *
     * @param veve Entidad Valoracion_eve en cuestion
     */
    public void setValoracion_eve(Valoracion_eve veve) throws InterruptedException {
        if (this.listaValoracion_eves.contains(veve)) {
            mutexValoracion_eves.acquire();
            this.listaValoracion_eves
                    .set(this.listaValoracion_eves.indexOf(veve), veve);
            mutexValoracion_eves.release();
        } else {
            veve.setId(System.currentTimeMillis());
            mutexValoracion_eves.acquire();
            this.listaValoracion_eves.add(veve);
            mutexValoracion_eves.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia la Valoracion_eve pedido
     *
     * @param id Id de la Valoracion_eve requerido
     * @return Valoracion_eve solicitada o null si no existiese
     */
    public Valoracion_eve getValoracion_eve(Long id) {
        Valoracion_eve veve = new Valoracion_eve();
        veve.setId(id);
        if (this.listaValoracion_eves.contains(veve)) {
            veve = this.listaValoracion_eves
                    .get(
                            this.listaValoracion_eves.indexOf(veve)
                    );

            return veve;
        }
        return null;
    }

    /**
     * Inserta o actualiza una valoracion_lug en el contexto de persistencia.
     *
     * @param vlug Entidad Valoracion_lug en cuestion
     */
    public void setValoracion_lug(Valoracion_lug vlug) throws InterruptedException {
        if (this.listaValoracion_lugs.contains(vlug)) {
            mutexValoracion_lugs.acquire();
            this.listaValoracion_lugs
                    .set(this.listaValoracion_lugs.indexOf(vlug), vlug);
            mutexValoracion_lugs.release();
        } else {
            vlug.setId(System.currentTimeMillis());
            mutexValoracion_lugs.acquire();
            this.listaValoracion_lugs.add(vlug);
            mutexValoracion_lugs.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia la Valoracion_lug pedido
     *
     * @param id Id de la Valoracion_lug requerido
     * @return Valoracion_lug solicitada o null si no existiese
     */
    public Valoracion_lug getValoracion_lug(Long id) {
        Valoracion_lug vlug = new Valoracion_lug();
        vlug.setId(id);
        if (this.listaValoracion_lugs.contains(vlug)) {
            vlug = this.listaValoracion_lugs
                    .get(
                            this.listaValoracion_lugs.indexOf(vlug)
                    );

            return vlug;
        }
        return null;
    }

    /**
     * Inserta o actualiza un mensaje en el contexto de persistencia.
     *
     * @param msg Entidad Mensaje en cuestion
     */
    public void setMensaje(Mensaje msg) throws InterruptedException {
        if (this.listaMensajes.contains(msg)) {
            mutexMensajes.acquire();
            this.listaMensajes
                    .set(this.listaMensajes.indexOf(msg), msg);
            mutexMensajes.release();
        } else {
            msg.setId(System.currentTimeMillis());
            mutexMensajes.acquire();
            this.listaMensajes.add(msg);
            mutexMensajes.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el mensaje pedido
     *
     * @param id Id del mensaje requerido
     * @return mensaje solicitado o null si no existiese
     */
    public Mensaje getMensaje(Long id) {
        Mensaje msg = new Mensaje();
        msg.setId(id);
        if (this.listaMensajes.contains(msg)) {
            msg = this.listaMensajes
                    .get(
                            this.listaMensajes.indexOf(msg)
                    );

            return msg;
        }
        return null;
    }

    /**
     * Inserta o actualiza una notificacion en el contexto de persistencia.
     *
     * @param ntf Entidad Notificacion en cuestion
     */
    public void setNotificacion(Notificacion ntf) throws InterruptedException {
        if (this.listaNotificaciones.contains(ntf)) {
            mutexNotificaciones.acquire();
            this.listaNotificaciones
                    .set(this.listaNotificaciones.indexOf(ntf), ntf);
            mutexNotificaciones.release();
        } else {
            ntf.setId(System.currentTimeMillis());
            mutexNotificaciones.acquire();
            this.listaNotificaciones.add(ntf);
            mutexNotificaciones.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia la notificacion pedida
     *
     * @param id Id de la notificacion requerida
     * @return notificacion solicitada o null si no existiese
     */
    public Notificacion getNotificacion(Long id) {
        Notificacion ntf = new Notificacion();
        ntf.setId(id);
        if (this.listaNotificaciones.contains(ntf)) {
            ntf = this.listaNotificaciones
                    .get(
                            this.listaNotificaciones.indexOf(ntf)
                    );

            return ntf;
        }
        return null;
    }

    /**
     * Inserta o actualiza una geolocalizacion en el contexto de persistencia.
     *
     * @param geo Entidad Geolocalizacion en cuestion
     */
    public void setGeolocaclizacion(Geolocalizacion geo) throws InterruptedException {
        if (this.listaGeolocalizaciones.contains(geo)) {
            mutexGeolocalizaciones.acquire();
            this.listaGeolocalizaciones
                    .set(this.listaGeolocalizaciones.indexOf(geo), geo);
            mutexGeolocalizaciones.release();
        } else {
            geo.setId(System.currentTimeMillis());
            mutexGeolocalizaciones.acquire();
            this.listaGeolocalizaciones.add(geo);
            mutexGeolocalizaciones.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia la geolocalizacion pedida
     *
     * @param id Id de la geolocalizacion requerida
     * @return geolocalizacion solicitada o null si no existiese
     */
    public Geolocalizacion getGeolocalizacion(Long id) {
        Geolocalizacion geo = new Geolocalizacion();
        geo.setId(id);
        if (this.listaGeolocalizaciones.contains(geo)) {
            geo = this.listaGeolocalizaciones
                    .get(
                            this.listaGeolocalizaciones.indexOf(geo)
                    );

            return geo;
        }
        return null;
    }

    /**
     * Inserta o actualiza un formulario en el contexto de persistencia.
     *
     * @param frm Entidad Formulario en cuestion
     */
    public void setFormulario(Formulario frm) throws InterruptedException {
        if (this.listaFormularios.contains(frm)) {
            mutexFormularios.acquire();
            this.listaFormularios
                    .set(this.listaFormularios.indexOf(frm), frm);
            mutexFormularios.release();
        } else {
            frm.setId(System.currentTimeMillis());
            mutexFormularios.acquire();
            this.listaFormularios.add(frm);
            mutexFormularios.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el formulario pedido
     *
     * @param id Id del formulario requerido
     * @return formulario solicitado o null si no existiese
     */
    public Formulario getFormulario(Long id) {
        Formulario frm = new Formulario();
        frm.setId(id);
        if (this.listaFormularios.contains(frm)) {
            frm = this.listaFormularios
                    .get(
                            this.listaFormularios.indexOf(frm)
                    );

            return frm;
        }
        return null;
    }

    /**
     * Inserta o actualiza un anuncio en el contexto de persistencia.
     *
     * @param adv Entidad Anuncio en cuestion
     */
    public void setAnuncio(Anuncio adv) throws InterruptedException {
        if (this.listaAnuncios.contains(adv)) {
            mutexAnuncios.acquire();
            this.listaAnuncios
                    .set(this.listaAnuncios.indexOf(adv), adv);
            mutexAnuncios.release();
        } else {
            adv.setId(System.currentTimeMillis());
            mutexAnuncios.acquire();
            this.listaAnuncios.add(adv);
            mutexAnuncios.release();
        }
    }

    /**
     * Devuelve del contexto de persistencia el anuncio pedido
     *
     * @param id Id del anuncio requerido
     * @return anuncio solicitado o null si no existiese
     */
    public Anuncio getAnuncio(Long id) {
        Anuncio adv = new Anuncio();
        adv.setId(id);
        if (this.listaAnuncios.contains(adv)) {
            adv = this.listaAnuncios
                    .get(
                            this.listaAnuncios.indexOf(adv)
                    );

            return adv;
        }
        return null;
    }
}
