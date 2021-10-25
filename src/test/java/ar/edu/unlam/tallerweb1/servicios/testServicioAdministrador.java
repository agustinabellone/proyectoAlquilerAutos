package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAutos;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.unlam.tallerweb1.repositorios.testRepositorioEnviarAutoAMantenimiento.FORD;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class testServicioAdministrador {

    private RepositorioDeAutos reposotorio = mock(RepositorioDeAutos.class);
    private ServicioAdministrador servicio = new ServicioAdministradorImpl(reposotorio);

    @Test
    public void queSePuedaObtenerUnaListaDeAutosCorrectamente() throws NohayAutosException {
        givenExisteUnaListaDeAutos();
        List<Auto> autosObtenidos = whenObtengoLaListaDeAutos();
        thenSeObtieneCorrectamente(autosObtenidos, 3);
    }

    @Test(expected = NohayAutosException.class)
    public void queSeObtengaUnaListaVaciaDeAutos() throws NohayAutosException {
        givenNoExisteUnaListaDeAutos();
        List<Auto> autosObtenidos = whenObtengoLaListaDeAutos();
        thenSeObtieneUnaListaVacia(autosObtenidos);
    }

    @Test
    public void queSePuedaObtenerUnaListaDeAutosSegunSuMarca() {
        givenExisteUnaListaDeAutosDe(FORD, 4);
        List<Auto> autosObtenidos = whenObtengoAutosSegunLaMarca(FORD);
        thenSeObtieneCorrectamente(autosObtenidos, 4);
        thenSeObtienelaListaConLaMarcaCorrecta(autosObtenidos, FORD);
    }

    private void givenExisteUnaListaDeAutosDe(String marca, int cantidadDeAutos) {
        List<Auto> autos = new ArrayList<Auto>();
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setMarca(marca);
            autos.add(auto);
        }
        when(reposotorio.obtenerListaDeAutosPorMarca(anyString())).thenReturn(autos);
    }

    private void givenNoExisteUnaListaDeAutos() {
        doThrow(NohayAutosException.class).when(reposotorio).obtenerTodosLosAutos();
    }

    private void givenExisteUnaListaDeAutos() {
        List<Auto> autos = new ArrayList<Auto>();
        for (int i = 0; i < 3; i++) {
            autos.add(new Auto());
        }
        when(reposotorio.obtenerTodosLosAutos()).thenReturn(autos);
    }

    private List<Auto> whenObtengoLaListaDeAutos() throws NohayAutosException {
        return servicio.obtenerTodosLoAutos();
    }

    private List<Auto> whenObtengoAutosSegunLaMarca(String marca) {
        return reposotorio.obtenerListaDeAutosPorMarca(marca);
    }

    private void thenSeObtieneCorrectamente(List<Auto> autosObtenidos, int cantidadDeAutosEsperados) {
        assertThat(autosObtenidos).hasSize(cantidadDeAutosEsperados);
    }

    private void thenSeObtieneUnaListaVacia(List<Auto> autosObtenidos) {
        assertThat(autosObtenidos).hasSize(0);
    }

    private void thenSeObtienelaListaConLaMarcaCorrecta(List<Auto> autosObtenidos, String marcaEsperada) {
        for (Auto auto : autosObtenidos) {
            assertThat(auto.getMarca()).isEqualTo(marcaEsperada);
        }
    }
}
