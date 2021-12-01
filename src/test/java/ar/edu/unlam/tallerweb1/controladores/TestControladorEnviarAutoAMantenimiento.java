package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.Exceptions.NoSeAsignoElRol;
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

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
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimientoSoloDesdeLaVistaDeAutosDisponibles() throws AutoNoExistente, NoEnviaAutoAMantenimiento {
        Long id_auto = givenExisteUnAuto(Situacion.DISPONIBLE);
        HttpServletRequest administrador = givenExisteUnUsuarioConRol(Rol.ADMIN);
        givenAccedeALaVistaDeAutosDisponibles(administrador);
        whenEnviaUnAutoAMantenimiento(id_auto, administrador);
        thenSeMuestrsUnMensajeDeExito(this.modelAndView, id_auto);
    }

    private void givenAccedeALaVistaDeAutosDisponibles(HttpServletRequest administrador) {
        controladorAdministrador.mostrarAutosDisponibles(administrador);
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(Rol admin) {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("rol")).thenReturn(admin);
        return request;
    }

    private Long givenExisteUnAuto(Situacion situacion) throws AutoNoExistente, NoEnviaAutoAMantenimiento {
        Auto auto = new Auto();
        auto.setId(1l);
        auto.setSituacion(situacion);
        when(servicioDeAuto.buscarAutoPorId(anyLong())).thenReturn(auto);
        when(servicioDeAuto.enviarAMantenimiento(auto.getId())).thenReturn(auto);
        return auto.getId();
    }

    private void whenEnviaUnAutoAMantenimiento(Long id_auto, HttpServletRequest administrador) throws AutoNoExistente {
        this.modelAndView = controladorAdministrador.enviarAMantenimiento(id_auto, administrador);
    }

    private void thenSeMuestrsUnMensajeDeExito(ModelAndView modelAndView, Long id_auto) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos_disponibles");
        assertThat(modelAndView.getModel().get("mensaje_exito")).isEqualTo("Se envio un auto correctamente a mantenimiento");
        assertThat(modelAndView.getModel().get("autoAEnviar")).isNotNull();
        assertThat(modelAndView.getModel().get("autoAEnviar")).isInstanceOf(Auto.class);
        Auto auto = (Auto) modelAndView.getModel().get("autoAEnviar");
        assertThat(auto.getId()).isEqualTo(id_auto);
        assertThat(auto.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
    }

    @Test
    public void queElAdministradorVeaUnMensajeDeErrorAlEnviarUnAutoAMantenimientoQueNoEstaDisponible() throws AutoNoExistente, NoEnviaAutoAMantenimiento {
        Long id_auto = givenExisteUnAutoOcupado(Situacion.OCUPADO);
        HttpServletRequest adminisrtrador = givenExisteUnUsuarioConRol(Rol.ADMIN);
        givenAccedeALaVistaDeAutosDisponibles(adminisrtrador);
        whenEnviaUnAutoAMantenimiento(id_auto,adminisrtrador);
        thenEnviaError(this.modelAndView);
    }

    private void thenEnviaError(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("autos_disponibles");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("No se envio el auto a mantenimiento porque esta ocupado");
        assertThat(modelAndView.getModel().get("auto")).isNotNull();
        assertThat(modelAndView.getModel().get("auto")).isInstanceOf(Auto.class);
        Auto auto = (Auto) modelAndView.getModel().get("auto");
        assertThat(auto.getSituacion()).isEqualTo(Situacion.OCUPADO);
    }

    private Long givenExisteUnAutoOcupado(Situacion ocupado) throws AutoNoExistente, NoEnviaAutoAMantenimiento {
        Auto auto = new Auto();
        auto.setId(1l);
        auto.setSituacion(ocupado);
        when(servicioDeAuto.buscarAutoPorId(auto.getId())).thenReturn(auto);
        doThrow(NoEnviaAutoAMantenimiento.class).when(servicioDeAuto).enviarAMantenimiento(auto.getId());
        return auto.getId();
    }
}
