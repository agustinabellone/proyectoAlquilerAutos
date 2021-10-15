package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioDevolucion")
public class RepositorioDevolucionImpl implements RepositorioDevolucion{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDevolucionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Auto finalizarAlquiler(Alquiler alquiler) {
        //sessionFactory.getCurrentSession().update("Alquiler", alquiler);
        return null;
    }

    @Override
    public void guardar(Auto auto) {
      sessionFactory.getCurrentSession().save(auto);
    }

    @Override
    public List<Auto> buscarPorSuEstadoDisponible(boolean verdadero) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class).add(Restrictions.eq("disponible", verdadero)).list();
    }
}