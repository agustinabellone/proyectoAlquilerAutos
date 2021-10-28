package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RepositorioAuto")
public class RepositorioAutoImpl implements RepositorioAuto{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAutoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Auto auto) {
        sessionFactory.getCurrentSession().save(auto);
    }

    @Override
    public Auto buscarPor(Long id) {
        return sessionFactory.getCurrentSession().get(Auto.class, id);
    }

    @Override
    public Auto buscarPorModelo(String modelo) {
        final Session session = sessionFactory.getCurrentSession();
        return (Auto) session.createCriteria(Auto.class)
                .add(Restrictions.eq("modelo", modelo))
                .uniqueResult();
    }


    @Override
    public List<Auto> buscarTodos() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Auto.class)
                .list();
    }

    @Override
    public Auto guardarEnMantenimiento(Auto buscado) {
        return null;
    }

    @Override
    public Auto buscarAutoEnMantenimientoPorIdYPorSituacion(Long id, Situacion situacion) {
        return null;
    }
}
