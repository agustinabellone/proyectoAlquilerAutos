package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestControladorAdministrador {

    private static final String ADMINISTRADOR = "admin";
    private HttpServletRequest request;
    private HttpSession session;
    private ControladorAdministrador controlador;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ModelAndView modelAndView;
    private ServicioUsuario servicioUsuario;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        modelAndView = new ModelAndView();
        servicioUsuario = mock(ServicioUsuario.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorAdministrador(servicioDeAuto, servicioSuscripcion, servicioUsuario);
        request = givenExsiteUnUsuarioConRol(ADMINISTRADOR);
    }

    private HttpServletRequest givenExsiteUnUsuarioConRol(String administrador) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(administrador);
        return request;
    }

    @Test
    public void alIngresarElAdministradorASuPantallaPrincipalVisualizaLosAutosActualmenteAlquilados() throws NoHayAutosAlquiladosException {
        givenExistenAutosAlquilados(5, Situacion.OCUPADO);
        whenAccedeALaPantallaPrincipal(request);
        thenVisualizaLOsAutosAlquilados(this.modelAndView);
    }

    private void givenExistenAutosAlquilados(int cantidad, Situacion ocupado) throws NoHayAutosAlquiladosException {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(ocupado);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosAlquilados()).thenReturn(autoList);
    }

    private void whenAccedeALaPantallaPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.irAlPanelPrincipal(request);
    }

    private void thenVisualizaLOsAutosAlquilados(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("autos_alquilados")).isNotNull();
        assertThat(modelAndView.getModel().get("autos_alquilados")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autos_alquilados");
        assertThat(autoList).hasSize(5);
    }

    @Test
    public void alIngresarElAdministradorASuPantallaPrincipalVisualizaUnMensajeDeErrorAvisandoQueNoHayAutosAlquilados() throws NoHayAutosAlquiladosException {
        givenQueNoExistenAutosAlquilados();
        whenAccedeALaPantallaPrincipal(request);
        thenVisualizaLaVista(this.modelAndView, "panel-principal");
        thenVisualizaUnMensajeDeError("No hay autos alquilados actualmente", "error_no_hay_alquilados");
    }

    private void givenQueNoExistenAutosAlquilados() throws NoHayAutosAlquiladosException {
        doThrow(NoHayAutosAlquiladosException.class).when(servicioDeAuto).obtenerAutosAlquilados();
    }

    private void thenVisualizaLaVista(ModelAndView modelAndView, String vista) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }

    private void thenVisualizaUnMensajeDeError(String error, String nombre_error) {
        assertThat(modelAndView.getModel().get(nombre_error)).isEqualTo(error);
    }

    @Test
    public void alAccederALaPantallaDeAutosDisponiblesPuedaVisualizarUnaListaDeAutosDisponiblesParaAlquilar() throws NoHayAutosDisponiblesException {
        givenExistenAutosDisponiblesParaAlquilar(5, Situacion.DISPONIBLE);
        whenAccedoALaPantallaDeAutosDisponibles(request);
        thenVisualizaLosAutosDisponibles(this.modelAndView);
    }

    private void givenExistenAutosDisponiblesParaAlquilar(int cantidad, Situacion disponible) throws NoHayAutosDisponiblesException {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(disponible);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosDisponiblesParaAlquilar()).thenReturn(autoList);
    }

    private void whenAccedoALaPantallaDeAutosDisponibles(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosDisponiblesParaAlquilar(request);
    }

    private void thenVisualizaLosAutosDisponibles(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos-disponibles-para-alquilar");
        assertThat(modelAndView.getModel().get("autos_disponibles_para_alquilar")).isNotNull();
        assertThat(modelAndView.getModel().get("autos_disponibles_para_alquilar")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autos_disponibles_para_alquilar");
        assertThat(autoList).hasSize(5);
    }

    @Test
    public void alAccederALaPantallaDeAutosDisponiblesPuedeVisualizarUnMensajeDeErrorDeQueNoHayAutosDisponibles() throws NoHayAutosDisponiblesException {
        givenQueNoExistenAutosDisponiblesParaAlquilar();
        whenAccedoALaPantallaDeAutosDisponibles(request);
        thenVisualizaLaVista(this.modelAndView, "autos-disponibles-para-alquilar");
        thenVisualizaUnMensajeDeError("No hay autos disponibles para alquilar actualmente", "error_no_hay_disponibles");
    }

    private void givenQueNoExistenAutosDisponiblesParaAlquilar() throws NoHayAutosDisponiblesException {
        doThrow(NoHayAutosDisponiblesException.class).when(servicioDeAuto).obtenerAutosDisponiblesParaAlquilar();
    }

    @Test
    public void alAccederAlaPantallaDeAutosEnMantenimientoPuedaVisualizarUnaListaConLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosEnMantenimiento(5, Situacion.EN_MANTENIMIENTO);
        whenAccedeALaPantallaDeAutosEnMantenimiento(request);
        thenVisualizaLosAutosEnMantenimiento(this.modelAndView, 5);
    }

    private void givenExistenAutosEnMantenimiento(int cantidad, Situacion enMantenimiento) throws NoHayAutosEnMantenientoException {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enMantenimiento);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnMantenimiento()).thenReturn(autoList);
    }

    private void whenAccedeALaPantallaDeAutosEnMantenimiento(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosEnMantenimiento(request);
    }

    private void thenVisualizaLosAutosEnMantenimiento(ModelAndView modelAndView, int cantidad_esperada) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos-en-mantenimiento");
        assertThat(modelAndView.getModel().get("autos_en_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("autos_en_mantenimiento")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autos_en_mantenimiento");
        assertThat(autoList).hasSize(cantidad_esperada);
    }

    @Test
    public void queAlAccederALaPantallaDeAutosEnMantenimientoPuedaVisualizarUnMensajeDeErrorDeQueNoHayAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenQueNoExistenAutosEnMantenimiento();
        whenAccedeALaPantallaDeAutosEnMantenimiento(request);
        thenVisualizaLaVista(this.modelAndView, "autos-en-mantenimiento");
        thenVisualizaUnMensajeDeError("No hay autos para mantenimiento actualmente", "error_no_hay_en_mantenimiento");
    }

    private void givenQueNoExistenAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        doThrow(NoHayAutosEnMantenientoException.class).when(servicioDeAuto).obtenerAutosEnMantenimiento();
    }

    @Test
    public void alAccederALaPantallaDeAutosEnRevisionPuedaVisualizarAutosEnRevision() throws NoHayAutosParaRevision {
        givenExistenAutosEnRevision(5, Situacion.EN_REVISION);
        whenAccedeALaPantallaDeAutosEnRevision(request);
        thenVisualizaLosAutosEnRevision(this.modelAndView);
    }

    private void givenExistenAutosEnRevision(int cantidad, Situacion enRevision) throws NoHayAutosParaRevision {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enRevision);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnRevision()).thenReturn(autoList);
    }

    private void whenAccedeALaPantallaDeAutosEnRevision(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosEnRevision(request);
    }

    private void thenVisualizaLosAutosEnRevision(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos-en-revision");
        assertThat(modelAndView.getModel().get("autos_en_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("autos_en_revision")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autos_en_revision");
        assertThat(autoList).hasSize(5);
    }

    @Test
    public void alAccederALaPantallaDeAutosEnRevisionVisualiceUnMensajeDeErrorDeQueNoHayAutosEnRevision() throws NoHayAutosParaRevision {
        givenQueNoExistenAutosEnRevision();
        whenAccedeALaPantallaDeAutosEnRevision(request);
        thenVisualizaLaVista(this.modelAndView, "autos-en-revision");
        thenVisualizaUnMensajeDeError("No hay autos para revision actualmente", "error_no_hay_en_revision");
    }

    private void givenQueNoExistenAutosEnRevision() throws NoHayAutosParaRevision {
        doThrow(NoHayAutosParaRevision.class).when(servicioDeAuto).obtenerAutosEnRevision();
    }

    @Test
    public void alAccederALaPantallaDeClientesSuscriptosDebeVisualizarUnaListaConLosClientesSusCriptos() throws NoHayClientesSuscriptos {
        givenExistenClientesSusCriptos(5);
        whenAccedeALaPantallaDeClientesSuscriptos(request);
        thenVisualizaLaVista(this.modelAndView, "clientes-suscriptos");
        thenVisualizaLosClientesSuscriptos(this.modelAndView);
    }

    private void givenExistenClientesSusCriptos(int cantidad) throws NoHayClientesSuscriptos {
        List<Suscripcion> clientes_suscriptos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setUsuario(new Usuario());
            clientes_suscriptos.add(suscripcion);
        }
        when(servicioSuscripcion.obtenerClientesSuscriptos()).thenReturn(clientes_suscriptos);
    }

    private void whenAccedeALaPantallaDeClientesSuscriptos(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarClientesSuscriptos(request);
    }

    private void thenVisualizaLosClientesSuscriptos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isNotNull();
        assertThat(modelAndView.getModel().get("clientes_suscriptos")).isInstanceOf(List.class);
        List<Suscripcion> suscripcions = (List<Suscripcion>) modelAndView.getModel().get("clientes_suscriptos");
        assertThat(suscripcions).hasSize(5);
    }

    @Test
    public void alAccederALaPantallaDeClientesSusCriptosDebeVisualizarUnMensajeDeErrorDeQueNoHayClientesSuscriptos() throws NoHayClientesSuscriptos {
        givenQueNoExistenClientesSuscriptos();
        whenAccedeALaPantallaDeClientesSuscriptos(request);
        thenVisualizaLaVista(this.modelAndView, "clientes-suscriptos");
        thenVisualizaUnMensajeDeError("No hay clientes suscriptos actualmente", "error_no_hay_clientes_suscriptos");
    }

    private void givenQueNoExistenClientesSuscriptos() throws NoHayClientesSuscriptos {
        doThrow(NoHayClientesSuscriptos.class).when(servicioSuscripcion).obtenerClientesSuscriptos();
    }

    @Test
    public void alAccederALaPantallaDeClientesNoSuscriptosDebeVisualizarUnaListaConLosClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        givenQueExistenClientesNoSuscriptos(5);
        whenAccedeALaPantallaDeClientesNoSuscriptos(request);
        thenVisualizaLaVista(this.modelAndView, "clientes-no-suscriptos");
        thenVisualizaLosClientesNoSuscriptos(this.modelAndView);
    }

    private void givenQueExistenClientesNoSuscriptos(int cantidad) throws NoHayClientesNoSuscriptos {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuarioList.add(usuario);
        }
        when(servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos()).thenReturn(usuarioList);
    }

    private void whenAccedeALaPantallaDeClientesNoSuscriptos(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarClientesNoSuscriptos(request);
    }

    private void thenVisualizaLosClientesNoSuscriptos(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("clientes_no_suscriptos")).isNotNull();
        assertThat(modelAndView.getModel().get("clientes_no_suscriptos")).isInstanceOf(List.class);
        List<Usuario> usuarioList = (List<Usuario>) modelAndView.getModel().get("clientes_no_suscriptos");
        assertThat(usuarioList).hasSize(5);
    }

    @Test
    public void alAccederALaPantallaDeClientesNoSuscriptosDebeVisualizarunMensajeDeErrorCuandoNoHayanClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        givenQueNoExistenClientesConSuscripcion();
        whenAccedeALaPantallaDeClientesNoSuscriptos(request);
        thenVisualizaLaVista(this.modelAndView, "clientes-no-suscriptos");
        thenVisualizaUnMensajeDeError("No hay clientes sin suscripcion actualmente", "error_no_hay_sin_suscripcion");
    }

    private void givenQueNoExistenClientesConSuscripcion() throws NoHayClientesNoSuscriptos {
        doThrow(NoHayClientesNoSuscriptos.class).when(servicioSuscripcion).obtenerListaDeUsuariosNoSuscriptos();
    }

    @Test
    public void alAccederALaPantallaDeEmpleadosEncargadosDeDevolucionVisualizaUnaListaDeLosEmpleados() throws NoHayEmpladosException {
        givenQueExitenEmpleados("encargado", 5);
        whenAccedeALaPantallaDeEncargados(request);
        thenVisualizaLaVista(this.modelAndView, "encargados-devolucion");
        thenVisualizaLosEmpleadosEncargados(this.modelAndView);
    }

    private void givenQueExitenEmpleados(String rol, int cantidad) throws NoHayEmpladosException {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(rol);
            usuarioList.add(usuario);
        }
        when(servicioUsuario.obtenerListaDeUsuariosPorRol(rol)).thenReturn(usuarioList);
    }

    private void whenAccedeALaPantallaDeEncargados(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarEncargadosDeDevolucion(request);
    }

    private void thenVisualizaLosEmpleadosEncargados(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("encargados_devolucion")).isNotNull();
        assertThat(modelAndView.getModel().get("encargados_devolucion")).isInstanceOf(List.class);
        List<Usuario> usuarioList = (List<Usuario>) modelAndView.getModel().get("encargados_devolucion");
        assertThat(usuarioList).hasSize(5);
    }
}
