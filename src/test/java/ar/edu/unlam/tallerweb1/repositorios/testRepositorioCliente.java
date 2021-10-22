package ar.edu.unlam.tallerweb1.repositorios;

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

    @Test
    @Transactional
    @Rollback
    public void guardarUnClienteDeberiaPersistirlo(){
        Cliente cliente = givenExisteUnCliente("agus@gmail.com");
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
        Cliente cliente = repositorioCliente.BuscarPorId(id);
        assertThat(cliente).isNotNull();
    }


}
