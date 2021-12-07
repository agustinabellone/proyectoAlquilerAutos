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

public class TestControladorMecanico {

    private String MECANICO = "mecanico";
    private ModelAndView modelAndView;
    private HttpServletRequest request;
    private HttpSession session;
    private ControladorMecanico controlador;
    private ServicioDeAuto servicio;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicio = mock(ServicioDeAuto.class);
        modelAndView = new ModelAndView();
        controlador = new ControladorMecanico(servicio);
        request = givenExisteUnUsuarioConRol(MECANICO);
    }

    @Test
    public void queUnMecanicoPuedaAccederASuPantallaPrincipal() {
        whenAccedeAlaPantallaPrincipal(request);
        thenSeMuestraLaVista("redirect:/pantalla-principal", this.modelAndView);
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(String mecanico) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(mecanico);
        when(request.getSession().getAttribute("nombre")).thenReturn("mecanico");
        return request;
    }

    private void whenAccedeAlaPantallaPrincipal(HttpServletRequest mecanico) {
        this.modelAndView = controlador.irAPantallaPrincipal(mecanico);
    }

    private void thenSeMuestraLaVista(String vista, ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }

    @Test
    public void queElMecanicoCuandoAccedeALaPantallaPrincipalVeaUnaListaConLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosEnMantenimiento(5, Situacion.EN_MANTENIMIENTO);
        whenEntraAlaPantallaPrincipal(request);
        thenSeMuestraLaVista("pantalla-principal-mecanico",this.modelAndView);
        thenSeMuestraUnaListaConAutosEnMantenimientp(this.modelAndView);
    }

    private void givenAccedioAlPanelPincipal(HttpServletRequest request) {
        whenAccedeAlaPantallaPrincipal(request);
    }

    private void givenExistenAutosEnMantenimiento(int cantidad, Situacion enMantenimiento) throws NoHayAutosEnMantenientoException {
        List<Auto> paraMantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enMantenimiento);
            paraMantenimiento.add(auto);
        }
        when(servicio.obtenerAutosEnMantenimiento()).thenReturn(paraMantenimiento);
    }

    private void whenEntraAlaPantallaPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosParaMantenimiento(request);
    }

    private void thenSeMuestraUnaListaConAutosEnMantenimientp(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("lista_autos_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("lista_autos_mantenimiento")).isInstanceOf(List.class);
        List<Auto> enMantenimiento = (List<Auto>) modelAndView.getModel().get("lista_autos_mantenimiento");
        assertThat(enMantenimiento).hasSize(5);
    }
}
