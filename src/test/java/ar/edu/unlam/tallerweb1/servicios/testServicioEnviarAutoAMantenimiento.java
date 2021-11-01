package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class testServicioEnviarAutoAMantenimiento {

    private RepositorioAuto repositorioAuto = mock(RepositorioAuto.class);
    private ServicioDeAuto servicioDeAuto = new ServicioDeAutoImpl(repositorioAuto);

    @Test
    public void queSePuedaBuscarUnAutoExistentePorId() throws AutoNoExistente {
        Auto existente = givenExisteUnAuto();
        Auto buscado = whenBuscoElAuto(existente);
        thenLoEncuentro(buscado);
    }

    @Test (expected = AutoNoExistente.class)
    public void queNoSePuedaBuscarUnAutoQueNoExiste() throws AutoNoExistente {
        Auto noExistente = givenNoExisteUnAuto();
        Auto buscado = whenBuscoElAuto(noExistente);
        thenNoLoEncuentra(buscado);
    }

    @Test
    public void queSePuedaEnviarUnAutoAMantenimientoCorrectamente() throws AutoYaExistente {
        Auto queNecesitaMantenimiento = givenExisteUnAuto();
        Auto guardadoEnMantenimiento = whenLoEnvioAMantenimiento(queNecesitaMantenimiento);
        thenElAutoesEnviadoCorrectamente(guardadoEnMantenimiento);
    }

    @Test (expected = AutoYaExistente.class)
    public void queNoSePuedaEnviarUnAutoDosVecesAMantenimiento() throws AutoYaExistente {
        Auto enMantenimiento = givenExisteUnAutoEnMantenimiento();
        Auto noGuardado = whenLoEnvioAMantenimiento(enMantenimiento);
        thenElAutoNoSeEnvia(noGuardado);
    }

    private void thenElAutoNoSeEnvia(Auto noGuardado) {
        assertThat(noGuardado).isNull();
        assertThat(noGuardado.getSituacion()).isEqualTo(Situacion.DISPONIBLE);
    }

    private Auto givenExisteUnAutoEnMantenimiento() {
        Auto auto = new Auto();
        doThrow(AutoYaExistente.class).when(repositorioAuto).guardarEnMantenimiento(auto, LocalDate.now());
        return auto;
    }


    private Auto givenNoExisteUnAuto() throws AutoNoExistente {
        Auto auto = new Auto();
        when(repositorioAuto.buscarPor(auto.getId())).thenThrow(AutoNoExistente.class);
        return auto;
    }

    private Auto givenExisteUnAuto() {
        Auto auto = new Auto();
        when(repositorioAuto.buscarPor(auto.getId())).thenReturn(auto);
        when(repositorioAuto.buscarAutoEnMantenimientoPorIdYPorSituacion(anyLong(), anyObject())).thenReturn(null);
        return auto;
    }

    private Auto whenBuscoElAuto(Auto existente) throws AutoNoExistente {
        return servicioDeAuto.buscarAutoPorId(existente.getId());
    }

    private Auto whenLoEnvioAMantenimiento(Auto queNecesitaMantenimiento) throws AutoYaExistente {

        return servicioDeAuto.enviarAutoMantenimiento(queNecesitaMantenimiento);
    }

    private void thenLoEncuentro(Auto buscado) {
        assertThat(buscado).isNotNull();
    }

    private void thenNoLoEncuentra(Auto buscado) {
        assertThat(buscado).isNull();
    }

    private void thenElAutoesEnviadoCorrectamente(Auto guardadoEnMantenimiento) {
        assertThat(guardadoEnMantenimiento).isNotNull();
        assertThat(guardadoEnMantenimiento.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        verify(repositorioAuto,times(1)).guardarEnMantenimiento(anyObject(), anyObject());
    }
}
