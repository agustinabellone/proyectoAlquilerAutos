package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDevolucion")
public class RepositorioDevolucionImpl implements RepositorioDevolucion{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDevolucionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Alquiler obtenerAlquilerActivoDeCliente(Long clienteID) {
        return (Alquiler) sessionFactory.getCurrentSession().createCriteria(Alquiler.class)
                .add(Restrictions.eq("cliente", clienteID))
                .add(Restrictions.eq("estado", Estado.ACTIVO)).uniqueResult();
    }

    @Override
    public Alquiler obtenerAlquilerPorId(Long alquilerID) {
        return sessionFactory.getCurrentSession().get(Alquiler.class, alquilerID);
    }

    @Override
    public void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler) {
        sessionFactory.getCurrentSession().update(alquiler); //A PROBAR, PUEDE SALIR MAL
    }

    @Override
    public void finalizarAlquilerCliente(Alquiler alquiler) {
        sessionFactory.getCurrentSession().update(alquiler); //A PROBAR, PUEDE SALIR MAL
    }


}