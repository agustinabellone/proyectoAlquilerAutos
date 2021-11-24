package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RepositorioGarageImpl implements RepositorioGarage{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioGarageImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Garage> obtenerListadoDeGarages() {
        return sessionFactory.getCurrentSession().createCriteria(Garage.class).list();
    }

    @Override
    public Garage obtenerGaragePorId(Long garageID) {
        return sessionFactory.getCurrentSession().get(Garage.class, garageID);
    }
}
