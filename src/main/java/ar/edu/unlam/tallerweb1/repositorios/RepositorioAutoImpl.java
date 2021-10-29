package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("RepositorioAuto")
public class RepositorioAutoImpl implements RepositorioAuto{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAutoImpl(SessionFactory sessionFactory){
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
                createAlias("modelo","modelo")
                .add(Restrictions.eq("modelo.id",modelo.getId())).list();
    }


    @Override
    public List<Auto> buscarTodos() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Auto.class)
                .list();
    }

    @Override
    public Auto guardarEnMantenimiento(Auto buscado, LocalDate localDate) {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setAuto(buscado);
        mantenimiento.setFechaDeEnvio(localDate);
        return (Auto) sessionFactory.getCurrentSession().save(mantenimiento);
    }

    @Override
    public Auto buscarAutoEnMantenimientoPorIdYPorSituacion(Long id, Situacion situacion) {
        return null;
    }

    @Override
    public List<Auto> buscarPorMarca(Marca marca) {
        return sessionFactory.getCurrentSession().createCriteria(Auto.class)
                .createAlias("marca","marca")
                .add(Restrictions.eq("marca.id",marca.getId())).list();

        //return sessionFactory.getCurrentSession().createCriteria(Producto.class)
                //.createAlias("subcategoria", "scBuscada")
                //.add(Restrictions.like("scBuscada.descripcion", productoBusqueda+"%"))
                //.list();
    }
}
