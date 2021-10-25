package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAutos;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testServicioAdministrador {

    private RepositorioDeAutos reposotorio = mock(RepositorioDeAutos.class);
    private ServicioAdministrador servicio = new ServicioAdministradorImpl(reposotorio);

    @Test
    public void queSePuedaObtenerUnaListaDeAutosCorrectamente() throws NohayAutosException {
        givenExisteUnaListaDeAutos();
        List<Auto> autosObtenidos = whenObtengoLaListaDeAutos();
        thenSeObtieneCorrectamente(autosObtenidos);
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

    private void thenSeObtieneCorrectamente(List<Auto> autosObtenidos) {
        assertThat(autosObtenidos).hasSize(3);
    }
}
