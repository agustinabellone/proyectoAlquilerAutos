package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAdministradorSeccionEmpleado extends SpringTest {

    private static final String MECANICO = "mecanico";
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    private static final String ENCARGADO = "encargado";

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaDeEmpleadosEncargadoDeDevolucion() {
        givenExisteUnEmpleado(ENCARGADO, 5);
        List<Usuario> usuarioList = whenBuscoPorRolDe("encargado");
        thenObtengoUnaListaDeTodosLosEncargados(usuarioList, 5);
    }

    private void givenExisteUnEmpleado(String rol, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(rol);
            session().save(usuario);
        }
    }

    private List<Usuario> whenBuscoPorRolDe(String rol) {
        return repositorioUsuario.buscarUsuariosPorRol(rol);
    }

    private void thenObtengoUnaListaDeTodosLosEncargados(List<Usuario> usuarioList, int cantidad_esperada) {
        assertThat(usuarioList).hasSize(cantidad_esperada);
        for (Usuario usuario : usuarioList) {
            assertThat(usuario.getRol()).isEqualTo("encargado");
        }
    }


    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaDeEmpleadosMecanicos() {
        givenExisteUnEmpleado(MECANICO, 5);
        List<Usuario> usuarioList = whenBuscoPorRolDe(MECANICO);
        thenObtengoUnaListaDeTodosLosMecanicos(usuarioList, 5);
    }

    private void thenObtengoUnaListaDeTodosLosMecanicos(List<Usuario> usuarioList, int cantidad_esperada) {
        assertThat(usuarioList).hasSize(cantidad_esperada);
        for (Usuario usuario : usuarioList) {
            assertThat(usuario.getRol()).isEqualTo("mecanico");
        }
    }
}
