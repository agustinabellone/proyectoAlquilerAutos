package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class testServicioEnviarAutoAMantenimiento {

    public static final String FECHA_QUE_SE_ENVIA = "09/10/21";
    private ServicioMantenimiento servicioEnviarAutoAMantenimiento = new ServicioMantenimientoImpl();

    @Test
    public void queSePuedaEnviarUnAutoAMantenimiento() throws Exception {
        Auto queNecesitaMantenimiento = givenUnAuto();
        whenEnvioAMantenimientoAlAuto(queNecesitaMantenimiento, FECHA_QUE_SE_ENVIA);
        thenElEnvioEsExitoso();
    }

    private Auto givenUnAuto() {
        return new Auto();
    }

    private void whenEnvioAMantenimientoAlAuto(Auto queNecesitaMantenimiento, String FECHA_QUE_SE_ENVIA) throws Exception {
        servicioEnviarAutoAMantenimiento.enviar(queNecesitaMantenimiento,FECHA_QUE_SE_ENVIA);
    }

    private void thenElEnvioEsExitoso() {
    }
}
