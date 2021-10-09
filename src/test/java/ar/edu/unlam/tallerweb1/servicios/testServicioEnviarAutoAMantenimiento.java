package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class testServicioEnviarAutoAMantenimiento {

    public static final String FECHA_QUE_SE_ENVIA = "09/10/21";
    private ServicioMantenimiento servicioEnviarAutoAMantenimiento = new ServicioMantenimientoImpl();

    @Test
    public void queSePuedaEnviarUnAutoAMantenimiento() throws Exception {
        Auto queNecesitaMantenimiento = givenUnAuto();
        Auto enviado = whenEnvioAMantenimientoAlAuto(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
        thenElEnvioEsExitoso(enviado, FECHA_QUE_SE_ENVIA);
    }

    @Test (expected = AutoYaExistente.class)
    public void queNoSePuedaEnviarUnAutoYaExistenteAMatenimiento() {

    }

    private Auto givenUnAuto() {
        return new Auto();
    }

    private Auto whenEnvioAMantenimientoAlAuto(Auto queNecesitaMantenimiento, String FECHA_QUE_SE_ENVIA) throws Exception {
        return servicioEnviarAutoAMantenimiento.enviar(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
    }

    private void thenElEnvioEsExitoso(Auto enviado, String fechaQueSeEnvia) {
        assertThat(enviado).isNotNull();
        assertThat(servicioEnviarAutoAMantenimiento.obtenerPor("ABC123")).isNotNull();
    }
}
