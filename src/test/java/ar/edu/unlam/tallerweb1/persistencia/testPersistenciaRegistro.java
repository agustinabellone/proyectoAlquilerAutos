package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testPersistenciaRegistro extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnCliente(){
        Cliente cliente = givenExisteUnCliente();
        Long id = whenGuardoElCliente(cliente);
        thenLoPuedoBuscarPorId(id);
    }

    private Cliente givenExisteUnCliente() {
        return new Cliente();
    }

    private Long whenGuardoElCliente(Cliente cliente) {
        session().save(cliente);
        return cliente.getId();
    }

    private void thenLoPuedoBuscarPorId(Long id) {
        Cliente clienteBuscado = session().get(Cliente.class, id);
        assertThat(clienteBuscado).isNotNull();
    }


    /////////////////////////////////////////////////////////////


    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnDatoDelCliente(){
        Cliente cliente = givenExisteUnCliente("agus@gmail.com");
        Long id = givenPersistoUnCliente(cliente);
        String nuevoEmail = "agustina@gmail.com";
        whenModificoUnDatoDelCliente(cliente, nuevoEmail);
        thenElClienteModificoUnDato(id, nuevoEmail);

    }
    private Cliente givenExisteUnCliente(String email) {
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        return cliente;
    }


    private Long givenPersistoUnCliente(Cliente cliente) {
        session().save(cliente);
        return cliente.getId();
    }

    private void whenModificoUnDatoDelCliente(Cliente cliente, String nuevoEmail) {
        cliente.setEmail(nuevoEmail);
        session().update(cliente);
    }

    private void thenElClienteModificoUnDato(Long id, String nuevoEmail) {
        Cliente clienteBuscado = session().get(Cliente.class, id);
        assertThat(clienteBuscado.getEmail()).isEqualTo(nuevoEmail);
    }

}