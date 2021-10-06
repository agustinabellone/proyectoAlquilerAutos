package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

import java.util.List;

public interface RepositorioCliente {

    void guardar(Cliente cliente);

    Cliente buscarPor(Long id);

    List<Cliente> buscarPor(String email);

    List<Cliente> buscarTodos();
}
