package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Auto buscarAutoPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Auto.class, id);
    }

    @Override
    public Alquiler buscarAlquilerPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Alquiler.class, id);
    }

}
