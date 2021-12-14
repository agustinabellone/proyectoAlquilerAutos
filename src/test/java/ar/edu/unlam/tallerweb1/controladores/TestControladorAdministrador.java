package ar.edu.unlam.tallerweb1.controladores;

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

}
