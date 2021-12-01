package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
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

public class TestServicioAdministradorSeccionEmpleados {

    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario,repositorioSuscripcion);

    @Test(expected = NoHayEmpladosException.class)
    public void lanzarUnaExceptionCuandoNoHyaClientesConRolDeEncargadoDeDevolucion() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        givenNoExistenEncargadosDeDevolucion();
        whenObtengoLaListaDeLosEncargados();
    }

    private void givenNoExistenEncargadosDeDevolucion() {
    }

    private List<Usuario> whenObtengoLaListaDeLosEncargados() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        return servicioUsuario.obtenerListaDeUsuariosPorRol(Rol.ENCARGADO_DEVOLUCION);
    }

    @Test
    public void queSePuedaObtenerUnaListaDeLosClientesConRolDeEncargadoDeDevolucion() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        givenExistenEncargadosDeDevolucion(5);
        List<Usuario> usuarios = whenObtengoLaListaDeLosEncargados();
        thenObtengoUnaListaConLosEncargados(usuarios, 5);
    }

    private void givenExistenEncargadosDeDevolucion(int cantidad) {
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(Rol.ENCARGADO_DEVOLUCION);
            usuarios.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPorRol(Rol.ENCARGADO_DEVOLUCION)).thenReturn(usuarios);
    }

    private void thenObtengoUnaListaConLosEncargados(List<Usuario> usuarios, int cantidad_esperada) {
        assertThat(usuarios).hasSize(cantidad_esperada);
        for (Usuario usuario: usuarios) {
            assertThat(usuario.getRol()).isEqualTo(Rol.ENCARGADO_DEVOLUCION);
        }
    }

    @Test(expected = NoHayEmpladosException.class)
    public void lanzarUnaExceptionCuandoNoHayEmpleadosMecanicos() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        givenNoExistenUsuariosMecanicos();
        whenObtengoLaListaDeLosMecanicos();
    }

    private void givenNoExistenUsuariosMecanicos() {

    }

    private List<Usuario> whenObtengoLaListaDeLosMecanicos() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        return servicioUsuario.obtenerListaDeUsuariosPorRol(Rol.MECANICO);
    }

    @Test
    public void queSePuedaObtenerUnaListaDeMecanicos() throws NoHayEmpladosException, NoHayUsuariosPendientesDeRol {
        givenExistenMecanicos(5);
        List<Usuario> usuarioList = whenObtengoLaListaDeLosMecanicos();
        thenObtengoLaListaDeLosMecanicos(usuarioList,5);
    }

    private void givenExistenMecanicos(int cantidad) {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(Rol.MECANICO);
            usuarioList.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPorRol(Rol.MECANICO)).thenReturn(usuarioList);
    }

    private void thenObtengoLaListaDeLosMecanicos(List<Usuario> usuarioList, int cantidad_esperada) {
        assertThat(usuarioList).hasSize(cantidad_esperada);
        for (Usuario usuario : usuarioList) {
            assertThat(usuario.getRol()).isEqualTo(Rol.MECANICO);
        }
    }
}
