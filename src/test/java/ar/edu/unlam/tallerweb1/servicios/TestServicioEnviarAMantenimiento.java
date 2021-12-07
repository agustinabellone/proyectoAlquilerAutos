package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioEnviarAMantenimiento {

    private RepositorioAuto repositorioAuto = mock(RepositorioAuto.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioDeAuto servicioDeAuto = new ServicioDeAutoImpl(repositorioAuto, repositorioUsuario);

    @Test(expected = NoEnviaAutoAMantenimiento.class)
    public void lanzarUnaExceptionCuandoElAdministradorEnviaUnAutoOcupadoAMantenimiento() throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        Long id_auto = givenExisteUnAutoOcupado();
        whenEnviaAMantenimiento(id_auto);
    }

    private Auto whenEnviaAMantenimiento(Long id_auto) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        return servicioDeAuto.enviarAMantenimiento(id_auto);
    }

    private Long givenExisteUnAutoOcupado() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.OCUPADO);
        auto.setId(1l);
        return auto.getId();
    }

    @Test
    public void queSePuedaEnviarUnAutoDisponibleAMantenimiento() throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        Long id_auto = givenExisteUnAutoDisponible();
        whenSeteaLaSituacion();
        Auto enMantenimiento = whenEnviaAMantenimiento(id_auto);
        thenObtengoElAuto(enMantenimiento);
    }

    private void whenSeteaLaSituacion() {
        Auto auto  = new Auto();
        auto.setId(1l);
        auto.setSituacion(Situacion.EN_MANTENIMIENTO);
        when(repositorioAuto.enviarAMantenimiento(anyLong(),any(),any())).thenReturn(auto);
    }


    private Long givenExisteUnAutoDisponible() {
        Auto auto = new Auto();
        auto.setId(1l);
        auto.setSituacion(Situacion.DISPONIBLE);
        when(repositorioAuto.buscarPor(anyLong())).thenReturn(auto);
        return auto.getId();
    }

    private void thenObtengoElAuto(Auto enMantenimiento) {
        assertThat(enMantenimiento.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
    }
}
