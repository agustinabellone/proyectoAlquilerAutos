package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testRepositorioUsuario extends SpringTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void guardarUnUsuarioDeberiaPersistirlo(){
        Usuario usuario = givenExisteUnUsuario("agus@gmail.com");
        Long id = whenGuardoUnUsuario(usuario);
        thenEncuentroElUsuario(id);
    }

    private Usuario givenExisteUnUsuario(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return usuario;
    }

    private Long whenGuardoUnUsuario(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
        return usuario.getId();
    }

    private void thenEncuentroElUsuario(Long id) {
        Usuario usuario = repositorioUsuario.buscarPorId(id);
        assertThat(usuario).isNotNull();
    }


}
