package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioEnviarAutoAMantenimientoImpl implements RepositorioEnviarAutoAMantenimiento {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEnviarAutoAMantenimientoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Auto buscarPor(String patente) {
        final Session session = sessionFactory.getCurrentSession();
        //session.createSQLQuery("SELECT * FROM ");
        return (Auto) session.createCriteria(Auto.class)
                .add(Restrictions.eq("patente", patente))
                .uniqueResult();
    }

    @Override
    public void guardar(Auto enviado) {

    }

    @Override
    public List<Auto> buscarPorModelo(String modelo) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Auto.class)
                .add(Restrictions.eq("modelo", modelo))
                .list();
    }
}
