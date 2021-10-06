package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class testRepositorioCliente extends SpringTest {

    @Autowired
    private  RepositorioCliente repositorioCliente;

    /// TEST DE EJEMPLO
    @Test
    @Transactional
    @Rollback
    public void guardarUnClienteDeberiaPersistirlo(){
        Cliente cliente = givenExisteUnCliente("agustina@gmail.com");
        Long id = whenGuardoUnCliente(cliente);
        thenEncuentroElCliente(id);
    }

    private Cliente givenExisteUnCliente(String email) {
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        return cliente;
    }

    private Long whenGuardoUnCliente(Cliente cliente) {
        repositorioCliente.guardar(cliente);
        return cliente.getId();
    }

    private void thenEncuentroElCliente(Long id) {
        Cliente cliente = repositorioCliente.buscarPor(id);
        assertThat(cliente).isNotNull();
    }


    //////////////////////////////////////////////////////


    @Test
    @Transactional
    @Rollback
    public void puedoBuscarEscuelasPorNombre(){
        Cliente cliente1 = givenExisteUnCliente("agustina@gmail.com");
        Cliente cliente2 = givenExisteUnCliente("federico@gmail.com");
        Cliente cliente3 = givenExisteUnCliente("dana@gmail.com");

        givenGuardoUnCliente(cliente1);
        givenGuardoUnCliente(cliente2);
        givenGuardoUnCliente(cliente3);



        List<Cliente> clientesBuscados = whenBuscoClientePorEmail("agustina@gmail.com");
        Integer cantidadEsperada = 1;
        thenEncuentroElClientePorSuEmail(clientesBuscados, cantidadEsperada);
    }

    private void givenGuardoUnCliente(Cliente cliente) {
        repositorioCliente.guardar(cliente);
    }

    private List<Cliente> whenBuscoClientePorEmail(String email) {
        return repositorioCliente.buscarPor(email);
    }

    private void thenEncuentroElClientePorSuEmail(List<Cliente> clientesBuscados, int cantidadEsperada) {
        assertThat(clientesBuscados).hasSize(cantidadEsperada);
    }

    ///////////////////////////////////////////////////////////////


    @Test
    @Transactional
    @Rollback
    public void sePuedenBuscarTodosLosClientes(){
        Cliente cliente1 = givenExisteUnCliente("agustina@gmail.com");
        Cliente cliente2 = givenExisteUnCliente("federico@gmail.com");
        Cliente cliente3 = givenExisteUnCliente("dana@gmail.com");

        givenGuardoUnCliente(cliente1);
        givenGuardoUnCliente(cliente2);
        givenGuardoUnCliente(cliente3);

        Integer cantidadEsperada = 3;
        List<Cliente> todos = whenBuscoTodosLosClientes();
        thenEncuentroTodosLosClientes(todos, cantidadEsperada);

    }

    private List<Cliente> whenBuscoTodosLosClientes() {
        return repositorioCliente.buscarTodos();
    }

    private void thenEncuentroTodosLosClientes(List<Cliente> todos, Integer cantidadEsperada) {
        assertThat(todos).hasSize(cantidadEsperada);
    }


}
