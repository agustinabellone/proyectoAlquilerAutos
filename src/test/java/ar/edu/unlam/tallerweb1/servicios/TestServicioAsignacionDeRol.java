package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioAsignacionDeRol {

    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario, repositorioSuscripcion);

    @Test(expected = NoHayUsuariosPendientesDeRol.class)
    public void lanzarUnaExceptionSiNoHayUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        givenNoExistenUsuariosPendientesDeRol();
        whenObtengoUnaListaDeLosUsuariosPendientesDeRol();
    }

    private List<Usuario> whenObtengoUnaListaDeLosUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        return servicioUsuario.obtenerListaDeUsuariosPendienteDeRol();
    }

    private void givenNoExistenUsuariosPendientesDeRol() {
    }

    @Test
    public void queSePuedaObtenerUnaListaDeUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        givenExistenUsuariosPendientesDeRol(5);
        List<Usuario> usuarioList = whenObtengoUnaListaDeLosUsuariosPendientesDeRol();
        thenObtengoLaListaDeUsuariosConRolPendiente(usuarioList);
    }

    private void givenExistenUsuariosPendientesDeRol(int cantidad) {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setEmail("eze@tallerweb"+i+".com");
            usuario.setRol(Rol.EMPLEADO);
            usuarioList.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPendientesDeRol()).thenReturn(usuarioList);
    }

    private void thenObtengoLaListaDeUsuariosConRolPendiente(List<Usuario> usuarioList) {
        assertThat(usuarioList).hasSize(5);
        for (Usuario usuario :
                usuarioList) {
            assertThat(usuario.getEmail()).contains("@tallerweb");
            assertThat(usuario.getRol()).isEqualTo(Rol.EMPLEADO);
        }
    }
}
