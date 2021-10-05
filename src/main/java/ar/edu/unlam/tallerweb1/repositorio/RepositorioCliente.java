package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.springframework.stereotype.Repository;


public interface RepositorioCliente {

    void guardar(Cliente cliente);

    Cliente buscarPor(Long id);
}
