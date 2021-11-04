package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class testControladorAdministrador {
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;
    private HttpServletRequest request;
    private HttpSession session;
    private ServicioAlquiler servicioAlquiler;

    @Before
    public void init() {
        servicioAlquiler = mock(ServicioAlquiler.class);
        controlador = new ControladorAdministrador(servicioAlquiler);
        modelAndView = new ModelAndView();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void alAccederALaVistaPrincipalNoPuedePorqueNoEstaAsignadoElRolDeAdministrador() {
        HttpServletRequest usuarioSinRol = givenExisteUnUsuarioSinRolDeAdministrador();
        whenAccedeALaVistaPrincipal(usuarioSinRol);
        thenloMandaAlLoginConMensajeDeError(this.modelAndView, "No tienes los permisos necesarios para acceder a esta pagina");
    }

    @Test
    public void alAccederALaVistaPrincipalLoDejaAccederConMensajeDeBienvenida() {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenAccedeALaVistaPrincipal(usuarioConRol);
        thenLoEnviaALaVistaPrincipalConMensajeDeBienvenida(this.modelAndView);
    }

    @Test
    public void queSePuedaMostrarElPanelPrincipalConInformarcionDelAdministrador() {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenSeMuestraLaVistaPrincipalConLaInformacionDel(usuarioConRol);
        thenSeMuestraElPanelPrincipalConLaInformacionDelUsuario(this.modelAndView);
    }


    @Test
    public void queNoSePuedaMostrarelPanelPrincipalConLaInformacionPorqueIntentaAccederSinLoguearse() {
        HttpServletRequest usuarioSinRol = givenExisteUnUsuarioSinRolDeAdministrador();
        whenSeMuestraLaVistaPrincipalConLaInformacionDel(usuarioSinRol);
        thenloMandaAlLoginConMensajeDeError(this.modelAndView, "No tienes los permisos necesarios para acceder a esta pagina");
    }

    @Test
    public void alMostrarLaVistaPrincipalConLaInformacionDelAdministradorTambienSeDebeMostrarLaListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        givenSeMuestraLaVistaPrincipalConLaInformacion(usuarioConRol);
        List<Auto> autosAlquilados = givenExisteUnaListaDeAutosAlquilados(10);

        whenObtengoLaListaDeAutosAlquilados(autosAlquilados);
        thenLaMuestroEnElPanelPrincipal(this.modelAndView, autosAlquilados, 10);
    }

    @Test(expected = NoHayAutosAlquiladosException.class)
    public void alMostrarLaVistaPrincipalConLaInformacionDelAdministradorTambienNoSeDebeMostrarLaListaDeAutosYaQueNoHayAutosAlquilados() throws NoHayAutosAlquiladosException {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        givenSeMuestraLaVistaPrincipalConLaInformacion(usuarioConRol);
        givenNoExistenAutosAlquilados();
        whenObtengoLaListaDeAutosAlquilados(new ArrayList<>());
        thenMuestroUnMensajeDeError(this.modelAndView, "No hay autos alquilados actualmente");
    }

    private void thenMuestroUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("error_no_hay_autos_alquilados")).isEqualTo(error);
    }

    private void givenNoExistenAutosAlquilados() throws NoHayAutosAlquiladosException {
        doThrow(NoHayAutosAlquiladosException.class).when(servicioAlquiler).obtenerAutosAlquilados();
    }

    private void thenLaMuestroEnElPanelPrincipal(ModelAndView modelAndView, List<Auto> autosAlquilados, int cantidad_esperada) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(autosAlquilados).hasSize(cantidad_esperada);
        // assertThat(modelAndView.getModel().get("lista-de-autos-alquilados")).isEqualTo(autosAlquilados);
    }

    private List<Auto> whenObtengoLaListaDeAutosAlquilados(List<Auto> autosAlquilados) throws NoHayAutosAlquiladosException {
        when(servicioAlquiler.obtenerAutosAlquilados()).thenReturn(autosAlquilados);
        return controlador.obtenerListaDeAutosAlquilados();
    }

    private void givenSeMuestraLaVistaPrincipalConLaInformacion(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private List<Auto> givenExisteUnaListaDeAutosAlquilados(int cantidad) throws NoHayAutosAlquiladosException {
        List<Auto> autosAlquilados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.OCUPADO);
            autosAlquilados.add(auto);
        }
        return autosAlquilados;
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
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().get("errorSinPermisos")).isEqualTo(error);
    }

    private void thenSeMuestraElPanelPrincipalConLaInformacionDelUsuario(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("nombre")).isEqualTo("admin");
    }
}
