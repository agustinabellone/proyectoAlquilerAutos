package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RepositorioClienteImpl implements RepositorioCliente {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioClienteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Cliente cliente) {
        sessionFactory.getCurrentSession().save(cliente);
    }

    @Override
    public Cliente buscarPor(Long id) {
        return sessionFactory.getCurrentSession().get(Cliente.class, id);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        Cliente resultado = (Cliente) sessionFactory.getCurrentSession()
                .createCriteria(Cliente.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
        if((resultado) != null) {
            return new Cliente();
        }
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Cliente.class)
                .list();
    }

}
