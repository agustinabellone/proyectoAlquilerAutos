package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RepositorioAuto")
public class RepositorioAutoImpl implements RepositorioAuto {

    private SessionFactory sessionFactory;
    private Revision revision = new Revision();
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
        return sessionFactory.getCurrentSession().createCriteria(Auto.class)
                .add(Restrictions.eq("situacion", enMantenimiento))
                .list();
    }

    @Override
    public Auto enviarAMantenimiento(Long id, Situacion enMantenimiento) {
        Auto buscado = buscarPor(id);
        buscado.setSituacion(enMantenimiento);
        sessionFactory.getCurrentSession().update(buscado);
        return sessionFactory.getCurrentSession().get(Auto.class,buscado.getId());
    }


}
