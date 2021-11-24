package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioAdministradorSeccionEmpleados {

    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario,repositorioSuscripcion);

    @Test(expected = NoHayEmpladosException.class)
    public void lanzarUnaExceptionCuandoNoHyaClientesConRolDeEncargadoDeDevolucion() throws NoHayEmpladosException {
        givenNoExistenEncargadosDeDevolucion();
        whenObtengoLaListaDeLosEncargados();
    }

    private void givenNoExistenEncargadosDeDevolucion() {
    }

    private List<Usuario> whenObtengoLaListaDeLosEncargados() throws NoHayEmpladosException {
        return servicioUsuario.obtenerListaDeUsuariosPorRol("encargado");
    }

    @Test
    public void queSePuedaObtenerUnaListaDeLosClientesConRolDeEncargadoDeDevolucion() throws NoHayEmpladosException {
        givenExistenEncargadosDeDevolucion(5);
        List<Usuario> usuarios = whenObtengoLaListaDeLosEncargados();
        thenObtengoUnaListaConLosEncargados(usuarios, 5);
    }

    private void givenExistenEncargadosDeDevolucion(int cantidad) {
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol("encargado");
            usuarios.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPorRol("encargado")).thenReturn(usuarios);
    }

    private void thenObtengoUnaListaConLosEncargados(List<Usuario> usuarios, int cantidad_esperada) {
        assertThat(usuarios).hasSize(cantidad_esperada);
        for (Usuario usuario: usuarios) {
            assertThat(usuario.getRol()).isEqualTo("encargado");
        }
    }

    @Test(expected = NoHayEmpladosException.class)
    public void lanzarUnaExceptionCuandoNoHayEmpleadosMecanicos() throws NoHayEmpladosException {
        givenNoExistenUsuariosMecanicos();
        whenObtengoLaListaDeLosMecanicos();
    }

    private void givenNoExistenUsuariosMecanicos() {

    }

    private List<Usuario> whenObtengoLaListaDeLosMecanicos() throws NoHayEmpladosException {
        return servicioUsuario.obtenerListaDeUsuariosPorRol("mecanico");
    }

    @Test
    public void queSePuedaObtenerUnaListaDeMecanicos() throws NoHayEmpladosException {
        givenExistenMecanicos(5);
        List<Usuario> usuarioList = whenObtengoLaListaDeLosMecanicos();
        thenObtengoLaListaDeLosMecanicos(usuarioList,5);
    }

    private void givenExistenMecanicos(int cantidad) {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol("mecanico");
            usuarioList.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPorRol("mecanico")).thenReturn(usuarioList);
    }

    private void thenObtengoLaListaDeLosMecanicos(List<Usuario> usuarioList, int cantidad_esperada) {
        assertThat(usuarioList).hasSize(cantidad_esperada);
        for (Usuario usuario : usuarioList) {
            assertThat(usuario.getRol()).isEqualTo("mecanico");
        }
    }
}
