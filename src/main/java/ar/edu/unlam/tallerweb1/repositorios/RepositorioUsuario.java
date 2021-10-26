package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario buscarPorEmail(String email);

    List<Usuario> buscarTodos();


}
