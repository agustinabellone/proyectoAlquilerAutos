package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedoAccederAlPanelPrincipal() {
        HttpServletRequest usuarioInvitado = givenUnUsuarioConRol(INVITADO);
        whenAccedeAlPanelPrincipal(usuarioInvitado);
        thenNoAccedeAlPanelPrincipal(this.modelAndView);
    }

    @Test
    public void queUnUsuarioAdministradorPuedaAccederALaListaDeAutos() {
        HttpServletRequest usuarioAdminitrador = givenUnUsuarioConRol(ADMIN);
        whenAccedeALaListaDeAutos(usuarioAdminitrador);
        thenAccedeALaListaDeAutos(this.modelAndView);
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaAccederAListaDeAutos() {
        HttpServletRequest request = givenUnUsuarioConRol(INVITADO);
        whenAccedeALaListaDeAutos(request);
        thenNoAccedeALaListaDeAutos(this.modelAndView);
    }

    @Test
    public void queUnUsuarioAdministradorPuedaAccederARegistrarUnAuto() {
        HttpServletRequest request = givenUnUsuarioConRol(ADMIN);
        whenAccedeACrearUnAuto(request);
        thenAccedeAlRegistroCorrectamente(this.modelAndView);
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaAccederARegistrarUnAuto() {
        HttpServletRequest request = givenUnUsuarioConRol(INVITADO);
        whenAccedeACrearUnAuto(request);
        thenNoAccedeaRegistrarUnAuto(this.modelAndView);
    }

    //given
    private HttpServletRequest givenUnUsuarioConRol(String rolDelUsuario) {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute(anyString())).thenReturn(rolDelUsuario);
        return request;
    }

    //when
    private void whenAccedeALaListaDeAutos(HttpServletRequest request) {
        this.modelAndView = controlador.irALaListaDeAutos(request);
    }

    private void whenAccedeAlPanelPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.irAlPanelPrincipal(request);
    }

    private void whenAccedeACrearUnAuto(HttpServletRequest request) {
        this.modelAndView = controlador.irARegistrarUnNuevoAuto(request);
    }

    //then
    private void thenNoAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
    }

    private void thenAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    private void thenAccedeALaListaDeAutos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    private void thenNoAccedeALaListaDeAutos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
    }

    private void thenAccedeAlRegistroCorrectamente(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("registrar-auto");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    private void thenNoAccedeaRegistrarUnAuto(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
    }
}
