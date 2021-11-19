package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestControladorAdministradorSeccionEmpleados {

    private HttpServletRequest request;

    private HttpSession session;

    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;

    private ServicioUsuario servicioUsuario;

    private static final String ADMIN = "admin";
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion, servicioUsuario);
        modelAndView = new ModelAndView();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void queElUsuarioAdministradroPuedaAccederALaVistaDeEmpleados() {
        HttpServletRequest administrador = givenExisteUnUsuarioAdministrador(ADMIN);
        whenAccedeALaVistaDeEmpleados(administrador);
        thenSeMuestraLaVista("encargados-devolucion", this.modelAndView);
    }

    private HttpServletRequest givenExisteUnUsuarioAdministrador(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(rol);
        return request;
    }

    private void whenAccedeALaVistaDeEmpleados(HttpServletRequest administrador) {
        this.modelAndView = controlador.mostrarEmpleadosEncargadosDeDevolucion(administrador);
    }

    private void thenSeMuestraLaVista(String vista, ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }

}
