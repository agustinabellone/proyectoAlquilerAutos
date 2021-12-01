package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioAdministradorSeccionAutos {

    private RepositorioAlquiler repositorioAlquiler = mock(RepositorioAlquiler.class);
    private RepositorioAuto repositorioAuto = mock(RepositorioAuto.class);
    private ServicioAlquiler servicioAlquiler = new ServicioAlquilerImpl(repositorioAlquiler);
    private ServicioDeAuto servicioDeAuto = new ServicioDeAutoImpl(repositorioAuto);

    @Test
    public void queSePuedaObtenerAutosAlquilados() throws NoHayAutosAlquiladosException {
        givenExistenAutosAlquilados(Situacion.OCUPADO, 10);
        List<Auto> autosAlquilados = whenObtengoLosAutos();
        thenMeTraeLaListaConLosDiezAutos(autosAlquilados, 10);
    }

    @Test(expected = NoHayAutosAlquiladosException.class)
    public void queNoSePuedaObtenerAutosAlquiladosPorqueTodaviaNoHay() throws NoHayAutosAlquiladosException {
        givenNoExistenAutosAlquilados();
        List<Auto> autosAlquilados = whenObtengoLosAutos();
        thenMeDevuelveUnaListaVacia(autosAlquilados);
    }

    @Test
    public void queSePuedaObtenerAutosDisponibles() throws NoHayAutosDisponiblesException {
        givenExistenAutosDisponibles(Situacion.DISPONIBLE, 10);
        List<Auto> autosDisponibles = whenObtengoLosAutosDisponibles();
        thenMeTraeLaListaConLosDiezAutos(autosDisponibles, 10);
    }

    @Test(expected = NoHayAutosDisponiblesException.class)
    public void queNoSePuedaObtenerAutosDisponiblesPorqueNoHay() throws NoHayAutosDisponiblesException {
        givenNoExistenAutosDisponibles();
        List<Auto> autosDisponibles = whenObtengoLosAutosDisponibles();
        thenMeDevuelveUnaListaVacia(autosDisponibles);
    }

    @Test
    public void queSePuedanObtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO, 10);
        List<Auto> autosEnMantenimiento = whenObtengoLosAutosEnMantenimiento();
        thenMeTraeLaListaConLosDiezAutos(autosEnMantenimiento, 10);
    }

    @Test(expected = NoHayAutosEnMantenientoException.class)
    public void queNoSePuedanObtenerAutosEnMantenimientoPorQueNohay() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosEnMantenimiento();
        List<Auto> autosEnMantenimiento = whenObtengoLosAutosEnMantenimiento();
        thenMeDevuelveUnaListaVacia(autosEnMantenimiento);
    }

    private void givenNoExistenAutosEnMantenimiento() {
        when(repositorioAuto.buscarAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO)).thenThrow(NoHayAutosEnMantenientoException.class);
    }

    private void givenExistenAutosEnMantenimiento(Situacion enMantenimiento, int cantidad) {
        List<Auto> listaDeAutosEnMantenimiento = this.givenExistenAutos(enMantenimiento, cantidad);
        when(repositorioAuto.buscarAutosEnMantenimiento(enMantenimiento)).thenReturn(listaDeAutosEnMantenimiento);
    }

    private void givenNoExistenAutosDisponibles() {
        when(repositorioAlquiler.obtenerAutosDisponibles()).thenThrow(NoHayAutosDisponiblesException.class);
    }

    private void givenExistenAutosDisponibles(Situacion disponible, int cantidad) {
        List<Auto> listaDeAutosDisponible = this.givenExistenAutos(disponible, cantidad);
        when(repositorioAlquiler.obtenerAutosDisponibles()).thenReturn(listaDeAutosDisponible);
    }

    private void givenExistenAutosAlquilados(Situacion ocupado, int cantidad) {
        List<Auto> listaAutosAlquilados = this.givenExistenAutos(ocupado, cantidad);
        when(repositorioAlquiler.buscarAutosAlquilados(ocupado)).thenReturn(listaAutosAlquilados);
    }

    private void givenNoExistenAutosAlquilados() {
        when(repositorioAlquiler.buscarAutosAlquilados(Situacion.OCUPADO)).thenThrow(NoHayAutosAlquiladosException.class);
    }

    private List<Auto> whenObtengoLosAutos() throws NoHayAutosAlquiladosException {
        return servicioAlquiler.obtenerAutosAlquilados();
    }

    private List<Auto> whenObtengoLosAutosDisponibles() throws NoHayAutosDisponiblesException {
        return servicioAlquiler.obtenerAutosDisponibles();
    }

    private List<Auto> whenObtengoLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return servicioDeAuto.obtenerAutosEnMantenimiento();
    }

    private void thenMeTraeLaListaConLosDiezAutos(List<Auto> listaDeAutos, int cantidad_esperada) {
        assertThat(listaDeAutos).isNotNull();
        assertThat(listaDeAutos).hasSize(cantidad_esperada);
    }

    private void thenMeDevuelveUnaListaVacia(List<Auto> autosAlquilados) {
        assertThat(autosAlquilados).hasSize(0);
    }

    private List<Auto> givenExistenAutos(Situacion situacion, int cantidadDeAutos) {
        List<Auto> listaDeAutos = new ArrayList<>();
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setSituacion(situacion);
            listaDeAutos.add(auto);
        }
        return listaDeAutos;
    }
}
