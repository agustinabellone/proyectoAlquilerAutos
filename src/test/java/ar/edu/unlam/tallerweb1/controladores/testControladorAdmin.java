package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testControladorAdmin {
    private static final String ADMIN = "ADMIN";
    private static final String INVITADO = "INVITADO";
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession httpSession = mock(HttpSession.class);
    private ModelAndView modelAndView;
    private ControladorAdmin controlador = new ControladorAdmin();

    @Test
    public void queUnUsuarioAdministradorPuedaIngresarAlPanelPrincipal() {
        HttpServletRequest usuarioAdministrador = givenUnUsuarioConRol(ADMIN);
        whenAccedeAlPanelPrincipal(usuarioAdministrador);
        thenAccedeAlPanelPrincipal(this.modelAndView);
    }

    private HttpServletRequest givenUnUsuarioConRol(String rolDelUsuario) {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute(anyString())).thenReturn(rolDelUsuario);
        return request;
    }

    private void whenAccedeAlPanelPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.irAlPanelPrincipal(request);
    }

    private void thenAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedoAccederAlPanelPrincipal() {
        HttpServletRequest usuarioInvitado = givenUnUsuarioConRol(INVITADO);
        whenAccedeAlPanelPrincipal(usuarioInvitado);
        thenNoAccedeAlPanelPrincipal(this.modelAndView);
    }

    private void thenNoAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
    }
}
