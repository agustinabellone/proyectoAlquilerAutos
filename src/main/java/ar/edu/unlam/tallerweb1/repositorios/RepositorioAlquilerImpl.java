package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RepositorioAlquiler")
public class RepositorioAlquilerImpl implements RepositorioAlquiler{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAlquilerImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Alquiler alquiler) {
        sessionFactory.getCurrentSession().save(alquiler);
    }

    @Override
    public Alquiler buscarAlquilerPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Alquiler.class, id);
    }

    @Override
    public List<Auto> obtenerAutosDisponibles() {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Auto.class)
                .add(Restrictions.eq("estado", "disponible"))
                .list();
    }

    @Override
    public Auto obtenerAutoPorId(Long id_auto) {
        return sessionFactory.getCurrentSession().get(Auto.class, id_auto);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id_usuario) {
        return sessionFactory.getCurrentSession().get(Usuario.class, id_usuario);
    }
}