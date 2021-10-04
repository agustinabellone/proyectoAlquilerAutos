package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class ControladorEnviarAutoAMenteniminetoTest {

    private static final String FECHA_INICIAL = "3/10/21";
    private static final int KILOMETROS_DEFINIDDOS = 100;
    private ControladorUsuarioAdministrador controladorUsuarioAdministrador = new ControladorUsuarioAdministrador();
    private Usuario usuarioAdministrador = new Usuario();
    private Usuario usuarioNoAdminstrador = new Usuario();
    private String mensaje;
    private ModelAndView modelAndView = new ModelAndView();

    @Test
    public void queUnUsuarioConRolDeAdministradorPuedaEnviarUnAutoAMantenimiento() {
        givenQueExisteUnUsuarioConRolAdministrador();
        Auto AUTO = givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimiento(AUTO, FECHA_INICIAL, usuarioAdministrador);

        mensaje = "El auto se envio correctamente a mantenimiento";
        thenElEnvioEsExitoso(modelAndView, mensaje, usuarioAdministrador, "mantenimiento");
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnaAutoAMantenimiento() {
        givenUnUsuarioSinRolDeAdministrador();
        Auto AUTO = givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimiento(AUTO, FECHA_INICIAL, usuarioNoAdminstrador);

        mensaje = "Error: no tiene permisos de administrador";
        thenElEnvioEsFalla(modelAndView, mensaje, usuarioNoAdminstrador, "home");
    }

    @Test
    public void cuandoUnAutoLlegaAUnKilometrajeDefinidoDebeSerEnviadoAMantenimientoCorrectamente() {
        givenQueExisteUnUsuarioConRolAdministrador();
        Auto conKmDefinidos = givenQueExisteUnAutoCon(KILOMETROS_DEFINIDDOS);

        modelAndView = whenEnvioElAutoAMantenimiento(conKmDefinidos, FECHA_INICIAL, usuarioAdministrador);

        mensaje = "El auto se envio correctamente a mantenimiento";
        thenElAutoConKilometrosDefinidosEsExitoso(modelAndView, conKmDefinidos, mensaje, usuarioAdministrador, "mantenimiento");
    }

    private void thenElAutoConKilometrosDefinidosEsExitoso(ModelAndView mav, Auto conKmDefinidos, String mensaje, Usuario usuarioNoAdminstrador, String viewName) {
        assertThat(mav.getViewName()).isEqualTo(viewName);
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(mav.getModel().get("kilometrosDefinidos")).isEqualTo(conKmDefinidos.getKm());
        assertThat(mav.getModel().get("rolDelUsuario")).isEqualTo(usuarioNoAdminstrador.getRol());
    }

    private Auto givenQueExisteUnAutoCon(int kilometrosDefiniddos) {
        Auto auto = new Auto();
        auto.setKm(kilometrosDefiniddos);
        return auto;
    }


    private void givenQueExisteUnUsuarioConRolAdministrador() {
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

    private void thenElEnvioEsExitoso(ModelAndView mav, String mensaje, Usuario usuarioAdministrador, String viewName) {
        assertThat(mav.getViewName()).isEqualTo(viewName);
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(mav.getModel().get("rolDelUsuario")).isEqualTo(usuarioAdministrador.getRol());
    }

    private void thenElEnvioEsFalla(ModelAndView mav, String mensaje, Usuario usuarioNoAdminstrador, String viewName) {
        assertThat(mav.getViewName()).isEqualTo(viewName);
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(mav.getModel().get("rolDelUsuario")).isEqualTo(usuarioNoAdminstrador.getRol());
    }
}
