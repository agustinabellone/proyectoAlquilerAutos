package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {

    Usuario buscarPorId(Long id);

    Usuario buscarPorEmail(String email);

}
