package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RepositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return this.sessionFactory.getCurrentSession().get(Usuario.class, id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        this.sessionFactory.getCurrentSession().save(usuario);
        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public List<Usuario> buscarTodos() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .list();
    }

    @Override
    public Usuario buscarPorEmailYHash(String email, String hash) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("hashCodigo", hash))
                .uniqueResult();
    }


    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setEstado(EstadoUsuario.INACTIVO);
        this.sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public void actualizarUsuario(Long id_usuario, String nombre, String contrase??a) {
        Usuario usuario = buscarPorId(id_usuario);
        usuario.setNombre(nombre);
        usuario.setClave(contrase??a);
        this.sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Usuario usuario) {

        return sessionFactory.getCurrentSession().createCriteria(Solicitud.class).
                add(Restrictions.eq("encargado", usuario))
                .add(Restrictions.eq("estadoSolicitud", EstadoSolicitud.PENDIENTE)).list();
    }

    public List<Usuario> buscarUsuariosPorSuscripcion(Suscripcion suscripcion) {
        return sessionFactory.getCurrentSession().createCriteria(Usuario.class).
                add(Restrictions.eq("usuario", suscripcion.getUsuario())).list();
    }

    @Override

    public List<Notificacion> getNotificacionesPorId(Usuario buscado) {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Notificacion.class)
                .add(Restrictions.eq("Usuario", buscado))
                .list();
    }

    public List<Usuario> buscarUsuariosPorRol(String rol) {
        return sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("rol", rol)).list();
    }

    @Override
    public List<Usuario> buscarUsuariosPendientesDeRol() {
        return sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.like("email", "%@tallerweb%"))
                .add(Restrictions.eq("rol", "empleado")).list();
    }

    @Override
    public void actualizarRol(String rol, Long id_usuario) {
        Usuario buscado = buscarPorId(id_usuario);
        buscado.setRol("mecanico");
        sessionFactory.getCurrentSession().update(buscado);
    }

    @Override
    public void actualizarNotificacion(Long id_noti) {

        Notificacion notiBuscada = sessionFactory.getCurrentSession()
                .load(Notificacion.class, id_noti);

        sessionFactory.getCurrentSession()
                .delete(notiBuscada);
    }

    public void restarPuntaje(int numero, Usuario usuario) {
        int puntajeActual = usuario.getPuntaje();
        usuario.setPuntaje(puntajeActual - numero);
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public void reactivarUsuario(Usuario usuario) {
        usuario.setEstado(EstadoUsuario.ACTIVO);
        this.sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public void guardarNotificacion(Notificacion notificacion) {
        this.sessionFactory.getCurrentSession().save(notificacion);
    }

    @Override
    public void actualizarPuntaje(int puntaje, Usuario usuario) {
        int puntajeActual = usuario.getPuntaje();
        usuario.setPuntaje(puntajeActual + puntaje);
        sessionFactory.getCurrentSession().update(usuario);
    }


}
