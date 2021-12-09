package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioDevolucion")
public class RepositorioDevolucionImpl implements RepositorioDevolucion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDevolucionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario) {
        return sessionFactory.getCurrentSession().createCriteria(Alquiler.class).add(Restrictions.eq("usuario", usuario)).add(Restrictions.eq("estado", Estado.ACTIVO)).list();
    }

    /*
        @Override
        public Alquiler obtenerAlquilerPorId(Long alquilerID) {
            return sessionFactory.getCurrentSession().get(Alquiler.class, alquilerID);
        }
    */
    @Override
    public void updateAlquiler(Alquiler alquiler) {
        sessionFactory.getCurrentSession().update(alquiler.getAuto());
        sessionFactory.getCurrentSession().update(alquiler);
    }

    @Override
    public void finalizarAlquilerCliente(Alquiler alquiler, Solicitud solAlquilerModificado, Auto auto) {
        //Auto auto = alquiler.getAuto();
        //sessionFactory.getCurrentSession().update(auto);
        sessionFactory.getCurrentSession().merge(auto);
        // sessionFactory.getCurrentSession().update(alquiler); //ACA ESTA EL PROBLEMA
        sessionFactory.getCurrentSession().merge(alquiler);
        sessionFactory.getCurrentSession().update(solAlquilerModificado);
    }

    @Override
    public Suscripcion obtenerSuscripcionDeUnUsuario(Usuario usuario) {
        return (Suscripcion) sessionFactory.getCurrentSession().createCriteria(Suscripcion.class).add(Restrictions.eq("Usuario", usuario)).uniqueResult();
    }

    @Override
    public void updateAuto(Auto auto) {
        sessionFactory.getCurrentSession().update(auto);
    }


}