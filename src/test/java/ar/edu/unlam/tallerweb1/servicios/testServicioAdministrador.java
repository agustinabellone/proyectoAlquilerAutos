package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testServicioAdministrador {

    private RepositorioAlquiler repositorioAlquiler = mock(RepositorioAlquiler.class);
    private ServicioAlquiler servicioAlquiler = new ServicioAlquilerImpl(repositorioAlquiler);

    @Test
    public void queSePuedaObtenerAutosAlquilados() throws NoHayAutosAlquiladosException {
        givenExistenAutosAlquilados(10);
        List<Auto> autosAlquilados = whenObtengoLosAutos();
        thenMeTraeLaListaConLosDiezAutos(autosAlquilados, 10);
    }

    @Test(expected = NoHayAutosAlquiladosException.class)
    public void queNoSePuedaObtenerAutosAlquiladosPorqueTodaviaNoHay() throws NoHayAutosAlquiladosException {
        givenNoExistenAutosAlquilados();
        List<Auto> autosAlquilados = whenObtengoLosAutos();
    }

    private void givenExistenAutosAlquilados(int cantidad) {
        List<Auto> listaAutosAlquilados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.OCUPADO);
            listaAutosAlquilados.add(auto);
        }
        when(repositorioAlquiler.buscarAutosAlquilados(Situacion.OCUPADO)).thenReturn(listaAutosAlquilados);
    }

    private void givenNoExistenAutosAlquilados() {
        when(repositorioAlquiler.buscarAutosAlquilados(Situacion.OCUPADO)).thenThrow(NoHayAutosAlquiladosException.class);
    }

    private List<Auto> whenObtengoLosAutos() throws NoHayAutosAlquiladosException {
        return servicioAlquiler.obtenerAutosAlquilados();
    }

    private void thenMeTraeLaListaConLosDiezAutos(List<Auto> autosAlquilados, int cantidad_esperada) {
        assertThat(autosAlquilados).hasSize(cantidad_esperada);
    }
}
