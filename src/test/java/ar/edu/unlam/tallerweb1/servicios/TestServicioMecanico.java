package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioMecanico {

    private String MECANICO;
    private RepositorioAuto repositorioDeAuto;
    private ServicioDeAuto servicioDeAuto;

    @Before
    public void init() {
        MECANICO = "mecanico";
        repositorioDeAuto = mock(RepositorioAuto.class);
        servicioDeAuto = new ServicioDeAutoImpl(repositorioDeAuto);
    }

    @Test(expected = NoHayAutosEnMantenientoException.class)
    public void lanzarUnaExceptionSiNoHayAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosParaMantenimiento();
        whenObtieneLaListaDeAutosParaMatenimiento();
    }

    private void givenNoExistenAutosParaMantenimiento() {
    }

    private List<Auto> whenObtieneLaListaDeAutosParaMatenimiento() throws NoHayAutosEnMantenientoException {
        return servicioDeAuto.obtenerAutosEnMantenimiento();
    }

    @Test
    public void queSePuedaObtenerUnaListaDeAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosParaMantenimiento(5, Situacion.EN_MANTENIMIENTO);
        List<Auto> paraMantenimiento = whenObtieneLaListaDeAutosParaMatenimiento();
        thenObtengoLaListaConLosAutosParaMatenimiento(paraMantenimiento);
    }

    private void givenExistenAutosParaMantenimiento(int cantidad, Situacion enMantenimiento) {
        List<Auto> para_mantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setId((long) i);
            auto.setSituacion(enMantenimiento);
            para_mantenimiento.add(auto);
        }
        when(repositorioDeAuto.buscarAutosEnMantenimiento(enMantenimiento)).thenReturn(para_mantenimiento);
    }

    private void thenObtengoLaListaConLosAutosParaMatenimiento(List<Auto> paraMantenimiento) {
        assertThat(paraMantenimiento).hasSize(5);
        for (Auto auto :
                paraMantenimiento) {
            assertThat(auto.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        }
    }
}
