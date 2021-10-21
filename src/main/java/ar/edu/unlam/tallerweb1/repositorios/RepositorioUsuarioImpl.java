package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RepositorioCliente")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return this.sessionFactory.getCurrentSession().get(Usuario.class, id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        this.sessionFactory.getCurrentSession().save(usuario);
        return usuario;
    }
}
