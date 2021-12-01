package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestControladorEnviarAutoAMantenimiento {

    private HttpServletRequest request;
    private HttpSession httpSession;
    private ControladorAdministrador controladorAdministrador;
    private ModelAndView modelAndView;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    @Before
    public void init() {
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        servicioUsuario = mock(ServicioUsuario.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        controladorAdministrador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion, servicioUsuario);
        modelAndView = new ModelAndView();
    }

    @Test
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimientoSoloDesdeLaVistaDeAutosDisponibles() {
        Long id_auto = givenExisteUnAuto(Situacion.DISPONIBLE);
        HttpServletRequest administrador = givenExisteUnUsuarioConRol(Rol.ADMIN);
        whenEnviaUnAutoAMantenimiento(id_auto, administrador);
        thenSeMuestrsUnMensajeDeExito(this.modelAndView);
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(Rol admin) {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("rol")).thenReturn(admin);
        return request;
    }

    private Long givenExisteUnAuto(Situacion disponible) {
        Auto auto = new Auto();
        auto.setSituacion(disponible);
        return auto.getId();
    }

    private void whenEnviaUnAutoAMantenimiento(Long id_auto, HttpServletRequest administrador) {
        this.modelAndView = controladorAdministrador.enviarAMantenimiento(id_auto, administrador);
    }

    private void thenSeMuestrsUnMensajeDeExito(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos_disponibles");
        assertThat(modelAndView.getModel().get("mensaje_exito")).isEqualTo("Se envio un auto correctamente a mantenimiento");
    }
}
