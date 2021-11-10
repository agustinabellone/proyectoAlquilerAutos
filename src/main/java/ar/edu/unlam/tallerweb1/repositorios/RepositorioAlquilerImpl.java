package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
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
    public List<Auto> obtenerAutosDisponibles() {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Auto.class)
                //.add(Restrictions.eq("situacion", Situacion.DISPONIBLE))
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

    @Override
    public List<Alquiler> obtenerAlquileresActivosDeUsuario(Usuario usuario) {
        return sessionFactory.getCurrentSession().createCriteria(Alquiler.class)
                .add(Restrictions.eq("usuario", usuario))
                .add(Restrictions.eq("estado", Estado.ACTIVO)).list();
    }



    @Override
    public List<Auto> buscarAutosAlquilados(Situacion alquilado) {

        return sessionFactory.getCurrentSession().createCriteria(Auto.class).
                add(Restrictions.eq("situacion",alquilado))
                .list();
    }

    @Override
    public Garage obtenerGaragePorId(Long lugar) {
        return sessionFactory.getCurrentSession().get(Garage.class, lugar);
    }

    @Override
    public List<Alquiler> obtenerAlquileresDelAuto(Auto id) {
        return sessionFactory.getCurrentSession().createCriteria(Alquiler.class)
                .add(Restrictions.eq("auto", id))
                .add(Restrictions.eq("estado", Estado.ACTIVO)).list();
    }

}
