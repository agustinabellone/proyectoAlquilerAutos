package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

import java.util.List;

public interface RepositorioCliente {

    void guardar(Cliente cliente);

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmail(String email);

    List<Cliente> buscarTodos();
    
}
