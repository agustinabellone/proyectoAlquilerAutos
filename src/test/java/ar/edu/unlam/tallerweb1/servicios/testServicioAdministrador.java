package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosConEsaMarca;
import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAutos;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.unlam.tallerweb1.repositorios.testRepositorioEnviarAutoAMantenimiento.FORD;
import static ar.edu.unlam.tallerweb1.repositorios.testRepositorioEnviarAutoAMantenimiento.TOYOTA;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class testServicioAdministrador {

    private RepositorioDeAutos reposotorio = mock(RepositorioDeAutos.class);
    private ServicioAdministrador servicio = new ServicioAdministradorImpl(reposotorio);
    private List<Auto> autosObtenidos = new ArrayList<Auto>();

    @Test
    public void queSePuedaObtenerUnaListaDeAutosCorrectamente() throws NohayAutosException {
        givenExisteUnaListaDeAutos();
        autosObtenidos = whenObtengoLaListaDeAutos();
        thenSeObtieneCorrectamente(autosObtenidos, 3);
    }

    @Test(expected = NohayAutosException.class)
    public void queSeObtengaUnaListaVaciaDeAutos() throws NohayAutosException {
        givenNoExisteUnaListaDeAutos();
        autosObtenidos = whenObtengoLaListaDeAutos();
        thenSeObtieneUnaListaVacia(autosObtenidos);
    }

    @Test
    public void queSePuedaObtenerUnaListaDeAutosSegunSuMarca() {
        givenExisteUnaListaDeAutosDe(FORD, 4);
        autosObtenidos = whenObtengoAutosSegunLaMarca(FORD);
        thenSeObtieneCorrectamente(autosObtenidos, 4);
        thenSeObtienelaListaConLaMarcaCorrecta(autosObtenidos, FORD);
    }

    @Test(expected = NoHayAutosConEsaMarca.class)
    public void queSeObtengaUnaListaVaciaDeAutosConUnaMarcaInexistenteYLanzeUnaException() {
        givenNoExistenAutosConLaMarca(TOYOTA);
        autosObtenidos = whenObtengoAutosSegunLaMarca(TOYOTA);
        thenSeObtieneUnaListaVacia(autosObtenidos);
    }

    private void givenNoExistenAutosConLaMarca(String marca) {
        doThrow(NoHayAutosConEsaMarca.class).when(reposotorio).obtenerListaDeAutosPorMarca(anyString());
    }

    //given
    private void givenExisteUnaListaDeAutosDe(String marca, int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setMarca(marca);
            autosObtenidos.add(auto);
        }
        when(reposotorio.obtenerListaDeAutosPorMarca(anyString())).thenReturn(autosObtenidos);
    }

    private void givenNoExisteUnaListaDeAutos() {
        doThrow(NohayAutosException.class).when(reposotorio).obtenerTodosLosAutos();
    }

    private void givenExisteUnaListaDeAutos() {
        for (int i = 0; i < 3; i++) {
            autosObtenidos.add(new Auto());
        }
        when(reposotorio.obtenerTodosLosAutos()).thenReturn(autosObtenidos);
    }

    //when
    private List<Auto> whenObtengoLaListaDeAutos() throws NohayAutosException {
        return servicio.obtenerTodosLoAutos();
    }

    private List<Auto> whenObtengoAutosSegunLaMarca(String marca) {
        return reposotorio.obtenerListaDeAutosPorMarca(marca);
    }

    //then
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
