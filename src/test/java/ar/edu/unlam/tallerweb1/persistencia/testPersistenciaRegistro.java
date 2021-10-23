package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testPersistenciaRegistro extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnUsuario(){
        Usuario usuario = givenExisteUnUsuario();
        Long id = whenGuardoElUsuario(usuario);
        thenLoPuedoBuscarPorId(id);
    }

    private Usuario givenExisteUnUsuario() {
        return new Usuario();
    }

    private Long whenGuardoElUsuario(Usuario usuario) {
        session().save(usuario);
        return usuario.getId();
    }

    private void thenLoPuedoBuscarPorId(Long id) {
        Usuario usuarioBuscado = session().get(Usuario.class, id);
        assertThat(usuarioBuscado).isNotNull();
    }


    /////////////////////////////////////////////////////////////


    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnDatoDelUsuario(){
        Usuario usuario = givenExisteUnUsuario("agus@gmail.com");
        Long id = givenPersistoUnUsuario(usuario);
        String nuevoEmail = "agustina@gmail.com";
        whenModificoUnDatoDelUsuario(usuario, nuevoEmail);
        thenElUsuarioModificoUnDato(id, nuevoEmail);

    }
    private Usuario givenExisteUnUsuario(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return usuario;
    }


    private Long givenPersistoUnUsuario(Usuario usuario) {
        session().save(usuario);
        return usuario.getId();
    }

    private void whenModificoUnDatoDelUsuario(Usuario usuario, String nuevoEmail) {
        usuario.setEmail(nuevoEmail);
        session().update(usuario);
    }

    private void thenElUsuarioModificoUnDato(Long id, String nuevoEmail) {
        Usuario usuarioBuscado = session().get(Usuario.class, id);
        assertThat(usuarioBuscado.getEmail()).isEqualTo(nuevoEmail);
    }

}