package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdministrador;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class testControladorAdmin {

    private static final String ADMIN = "admin";
    private static final String INVITADO = "INVITADO";
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession httpSession = mock(HttpSession.class);
    private ModelAndView modelAndView;
    private ServicioAdministrador servicio = mock(ServicioAdministrador.class);
    private ControladorAdmin controlador = new ControladorAdmin(servicio);

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


    @Test
    public void queUnUsuarioAdministradorAlAccederALaListadeAutosObtengaTodosLosAutos() throws NohayAutosException {
        HttpServletRequest request = givenUnUsuarioConRol(ADMIN);
        givenExisteUnaListaDeAutos();
        whenAccedeALaListaDeAutos(request);

        List<Auto> autosObtenidos = whenObtieneLaListaDeAutos();
        thenTodosLosAutosSonObtenidos(this.modelAndView, autosObtenidos);
    }

    @Test(expected = NohayAutosException.class)
    public void queUnUsuarioAdministradorAlAccederALaListadeAutosObtengaUnaListaVacia() throws NohayAutosException {
        //given
        HttpServletRequest request = givenUnUsuarioConRol(ADMIN);
        givenNoExisteUnaListaDeAutos();
        whenAccedeALaListaDeAutos(request);

        // when
        List<Auto> autosObtenidos = whenObtieneLaListaDeAutos();
        //then
        thenNoObtieneNigunAuto(this.modelAndView, autosObtenidos);
    }

    //given
    private HttpServletRequest givenUnUsuarioConRol(String rolDelUsuario) {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute(anyString())).thenReturn(rolDelUsuario);
        return request;
    }

    private void givenExisteUnaListaDeAutos() throws NohayAutosException {
        List<Auto> autos = new ArrayList<Auto>();
        for (int i = 0; i < 3; i++) {
            autos.add(new Auto());
        }
        when(servicio.obtenerTodosLoAutos()).thenReturn(autos);
    }


    private void givenNoExisteUnaListaDeAutos() throws NohayAutosException {
        doThrow(NohayAutosException.class).when(servicio).obtenerTodosLoAutos();
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

    private List<Auto> whenObtieneLaListaDeAutos() throws NohayAutosException {
        return servicio.obtenerTodosLoAutos();
    }


    //then
    private void thenAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("Bienvenido!!!");
    }

    private void thenNoAccedeAlPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No tienes los permisos necesarios para acceder a esta pagina.");
    }

    private void thenAccedeALaListaDeAutos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    private void thenNoAccedeALaListaDeAutos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No tienes los permisos necesarios para acceder a esta pagina.");
    }

    private void thenAccedeAlRegistroCorrectamente(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("registrar-auto");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(ADMIN);
    }

    private void thenNoAccedeaRegistrarUnAuto(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(INVITADO);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No tienes los permisos necesarios para acceder a esta pagina.");
    }

    private void thenTodosLosAutosSonObtenidos(ModelAndView modelAndView, List<Auto> autosObtenidos) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAndView.getModel().get("lista-de-autos")).isEqualTo(autosObtenidos);
        for (Auto auto : autosObtenidos) {
            assertThat(auto).isNotNull();
        }
        assertThat(autosObtenidos).hasSize(3);
    }

    private void thenNoObtieneNigunAuto(ModelAndView modelAndView, List<Auto> autosObtenidos) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No hay autos disponibles");
        assertThat(autosObtenidos).hasSize(0);
    }
}
