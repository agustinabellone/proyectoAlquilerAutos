package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class testControladorEnviarAutoAMentenimineto {

    private static final String ADMIN = "admin";
    private static final String INVITADO = "invitado";
    private ControladorEnviarAutoAMantenimiento controlador = new ControladorEnviarAutoAMantenimiento();
    private ModelAndView modelAnView = new ModelAndView();
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimiento() {
        Auto aEnviar = givenExisteUnAuto();
        HttpServletRequest usuarioConRolAdmin = givenExisteUnUsuarioConRol(ADMIN);

        whenElUsuarioAdministradorEnviaElAutoAMantenimiento(usuarioConRolAdmin, aEnviar);

        thenElEnvioEsExitoso(this.modelAnView);
    }

    private Auto givenExisteUnAuto() {
        return new Auto();
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(anyString())).thenReturn(rol);
        return request;
    }

    private void whenElUsuarioAdministradorEnviaElAutoAMantenimiento(HttpServletRequest request, Auto aEnviar) {
        this.modelAnView = controlador.enviarAutoAMantenimiento(request, aEnviar);
    }

    private void thenElEnvioEsExitoso(ModelAndView modelAnView) {
        assertThat(request.getSession().getAttribute("rol")).isEqualTo("admin");
        assertThat(modelAnView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAnView.getModel().get("mensaje")).isEqualTo("Se envio un auto correctamente a mantenimiento");
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnAutoAMantenimiento() {
        Auto aEnviar = givenExisteUnAuto();
        HttpServletRequest usuarioConRolInvitado = givenExisteUnUsuarioConRol(INVITADO);
        whenElUsuarioAdministradorEnviaElAutoAMantenimiento(usuarioConRolInvitado, aEnviar);
        thenElEnvioFalla(this.modelAnView);
    }

    private void thenElEnvioFalla(ModelAndView modelAnView) {
        assertThat(modelAnView.getViewName()).isEqualTo("home");
        assertThat(modelAnView.getModel().get("mensaje")).isEqualTo("No tienes permisos para realizar esta accion");
    }
}
