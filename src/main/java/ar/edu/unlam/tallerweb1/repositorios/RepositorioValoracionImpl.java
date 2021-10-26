package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;
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
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Alquiler.class)
                .add(Restrictions.eq("id_cliente",clienteID))
                .list();
    }

    @Override
    public Auto obtenerAutoPorId(Long autoID) {
        return this.sessionFactory.getCurrentSession().get(Auto.class, autoID);
    }

    @Override
    public void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto) {
       ValoracionAuto valoracionAuto =new ValoracionAuto(cantidadEstrellas,comentarioAuto,auto);
       this.sessionFactory.getCurrentSession()
               .save(valoracionAuto);
    }


    @Override
    public List<ValoracionAuto> obtenerValoracionesAuto(Auto auto){
        return this.sessionFactory.getCurrentSession()
                .createCriteria(ValoracionAuto.class)
                .add(Restrictions.eq("auto",auto))
                .list();
    }

}



