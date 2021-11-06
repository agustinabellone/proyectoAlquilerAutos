package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("ServicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario=repositorioUsuario;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return this.repositorioUsuario.buscarPorId(id);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repositorioUsuario.buscarPorEmail(email);
    }

    @Override
    public void eliminarUsuario(Long id) {
        this.repositorioUsuario.eliminarUsuario(id);
    }
}
