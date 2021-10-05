package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testRepositorioCliente extends SpringTest {

    @Autowired
    private RepositorioCliente repositorioCliente;

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



}
