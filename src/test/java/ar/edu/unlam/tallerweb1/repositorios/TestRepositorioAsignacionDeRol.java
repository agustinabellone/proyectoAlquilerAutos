package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAsignacionDeRol extends SpringTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUsuariosPendientesDeRol() {
        givenExitenUsuariosPendientesDeRol(5);
        List<Usuario> usuarioList = whenBuscoALosUsuariosPendientesDeRol();
        thenObtengoUnaListaDeLosUsuariosPendientesDeRol(usuarioList);
    }

    private void givenExitenUsuariosPendientesDeRol(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setEmail("eze"+i+"@tallerweb.com");
            session().save(usuario);
        }
    }

    private List<Usuario> whenBuscoALosUsuariosPendientesDeRol() {
        return repositorioUsuario.buscarUsuariosPendientesDeRol();
    }

    private void thenObtengoUnaListaDeLosUsuariosPendientesDeRol(List<Usuario> usuarioList) {
        assertThat(usuarioList).hasSize(5);
        for (Usuario usuario: usuarioList) {
            assertThat(usuario.getRol()).isNull();
            assertThat(usuario.getEmail()).contains("@tallerweb");
        }
    }
}
