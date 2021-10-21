package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioUsuario {

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

}
