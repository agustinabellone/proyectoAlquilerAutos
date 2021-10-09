package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorio.RepositorioEnviarAutoAMantenimiento;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testServicioEnviarAutoAMantenimiento {

    public static final String FECHA_QUE_SE_ENVIA = "09/10/21";
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento = mock(RepositorioEnviarAutoAMantenimiento.class);
    private ServicioMantenimiento servicioEnviarAutoAMantenimiento = new ServicioMantenimientoImpl(repositorioEnviarAutoAMantenimiento);
    private Auto auto = new Auto();

    @Test
    public void queSePuedaEnviarUnAutoAMantenimiento() throws Exception {
        Auto queNecesitaMantenimiento = givenUnAuto();
        Auto enviado = whenEnvioAMantenimientoAlAuto(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
        thenElEnvioEsExitoso(enviado, FECHA_QUE_SE_ENVIA);
    }

    @Test(expected = AutoYaExistente.class)
    public void queNoSePuedaEnviarUnAutoYaExistenteAMatenimiento() throws Exception {
        givenUnAutoYaExistente(auto);
        Auto existente = whenEnvioAMantenimientoAlAuto(auto, FECHA_QUE_SE_ENVIA);
        thenElEnvioFalla(existente);
    }

    @Test
    public void queSePuedaObtenerUnAutoExistenteEnMatenimiento() {
        givenUnAutoYaExistente(auto);
        Auto obtenido = whenObetengoElAuto(auto);
        thenLaObtencionEsExitosa(obtenido);
    }

    @Test (expected = AutoNoExistente.class)
    public void queNoSePuedaObtenerUnAutoNoExistenteEnMatenimiento(){}

    private Auto givenUnAuto() {
        return auto;
    }

    private void givenUnAutoYaExistente(Auto auto) {
        when(repositorioEnviarAutoAMantenimiento.buscarPor(auto.getPatente())).thenReturn(new Auto());
    }

    private Auto whenEnvioAMantenimientoAlAuto(Auto queNecesitaMantenimiento, String FECHA_QUE_SE_ENVIA) throws Exception {
        return servicioEnviarAutoAMantenimiento.enviar(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
    }

    private Auto whenObetengoElAuto(Auto auto) {
        return servicioEnviarAutoAMantenimiento.obtenerPor(auto.getPatente());
    }

    private void thenElEnvioEsExitoso(Auto enviado, String fechaQueSeEnvia) {
        assertThat(enviado).isNotNull();
    }

    private void thenElEnvioFalla(Auto existente) {
        assertThat(existente).isNotNull();
    }

    private void thenLaObtencionEsExitosa(Auto obtenido) {
        assertThat(obtenido).isNotNull();
    }
}
