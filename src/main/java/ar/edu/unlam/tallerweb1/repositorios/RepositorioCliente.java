package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface RepositorioCliente {

    Cliente buscarPorId(Long id);

    Cliente guardar(Cliente cliente);

}
