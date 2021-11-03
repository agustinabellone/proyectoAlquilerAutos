package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testControladorAdministrador {
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init(){
        controlador = new ControladorAdministrador();
        modelAndView = new ModelAndView();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void alAccederALaVistaPrincipalNoPuedePorqueNoEstaAsignadoElRolDeAdministrador() {
        HttpServletRequest usuarioSinRol = givenExisteUnUsuarioSinRolDeAdministrador();
        whenAccedeALaVistaPrincipal(usuarioSinRol);
        thenloMandaAlLoginConMensajeDeError(this.modelAndView,"No tienes los permisos necesarios");
    }

    @Test
    public void alAccederALaVistaPrincipalLoDejaAccederConMensajeDeBienvenida() {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenAccedeALaVistaPrincipal(usuarioConRol);
        thenLoEnviaALaVistaPrincipalConMensajeDeBienvenida(this.modelAndView);
    }

    @Test
    public void queSePuedaMostrarElPanelPrincipalConInformarcionDelAdministrador(){
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenSeMuestraLaVistaPrincipalConLaInformacionDel(usuarioConRol);
        thenSeMuestraElPanelPrincipalConLaInformacionDelUsuario(this.modelAndView);
    }

    private HttpServletRequest givenExisteUnUsuarioSinRolDeAdministrador() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(null);
        return request;
    }

    private HttpServletRequest givenExisteUnUsuarioConRolDeAdministrador() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn("admin");
        when(request.getSession().getAttribute("id")).thenReturn(1L);
        when(request.getSession().getAttribute("nombre")).thenReturn("admin");
        return request;
    }

    private void whenAccedeALaVistaPrincipal(HttpServletRequest usuario) {
        this.modelAndView = controlador.irALaVistaPrincipal(usuario);
    }

    private void whenSeMuestraLaVistaPrincipalConLaInformacionDel(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private void thenLoEnviaALaVistaPrincipalConMensajeDeBienvenida(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/panel-principal");
    }

    private void thenloMandaAlLoginConMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    private void thenSeMuestraElPanelPrincipalConLaInformacionDelUsuario(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("nombre")).isEqualTo("admin");
    }
}
