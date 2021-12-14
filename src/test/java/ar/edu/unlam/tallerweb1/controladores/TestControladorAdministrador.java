package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestControladorAdministrador {

    private static final String ADMINISTRADOR = "admin";
    private HttpServletRequest request;
    private HttpSession session;
    private ControladorAdministrador controlador;
    private ServicioDeAuto servicioDeAuto;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorAdministrador(servicioDeAuto);
        request = givenExsiteUnUsuarioConRol(ADMINISTRADOR);
        modelAndView = new ModelAndView();
    }

    private HttpServletRequest givenExsiteUnUsuarioConRol(String administrador) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(administrador);
        return request;
    }

    @Test
    public void alIngresarElAdministradorASuPantallaPrincipalVisualizaLosAutosActualmenteAlquilados() {
        givenExistenAutosAlquilados(5, Situacion.OCUPADO);
        whenAccedeALaPantallaPrincipal(request);
        thenVisualizaLOsAutosAlquilados(this.modelAndView);
    }

    private void givenExistenAutosAlquilados(int cantidad, Situacion ocupado) {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(ocupado);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosAlquilados()).thenReturn(autoList);
    }

    private void thenVisualizaLOsAutosAlquilados(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
        assertThat(modelAndView.getModel().get("autos_alquilados")).isNotNull();
        assertThat(modelAndView.getModel().get("autos_alquilados")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autos_alquilados");
        assertThat(autoList).hasSize(5);
    }

    private void whenAccedeALaPantallaPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.irAlPanelPrincipal(request);
    }

    @Test
    public void alAccederALaPantallaDeAutosDisponiblesPuedaVisualizarUnaListaDeAutosDisponiblesParaAlquilar() {
        givenExistenAutosDisponiblesParaAlquilar(5, Situacion.DISPONIBLE);
        whenAccedoALaPantallaDeAutosDisponibles(request);
        thenVisualizaLosAutosDisponibles(this.modelAndView);
    }

    private void givenExistenAutosDisponiblesParaAlquilar(int cantidad, Situacion disponible) {
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
    public void alAccederALaPantallaDeAutosEnRevisionPuedaVisualizarAutosEnRevision(){
        givenExistenAutosEnRevision(5,Situacion.EN_REVISION);
        whenAccedeALaPantallaDeAutosEnRevision(request);
        thenVisualizaLosAutosEnRevision(this.modelAndView);
    }

    private void givenExistenAutosEnRevision(int cantidad, Situacion enRevision) {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enRevision);
            autoList.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnRevision()).thenReturn(autoList)
    }

    private void whenAccedeALaPantallaDeAutosEnRevision(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosEnRevision(request);
    }
}
