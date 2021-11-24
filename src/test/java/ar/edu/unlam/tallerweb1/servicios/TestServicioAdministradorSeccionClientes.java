package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesNoSuscriptos;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptos;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestServicioAdministradorSeccionClientes {

    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioSuscripcion servicioSuscripcion = new ServicioSuscripcionImpl(repositorioSuscripcion,repositorioUsuario);

    @Test(expected = NoHayClientesSuscriptos.class)
    public void lanzarUnaExceptionCuandoNoExistanClientesSuscriptos() throws NoHayClientesSuscriptos {
        givenNoExistenClientesSuscriptos();
        whenObtieneLaListaDeClientesSuscriptos();
    }

    private void givenNoExistenClientesSuscriptos() {
    }

    private List<Suscripcion> whenObtieneLaListaDeClientesSuscriptos() throws NoHayClientesSuscriptos {
        return servicioSuscripcion.obtenerClientesSuscriptos();
    }

    @Test
    public void queSePuedaObtenerUnaListaDeClientesSuscriptos() throws NoHayClientesSuscriptos {
        givenExistenClientesSuscriptos(4);
        List<Suscripcion> clientesSuscriptos = whenObtieneLaListaDeClientesSuscriptos();
        thenObtengoLaLista(clientesSuscriptos, 4);
    }

    private void givenExistenClientesSuscriptos(int cantidad) {
        List<Suscripcion> clientesSuscriptos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setUsuario(new Usuario());
            clientesSuscriptos.add(suscripcion);
        }
        when(repositorioSuscripcion.buscarSuscripciones()).thenReturn(clientesSuscriptos);
    }

    private void thenObtengoLaLista(List<Suscripcion> clientesSuscriptos, int cantidad_esperada) {
        assertThat(clientesSuscriptos).hasSize(cantidad_esperada);
    }

    @Test(expected = NoHayClientesNoSuscriptos.class)
    public void lanzarUnaExceptionCuandoNoExistanClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        givenNoExistenClientesNoSuscriptos();
        whenObtieneLaListaDeClientesNoSuscriptos();
    }

    private void givenNoExistenClientesNoSuscriptos() {
        when(repositorioUsuario.buscarUsuariosPorRol(anyString())).thenReturn(new ArrayList<>());
    }

    private List<Usuario> whenObtieneLaListaDeClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        return servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos();
    }

    @Test
    public void queSePuedaObtenerClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        givenExistenClientesNosusCriptos(4);
        List<Usuario> usuarios = whenObtieneLaListaDeClientesNoSuscriptos();
        thenObtengoLaListaDeUsuarios(usuarios,4);
    }

    private void givenExistenClientesNosusCriptos(int cantidad) {
        List<Usuario> listaUsuariosClientes = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol("cliente");
            listaUsuariosClientes.add(usuario);
        }
        when(repositorioUsuario.buscarUsuariosPorRol("cliente")).thenReturn(listaUsuariosClientes);
    }

    private void thenObtengoLaListaDeUsuarios(List<Usuario> usuarios, int cantidad_esperada) {
        assertThat(usuarios).hasSize(cantidad_esperada);
    }
}
