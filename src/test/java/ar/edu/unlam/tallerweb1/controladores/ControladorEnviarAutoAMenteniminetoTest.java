package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class ControladorEnviarAutoAMenteniminetoTest {

    private static final String FECHA_INICIAL = "3/10/21";
    private ControladorUsuarioAdministrador controladorUsuarioAdministrador = new ControladorUsuarioAdministrador();
    private Usuario usuarioAdministrador = new Usuario();
    private Usuario usuarioNoAdminstrador = new Usuario();

    @Test
    public void queUnUsuarioConRolDeAdministradorPuedaEnviarUnAutoAMantenimiento() {
        givenQueExisteUnUsuarioAdministrador();
        Auto AUTO = givenQueExisteUnAuto();

        ModelAndView mav = whenEnvioElAutoAMantenimiento(AUTO, FECHA_INICIAL, usuarioAdministrador);

        thenElEnvioEsExitoso(mav, "El auto se envio correctamente a mantenimiento",usuarioAdministrador);
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnaAutoAMantenimiento() {
        givenUnUsuarioSinRolDeAdministrador();
        Auto AUTO = givenQueExisteUnAuto();

        ModelAndView mav = whenEnvioElAutoAMantenimiento(AUTO, FECHA_INICIAL, usuarioNoAdminstrador);
        thenElEnvioEsFalla(mav, "Error: no tiene permisos de administrador",usuarioNoAdminstrador);
    }

    private void givenQueExisteUnUsuarioAdministrador() {
        usuarioAdministrador.setRol("Admin");
    }

    private Auto givenQueExisteUnAuto() {
        return new Auto();
    }

    private void givenUnUsuarioSinRolDeAdministrador() {
        usuarioNoAdminstrador.setRol("Invitado");
    }

    private ModelAndView whenEnvioElAutoAMantenimiento(Auto auto, String fechaInicial, Usuario usuario) {
        return controladorUsuarioAdministrador.enviarAutoAManteniminento(auto, fechaInicial, usuario);
    }

    private void thenElEnvioEsExitoso(ModelAndView mav, String mensaje, Usuario usuarioAdministrador) {
        assertThat(mav.getViewName()).isEqualTo("mantenimiento");
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(mav.getModel().get("rolDelUsuario")).isEqualTo(usuarioAdministrador.getRol());
    }

    private void thenElEnvioEsFalla(ModelAndView mav, String mensaje, Usuario usuarioNoAdminstrador) {
        assertThat(mav.getViewName()).isEqualTo("home");
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(mav.getModel().get("rolDelUsuario")).isEqualTo(usuarioNoAdminstrador.getRol());
    }
}
