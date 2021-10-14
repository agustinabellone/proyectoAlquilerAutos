package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RepositorioCliente")
public class RepositorioClienteImpl implements ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioClienteImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return this.sessionFactory.getCurrentSession().get(Cliente.class, id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        this.sessionFactory.getCurrentSession().save(cliente);
        return cliente;
    }
}
