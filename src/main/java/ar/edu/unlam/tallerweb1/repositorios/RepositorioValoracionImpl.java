package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioValoracion")
public class RepositorioValoracionImpl implements RepositorioValoracion {

    private SessionFactory sessionFactory;


    @Autowired
    public RepositorioValoracionImpl(SessionFactory sessionFactory){

        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Alquiler> obtenerAlquileresHechos(Long clienteID) {
        Usuario usuario=obtenerClientePorId(clienteID);
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Alquiler.class)
                .add(Restrictions.eq("usuario",usuario))
                .list();
    }

    @Override
    public Auto obtenerAutoPorId(Long autoID) {
        return this.sessionFactory.getCurrentSession().get(Auto.class, autoID);
    }

    @Override
    public Usuario obtenerClientePorId(Long autoID) {
        return this.sessionFactory.getCurrentSession().get(Usuario.class, autoID);
    }

    @Override
    public void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto, Alquiler alquiler) {
       ValoracionAuto valoracionAuto =new ValoracionAuto(cantidadEstrellas,comentarioAuto,auto,alquiler);
       this.sessionFactory.getCurrentSession()
               .save(valoracionAuto);
    }


    @Override
    public Alquiler obtenerAlquilerPorId(Long id){
        return this.sessionFactory.getCurrentSession().get(Alquiler.class, id);
    }

    @Override
    public ValoracionAuto obtenerValoracionALquilerAuto(Auto auto, Alquiler alquiler) {
        return (ValoracionAuto) this.sessionFactory.getCurrentSession()
                .createCriteria(ValoracionAuto.class)
                .add(Restrictions.eq("auto",auto))
                .add(Restrictions.eq("alquiler", alquiler))
                .uniqueResult();
    }


    @Override
    public List<ValoracionAuto> obtenerValoracionesAuto(Auto auto){
        return this.sessionFactory.getCurrentSession()
                .createCriteria(ValoracionAuto.class)
                .add(Restrictions.eq("auto",auto))
                .list();
    }

}



