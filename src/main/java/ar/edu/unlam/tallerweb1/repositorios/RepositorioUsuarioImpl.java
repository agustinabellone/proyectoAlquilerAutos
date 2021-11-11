package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Session;
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
    public void eliminarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        this.sessionFactory.getCurrentSession().delete(usuario);
    }

    @Override
    public void actualizarUsuario(Long id_usuario, String nombre, String contraseña) {
        Usuario usuario = buscarPorId(id_usuario);
        usuario.setNombre(nombre);
        usuario.setClave(contraseña);
        this.sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Usuario usuario) {

        return sessionFactory.getCurrentSession().createCriteria(Solicitud.class).
                add(Restrictions.eq("encargado", usuario))
                .add(Restrictions.eq("estadoSolicitud", EstadoSolicitud.PENDIENTE)).list();
    }


}
