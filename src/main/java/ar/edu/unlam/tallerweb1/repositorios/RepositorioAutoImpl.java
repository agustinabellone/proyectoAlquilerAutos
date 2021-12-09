package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("RepositorioAuto")
public class RepositorioAutoImpl implements RepositorioAuto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAutoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Long idDelAuto) {
        sessionFactory.getCurrentSession().save(idDelAuto);
    }

    @Override
    public Auto buscarPor(Long id) {
        return sessionFactory.getCurrentSession().get(Auto.class, id);
    }

    @Override
    public List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class).add(Restrictions.eq("situacion", enMantenimiento)).list();
    }

    @Override
    public Auto enviarAMantenimiento(Long id, Situacion enMantenimiento, LocalDate localDate) {
        Auto buscado = buscarPor(id);
        buscado.setSituacion(enMantenimiento);
        buscado.setFecha_inicio_mantenimiento(localDate);
        sessionFactory.getCurrentSession().update(buscado);
        return sessionFactory.getCurrentSession().get(Auto.class, buscado.getId());
    }

    @Override
    public Revision enviarARevision(Auto aEnviar, Usuario deLaRevision, LocalDate fecha_de_envio) {
        Revision nueva = new Revision();
        aEnviar.setSituacion(Situacion.EN_REVISION);
        sessionFactory.getCurrentSession().update(aEnviar);
        nueva.setAuto(aEnviar);
        nueva.setUsuario(deLaRevision);
        nueva.setFechaInicioRevision(fecha_de_envio);
        nueva.setEstadoRevision(EstadoRevision.ACTIVA);
        sessionFactory.getCurrentSession().save(nueva);
        return nueva;
    }

    @Override
    public List<Auto> obtenerAutosEnRevision() {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class)
                .add(Restrictions.eq("situacion",Situacion.EN_REVISION)).list();
    }

    @Override
    public Revision obtenerRevisionPorAuto(Auto deLaRevision) {
        return (Revision) sessionFactory.getCurrentSession().createCriteria(Revision.class)
                .add(Restrictions.eq("auto", deLaRevision))
                .add(Restrictions.eq("estadoRevision",EstadoRevision.ACTIVA)).uniqueResult();
    }

    @Override
    public Revision actualizarRevision(Revision revision) {
        sessionFactory.getCurrentSession().update(revision);
        return sessionFactory.getCurrentSession().get(Revision.class, revision.getId());
    }

    @Override
    public List<Revision> obtenerRevisionesPorMecanico(Usuario mecanico) {
        return sessionFactory.getCurrentSession().createCriteria(Revision.class).add(Restrictions.eq("mecanico", mecanico)).list();
    }

    @Override
    public Revision obtenerRevisionEstadoYAuto(Auto deLaRevision, EstadoRevision activa) {
        return null;
    }


}
