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
    public List<Auto> buscarPorModelo(Modelo modelo) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class).
                createAlias("modelo", "modelo")
                .add(Restrictions.eq("modelo.id", modelo.getId())).list();
    }


    @Override
    public List<Auto> buscarTodos() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Auto.class)
                .list();
    }


    @Override
    public List<Auto> buscarPorMarca(Marca marca) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class)
                .createAlias("marca", "marca")
                .add(Restrictions.eq("marca.id", marca.getId())).list();
    }

    @Override
    public List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class)
                .add(Restrictions.eq("situacion", enMantenimiento))
                .list();
    }

    @Override
    public Auto enviarAMantenimiento(Long id, Situacion enMantenimiento) {
        return null;
    }

}
