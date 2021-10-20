package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository("repositorioEnviarAutoAMantenimiento")
=======
import java.util.List;

@Repository
>>>>>>> master
public class RepositorioEnviarAutoAMantenimientoImpl implements RepositorioEnviarAutoAMantenimiento {

    private SessionFactory sessionFactory;
    private Session session;
    private String propertyName;

    @Autowired
    public RepositorioEnviarAutoAMantenimientoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Auto buscarPorPatente(String patente) {
        return obtenerAutoPor(patente, propertyName);
    }

    @Override
    public List<Auto> buscarPorModelo(String modelo) {
        this.propertyName = "modelo";
        return obtenerListadoDeAutosPor(modelo);
    }

    @Override
    public List<Auto> buscarPorMarca(String marca) {
        this.propertyName = "marca";
        return obtenerListadoDeAutosPor(marca);
    }

    @Override
    public Auto buscarPorId(Long idDelAuto) {
        session = getSession();
        return (Auto) session.createCriteria(Auto.class).
                add(Restrictions.eq("id", idDelAuto)).
                uniqueResult();
    }

    @Override
    public List<Auto> buscarPorAnioDeFabricacion(int anioDeFabricacion) {
        session = getSession();
        return obtenerListadoDeAutosPor(anioDeFabricacion);
    }

    @Override
    public Auto guardarAutoMantenimiento(Auto existente) {
        session = getSession();
        existente.setEstado();
        session.save(existente);
        return existente;
    }

    private Auto obtenerAutoPor(String patente, String propertyName) {
        session = getSession();
        propertyName = "patente";
        return (Auto) session.createCriteria(Auto.class)
                .add(Restrictions.eq(propertyName, patente))
                .uniqueResult();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private List<Auto> obtenerListadoDeAutosPor(String valor) {
        session = getSession();
        return session.createCriteria(Auto.class)
                .add(Restrictions.eq(propertyName, valor))
                .list();
    }

    private List<Auto> obtenerListadoDeAutosPor(int anioDeFabricacion) {
        session = getSession();
        propertyName = "añoFabricación";
        return session.createCriteria(Auto.class).
                add(Restrictions.eq(propertyName, anioDeFabricacion)).
                list();
    }

}
