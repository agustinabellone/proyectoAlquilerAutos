package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEnviarAutoAMantenimiento;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testServicioEnviarAutoAMantenimiento {

    public static final String FECHA_QUE_SE_ENVIA = "09/10/21";
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento = mock(RepositorioEnviarAutoAMantenimiento.class);
    private ServicioMantenimiento servicioEnviarAutoAMantenimiento = new ServicioMantenimientoImpl(repositorioEnviarAutoAMantenimiento);
    private Auto auto = new Auto();

    @Test
    public void queSePuedaEnviarUnAutoAMantenimiento() throws Exception {
        givenUnAutoNoExistente(auto.getPatente());
        Auto creado = whenEnvioAMantenimientoAlAuto(auto, FECHA_QUE_SE_ENVIA);
        thenElEnvioEsExitoso(creado, FECHA_QUE_SE_ENVIA);
    }

    @Test(expected = AutoYaExistente.class)
    public void queNoSePuedaEnviarUnAutoYaExistenteAMatenimiento() throws Exception {
        givenUnAutoYaExistente(auto);
        Auto existente = whenEnvioAMantenimientoAlAuto(auto, FECHA_QUE_SE_ENVIA);
        thenElEnvioFalla(existente);
    }

    @Test
    public void queSePuedaObtenerUnAutoExistenteEnMatenimiento() throws AutoNoExistente {
        givenUnAutoYaExistente(auto);
        Auto obtenido = whenObetengoElAuto(auto);
        thenLaObtencionEsExitosa(obtenido);
    }

    @Test(expected = AutoNoExistente.class)
    public void queNoSePuedaObtenerUnAutoNoExistenteEnMatenimiento() throws AutoNoExistente {
        givenUnAutoNoExistenteEnMantiemiento();
        Auto noExistente = whenObetengoElAuto(auto);
        thenLaObtencionEsFalla(noExistente);
    }

    @Test
    public void queSePuedaObtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosEnMantenimiento(3);
        List<Auto> autosObtenidos = whenObtengoLosAutosEnMantenimiento();
        thenObtengoLosAutos(autosObtenidos, 3);
    }

    @Test (expected = NoHayAutosEnMantenientoException.class)
    public void queNoSePuedaObtenerAutosQueNoEstenEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosEnMantenimiento();
        List<Auto> vacia = whenObtengoLosAutosEnMantenimiento();
        thenLaObtencionFalla(vacia, 0);
    }

    private void thenLaObtencionFalla(List<Auto> vacia, int cantidadDeAutosObtenidos) {
        assertThat(vacia).hasSize(0);
        for (Auto auto : vacia) {
            assertThat(auto).isNull();
        }
    }

    private void givenNoExistenAutosEnMantenimiento() {
        doThrow(NoHayAutosEnMantenientoException.class).when(repositorioEnviarAutoAMantenimiento).obtenerAutosEnMantenimiento();
    }

    private void givenExistenAutosEnMantenimiento(int cantidadDeAutos) {
        List<Auto> autosEnMantenimiento = new ArrayList<Auto>();
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            //auto.setEstado("EN MANTENIMIENTO");
            autosEnMantenimiento.add(auto);
        }
        when(repositorioEnviarAutoAMantenimiento.obtenerAutosEnMantenimiento()).thenReturn(autosEnMantenimiento);
    }

    private List<Auto> whenObtengoLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return servicioEnviarAutoAMantenimiento.obtenerAutosEnMantenimiento();
    }

    private void thenObtengoLosAutos(List<Auto> autosObtenidos, int cantidadDeAutosObtenidos) {
        assertThat(autosObtenidos).hasSize(cantidadDeAutosObtenidos);
        for (Auto auto : autosObtenidos) {
           // assertThat(auto.getEstado()).isEqualTo("EN MANTENIMIENTO");
        }
    }

    private void thenLaObtencionEsFalla(Auto auto) {
        assertThat(auto).isNull();
    }

    private void givenUnAutoNoExistenteEnMantiemiento() throws AutoNoExistente {
        doThrow(AutoNoExistente.class).when(repositorioEnviarAutoAMantenimiento).buscarPorPatente(auto.getPatente());
    }

    private void givenUnAutoNoExistente(String patente) {
        when(repositorioEnviarAutoAMantenimiento.buscarPorPatente(patente)).thenReturn(null);
    }

    private void givenUnAutoYaExistente(Auto auto) {
        when(repositorioEnviarAutoAMantenimiento.buscarPorPatente(auto.getPatente())).thenReturn(new Auto());
    }

    private Auto whenEnvioAMantenimientoAlAuto(Auto queNecesitaMantenimiento, String FECHA_QUE_SE_ENVIA) throws Exception {
        return servicioEnviarAutoAMantenimiento.enviar(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
    }

    private Auto whenObetengoElAuto(Auto auto) throws AutoNoExistente {
        return servicioEnviarAutoAMantenimiento.obtenerPor(auto.getPatente());
    }

    private void thenElEnvioEsExitoso(Auto enviado, String fechaQueSeEnvia) {
        assertThat(enviado).isNotNull();
        verify(repositorioEnviarAutoAMantenimiento, times(1)).guardarAutoMantenimiento(enviado);
    }

    private void thenElEnvioFalla(Auto existente) {
        verify(repositorioEnviarAutoAMantenimiento, never()).guardarAutoMantenimiento(existente);
    }

    private void thenLaObtencionEsExitosa(Auto obtenido) {
        assertThat(obtenido).isNotNull();
        verify(repositorioEnviarAutoAMantenimiento, times(1)).buscarPorPatente(obtenido.getPatente());
    }
}
