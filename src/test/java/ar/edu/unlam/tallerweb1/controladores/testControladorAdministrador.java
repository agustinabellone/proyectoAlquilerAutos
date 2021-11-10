package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
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
    private ServicioDeAuto servicioDeAuto;

    @Before
    public void init() {
        servicioDeAuto = mock(ServicioDeAuto.class);
        servicioAlquiler = mock(ServicioAlquiler.class);
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto);
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
        givenSeMuestraLaVistaPrincipalConLaInformacionDel(usuarioConRol);
        givenExisteUnaListaDeAutosAlquilados(10);
        whenMuestroLaListaDeAutosAlquiladosAl(usuarioConRol);
        thenSeMuestraElPanelPrincipalConLaListaDeAutos(this.modelAndView);
    }

    @Test(expected = NoHayAutosAlquiladosException.class)
    public void alMostrarLaVistaPrincipalConLaInformacionDelAdministradorTambienNoSeDebeMostrarLaListaDeAutosYaQueNoHayAutosAlquilados() throws NoHayAutosAlquiladosException {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        givenSeMuestraLaVistaPrincipalConLaInformacion(usuarioConRol);
        givenNoExistenAutosAlquilados();
        whenObtengoLaListaDeAutosAlquilados();
        thenMuestroUnMensajeDeError(this.modelAndView, "No hay autos alquilados actualmente");
    }

    @Test
    public void queElUsuarioAdministradorPuedaAccederALosAutosDisponibles() throws NoHayAutosDisponiblesException {
        givenExistenAutosDisponibles(Situacion.DISPONIBLE, 10);
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenAccedeALaVistaDeAutosDisponibles(usuarioConRol);
        thenSeMuestraLaVistaConLaListaDeLosAutosDisponibles(this.modelAndView);
    }

    @Test(expected = NoHayAutosDisponiblesException.class)
    public void queElUsuarioAdministradorNoPuedaVerLaListaPorqueNoHayAutosDisponibles() throws NoHayAutosDisponiblesException {
        givenNoExistenAutosDisponibles();
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        givenSeMuestraLaVistaDisponibles(usuarioConRol);
        whenObtengoLaListaDeAutosDisponibles();
        thenMuestroUnMensajeDeErrorDisponibles(this.modelAndView, "No hay autos disponibles actualmente");
    }

    @Test
    public void queElUsuarioAdministradorPuedaVerLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        givenExistenAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO, 10);
        whenAccedeAlaVistaDeAutosEnMantenimiento(usuarioConRol);
        whenObtengoLaListaDeAutosEnMantenimiento();
        thenSeMuestraLaVistaConLaListaDeLosAutosEnMantenimiento(this.modelAndView);
    }

    @Test(expected = NoHayAutosEnMantenientoException.class)
    public void queElUsuarioAdministradorNoPuedaVerLosAutosEnMantenimientoPorqueNoHayTodavia() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosEnMantenimiento();
        HttpServletRequest usuarioConRol = givenExisteUnUsuarioConRolDeAdministrador();
        whenAccedeAlaVistaDeAutosEnMantenimiento(usuarioConRol);
        whenObtengoLaListaDeAutosEnMantenimiento();
        thenMuestraLaVistaConMensajeDeErrorQueNoHayAutosEnMatenimiento(this.modelAndView, "No hay autos en mantenimiento actualmente");
    }

    private void thenMuestraLaVistaConMensajeDeErrorQueNoHayAutosEnMatenimiento(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos_en_mantenimiento");
        assertThat(modelAndView.getModel().get("error_no_hay_autos_en_mantenimiento")).isEqualTo(error);
    }

    private List<Auto> whenObtengoLaListaDeAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return controlador.obtenerListaDeAutosEnMantenimiento();
    }

    private void givenNoExistenAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        when(servicioDeAuto.obtenerAutosEnMantenimiento()).thenThrow(NoHayAutosEnMantenientoException.class);
    }

    private void thenSeMuestraLaVistaConLaListaDeLosAutosEnMantenimiento(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos_en_mantenimiento");
        assertThat(modelAndView.getModel().get("en_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("en_mantenimiento")).isInstanceOf(List.class);
        List<Auto> autosEnMantenimiento = (List<Auto>) modelAndView.getModel().get("en_mantenimiento");
        assertThat(autosEnMantenimiento).hasSize(10);
    }

    private void whenAccedeAlaVistaDeAutosEnMantenimiento(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarAutosEnMantenimiento(usuarioConRol);
    }

    private void givenExistenAutosEnMantenimiento(Situacion enMantenimiento, int cantidadDeAutos) throws NoHayAutosEnMantenientoException {
        List<Auto> listaDeAutosEnMantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enMantenimiento);
            listaDeAutosEnMantenimiento.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnMantenimiento()).thenReturn(listaDeAutosEnMantenimiento);
    }

    private void thenMuestroUnMensajeDeErrorDisponibles(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("disponibles");
        assertThat(modelAndView.getModel().get("error_sin_autos_disponibles")).isEqualTo(error);
    }

    private void givenSeMuestraLaVistaDisponibles(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarAutosDisponibles(usuarioConRol);
    }

    private List<Auto> whenObtengoLaListaDeAutosDisponibles() throws NoHayAutosDisponiblesException {
        return controlador.obtenerListaDeAutosDisponibles();
    }

    private void givenNoExistenAutosDisponibles() throws NoHayAutosDisponiblesException {
        when(servicioAlquiler.obtenerAutosDisponibles()).thenThrow(NoHayAutosDisponiblesException.class);
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

    private void givenSeMuestraLaVistaPrincipalConLaInformacionDel(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private void givenExisteUnaListaDeAutosAlquilados(int cantidad) throws NoHayAutosAlquiladosException {
        List<Auto> listaDeAutos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.OCUPADO);
            listaDeAutos.add(auto);
        }
        when(servicioAlquiler.obtenerAutosAlquilados()).thenReturn(listaDeAutos);
    }

    private void givenNoExistenAutosAlquilados() throws NoHayAutosAlquiladosException {
        doThrow(NoHayAutosAlquiladosException.class).when(servicioAlquiler).obtenerAutosAlquilados();
    }

    private void givenSeMuestraLaVistaPrincipalConLaInformacion(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private void givenExistenAutosDisponibles(Situacion disponible, int cantidadDeAutosDisponibles) throws NoHayAutosDisponiblesException {
        List<Auto> listaDeAutosDisponibles = new ArrayList<>();
        for (int i = 0; i < cantidadDeAutosDisponibles; i++) {
            Auto auto = new Auto();
            auto.setSituacion(disponible);
            listaDeAutosDisponibles.add(auto);
        }
        when(servicioAlquiler.obtenerAutosDisponibles()).thenReturn(listaDeAutosDisponibles);
    }

    private List<Auto> whenObtengoLaListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        return controlador.obtenerListaDeAutosAlquilados();
    }

    private void whenAccedeALaVistaPrincipal(HttpServletRequest usuario) {
        this.modelAndView = controlador.irALaVistaPrincipal(usuario);
    }

    private void whenSeMuestraLaVistaPrincipalConLaInformacionDel(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private void whenMuestroLaListaDeAutosAlquiladosAl(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarElPanelPrincipalConLaInformacionDelAdministrador(usuarioConRol);
    }

    private void whenAccedeALaVistaDeAutosDisponibles(HttpServletRequest usuarioConRol) {
        this.modelAndView = controlador.mostrarAutosDisponibles(usuarioConRol);
    }

    private void thenLoEnviaALaVistaPrincipalConMensajeDeBienvenida(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/panel-principal");
    }

    private void thenMuestroUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("error_no_hay_autos_alquilados")).isEqualTo(error);
    }

    private void thenloMandaAlLoginConMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().get("errorSinPermisos")).isEqualTo(error);
    }

    private void thenSeMuestraElPanelPrincipalConLaInformacionDelUsuario(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("nombre")).isEqualTo("admin");
    }

    private void thenSeMuestraElPanelPrincipalConLaListaDeAutos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("nombre")).isEqualTo(request.getSession().getAttribute("nombre"));
        assertThat(modelAndView.getModel().get("autosAlquilados")).isNotNull();
        assertThat(modelAndView.getModel().get("autosAlquilados")).isInstanceOf(List.class);
        List<Auto> autos = (List<Auto>) modelAndView.getModel().get("autosAlquilados");
        assertThat(autos).hasSize(10);
    }


    private void thenSeMuestraLaVistaConLaListaDeLosAutosDisponibles(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("disponibles");
        assertThat(modelAndView.getModel().get("autosDisponibles")).isNotNull();
        assertThat(modelAndView.getModel().get("autosDisponibles")).isInstanceOf(List.class);
        List<Auto> autosDisponibles = (List<Auto>) modelAndView.getModel().get("autosDisponibles");
        assertThat(autosDisponibles).hasSize(10);
    }
}
