package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class ControladorEnviarAutoAMenteniminetoTest {

    private static final String FECHA_INICIAL = "3/10/21";
    private ControladorUsuarioAdministrador controladorUsuarioAdministrador = new ControladorUsuarioAdministrador();

    @Test
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimiento() {
        givenQueExisteUnUsuarioAdministrador();
        Auto AUTO = givenQueExisteUnAuto();

        ModelAndView mav = whenEnvioElAutoAMantenimiento(AUTO, FECHA_INICIAL);

        thenElEnvioEsExitoso(mav, "El auto se envio correctamente a mantenimiento");
    }

    private void givenQueExisteUnUsuarioAdministrador() {
    }

    private Auto givenQueExisteUnAuto() {
        return new Auto();
    }

    private ModelAndView whenEnvioElAutoAMantenimiento(Auto auto, String fechaInicial) {
        return controladorUsuarioAdministrador.enviarAutoAManteniminento(auto, fechaInicial);
    }

    private void thenElEnvioEsExitoso(ModelAndView mav, String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("mantenimiento");
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
    }
}
