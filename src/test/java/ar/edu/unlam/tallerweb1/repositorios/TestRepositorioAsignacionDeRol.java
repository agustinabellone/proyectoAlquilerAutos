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
<<<<<<< HEAD
            usuario.setEmail("eze"+i+"@tallerweb.com");
=======
            usuario.setEmail("eze" + i + "@tallerweb.com");
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
            usuario.setRol(Rol.EMPLEADO);
            session().save(usuario);
        }
    }

    private List<Usuario> whenBuscoALosUsuariosPendientesDeRol() {
        return repositorioUsuario.buscarUsuariosPendientesDeRol();
    }

    private void thenObtengoUnaListaDeLosUsuariosPendientesDeRol(List<Usuario> usuarioList) {
        assertThat(usuarioList).hasSize(5);
<<<<<<< HEAD
        for (Usuario usuario: usuarioList) {
=======
        for (Usuario usuario : usuarioList) {
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
            assertThat(usuario.getRol()).isEqualTo(Rol.EMPLEADO);
            assertThat(usuario.getEmail()).contains("@tallerweb");
        }
    }
<<<<<<< HEAD
=======

    @Test
    @Rollback
    @Transactional
    public void queSePuedaActualizarElRolDelUsuario() {
        Long usuario = givenExisteUnUsuarioPendienteDeRol();
        whenActualizoElRol(usuario, Rol.MECANICO);
        Usuario actualizado = whenObtengoElUsuarioActualizado(usuario);
        thenObtengoElUsuarioActualizado(actualizado);
    }

    private Usuario whenObtengoElUsuarioActualizado(Long usuario) {
        return repositorioUsuario.buscarPorId(usuario);
    }

    private Long givenExisteUnUsuarioPendienteDeRol() {
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.EMPLEADO);
        usuario.setEmail("eze@tallerweb.comm");
        session().save(usuario);
        return usuario.getId();
    }

    private void whenActualizoElRol(Long usuario, Rol rol) {
        repositorioUsuario.actualizarRol(rol, usuario);
    }

    private void thenObtengoElUsuarioActualizado(Usuario actualizado) {
        assertThat(actualizado).isNotNull();
        assertThat(actualizado).isInstanceOf(Usuario.class);
        assertThat(actualizado.getRol()).isEqualTo(Rol.MECANICO);
    }
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
}
