package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioSolicitudImpl implements RepositorioSolicitud{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSolicitudImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crearSolicitud(Solicitud solicitud) {
        sessionFactory.getCurrentSession().save(solicitud);
    }

    @Override
    public Solicitud obtenerSolicitud(Solicitud solicitud) {
        return (Solicitud) this.sessionFactory.getCurrentSession().createCriteria(Solicitud.class).add(Restrictions.eq("alquiler", solicitud.getAlquiler())).uniqueResult();
    }

    @Override
    public Solicitud obtenerSolicitudPorId(Long solicitudID) {
        return this.sessionFactory.getCurrentSession().get(Solicitud.class, solicitudID);
    }
}
