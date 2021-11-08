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
    private RepositorioUsuario repositorioUsuario;
    private RepositorioAlquiler repositorioAlquiler;


    @Autowired
    public RepositorioValoracionImpl(SessionFactory sessionFactory,RepositorioUsuario repositorioUsuario, RepositorioAlquiler repositorioAlquiler){
        this.sessionFactory=sessionFactory;
        this.repositorioUsuario=repositorioUsuario;
        this.repositorioAlquiler=repositorioAlquiler;
    }

    @Override
    public List<Alquiler> obtenerAlquileresHechos(Long clienteID) {
        Usuario usuario=repositorioUsuario.buscarPorId(clienteID);
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Alquiler.class)
                .add(Restrictions.eq("usuario",usuario))
                .list();
    }




    @Override
    public void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto) {
        ValoracionAuto valoracionAuto =new ValoracionAuto(cantidadEstrellas,comentarioAuto,auto);
        this.sessionFactory.getCurrentSession()
                .save(valoracionAuto);
    }


    @Override
    public Alquiler obtenerAlquilerPorId(Long id){
        return this.sessionFactory.getCurrentSession().get(Alquiler.class, id);
    }



    @Override
    public List<ValoracionAuto> obtenerValoracionesAuto(Auto auto){
        return this.sessionFactory.getCurrentSession()
                .createCriteria(ValoracionAuto.class)
                .add(Restrictions.eq("auto",auto))
                .list();
    }

}




