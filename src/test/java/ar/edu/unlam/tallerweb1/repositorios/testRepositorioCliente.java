package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testRepositorioCliente extends SpringTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void guardarUnClienteDeberiaPersistirlo(){
        Usuario usuario = givenExisteUnCliente("agus@gmail.com");
        Long id = whenGuardoUnCliente(usuario);
        thenEncuentroElCliente(id);
    }

    private Usuario givenExisteUnCliente(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return usuario;
    }

    private Long whenGuardoUnCliente(Usuario cliente) {
        repositorioUsuario.guardar(cliente);
        return cliente.getId();
    }

    private void thenEncuentroElCliente(Long id) {
        Usuario usuario = repositorioUsuario.buscarPorId(id);
        assertThat(usuario).isNotNull();
    }


}
