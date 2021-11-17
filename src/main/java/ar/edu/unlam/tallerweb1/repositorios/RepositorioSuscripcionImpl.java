package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("RepositorioSuscripcion")
public class RepositorioSuscripcionImpl implements RepositorioSuscripcion{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSuscripcionImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Suscripcion guardar(Suscripcion suscripcion) {
        this.sessionFactory.getCurrentSession().save(suscripcion);
        return suscripcion;
    }

    @Override
    public Suscripcion buscarPorId(Long id) {

        return this.sessionFactory.getCurrentSession().get(Suscripcion.class, id);

    }

    @Override
    public List<Suscripcion> buscarPorTipo(TipoSuscripcion tipoSuscripcion) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Suscripcion.class)
                .add(Restrictions.eq("TipoSuscripcion", tipoSuscripcion))
                .list();
    }

    @Override
    public Suscripcion buscarPorUsuario(Usuario usuario) {
        return (Suscripcion)this.sessionFactory.getCurrentSession()
                .createCriteria(Suscripcion.class)
                .add(Restrictions.eq("Usuario", usuario))
                .add(Restrictions.gt("FechaFin", LocalDate.now()))
                .add(Restrictions.le("FechaInicio", LocalDate.now()))
                .add(Restrictions.isNull("FechaFinForzada"))
                .uniqueResult();
    }

    @Override
    public void actualizarSuscripcion(Suscripcion suscripcion) {
        this.sessionFactory.getCurrentSession()
                .update("Suscripcion", suscripcion);
    }

    @Override
    public List<Suscripcion> buscarSuscripcionesFueraDeFecha(LocalDate fechaActual) {
        List <Suscripcion> listaDeBajas;

        listaDeBajas= sessionFactory.getCurrentSession()
                .createCriteria(Suscripcion.class)
                .add(Restrictions.eq("FechaFin", fechaActual))
                .list();


        return listaDeBajas;
    }

    @Override
    public TipoSuscripcion getTipoPorId(Long id_tipo) {
        return (TipoSuscripcion) this.sessionFactory.getCurrentSession()
                .createCriteria(TipoSuscripcion.class)
                .add(Restrictions.eq("id", id_tipo))
                .uniqueResult();
    }

    @Override
    public List<Suscripcion> buscarSuscripciones() {
        return sessionFactory.getCurrentSession().createCriteria(Suscripcion.class).list();
    }


}
