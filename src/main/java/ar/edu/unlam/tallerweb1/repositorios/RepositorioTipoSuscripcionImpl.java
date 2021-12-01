package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RepositorioTipoSuscripcion")
public class RepositorioTipoSuscripcionImpl implements RepositorioTipoSuscripcion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioTipoSuscripcionImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public TipoSuscripcion buscarPorId(Long id) {
        return this.sessionFactory.getCurrentSession().get(TipoSuscripcion.class, id);
    }

    @Override
    public TipoSuscripcion guardar(TipoSuscripcion tipoSuscripcion) {
        this.sessionFactory.getCurrentSession().save(tipoSuscripcion);
        return tipoSuscripcion;
    }
}
