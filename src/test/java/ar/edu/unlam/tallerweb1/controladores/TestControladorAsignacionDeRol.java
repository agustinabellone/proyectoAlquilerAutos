package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestControladorAsignacionDeRol {

    private static final String ADMIN = "admin";
    private ModelAndView modelAndView = new ModelAndView();
    private ServicioAlquiler servicioAlquiler = mock(ServicioAlquiler.class);
    private ServicioDeAuto servicioDeAuto = mock(ServicioDeAuto.class);
    private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
    private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
    private ControladorAdministrador controladorAdministrador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion, servicioUsuario);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void queElUsuarioConRolDeAdministradorPuedaAccederAlASeccionDeAsignarRoles() {
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(ADMIN);
        whenAccedeALaVistaDeAsignacionDeRoles(request);
        thenSeMuestraLaVistaCorrectamente(this.modelAndView, request);
    }

    private HttpServletRequest givenExisteUnUsuarioConRolDe(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(rol);
        return request;
    }

    private void whenAccedeALaVistaDeAsignacionDeRoles(HttpServletRequest request) {
        this.modelAndView = controladorAdministrador.mostrarAsignacionDeRol(request);
    }

    private void thenSeMuestraLaVistaCorrectamente(ModelAndView modelAndView, HttpServletRequest request) {
        assertThat(this.modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo("admin");
    }

    @Test
    public void queSePuedaVerUnaListaDeLosUsuariosQueRequierenAsignacionDeRol() throws NoHayUsuariosPendientesDeRol {
        givenExistenUsuariosPendientesDeRol(5);
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(ADMIN);
        whenAccedeALaVistaDeAsignacionDeRoles(request);
        whenObtieneLaListaDeLosUsuariosPendientesDeRol();
        thenSeMuestraLaVistaCorrectamente(this.modelAndView, request);
        thenSeMuestraLaListaConLosUsuariosPendientesDeRol(this.modelAndView);
    }

    private void givenExistenUsuariosPendientesDeRol(int cantidad) throws NoHayUsuariosPendientesDeRol {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre("Humano" + i);
            usuarioList.add(usuario);
        }
        when(servicioUsuario.obtenerListaDeUsuariosPendienteDeRol()).thenReturn(usuarioList);
    }

    private List<Usuario> whenObtieneLaListaDeLosUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        return controladorAdministrador.obtenerListaDeUsuariosConRolPendiente();
    }

    private void thenSeMuestraLaListaConLosUsuariosPendientesDeRol(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
        assertThat(modelAndView.getModel().get("pendientes_de_rol")).isNotNull();
        assertThat(modelAndView.getModel().get("pendientes_de_rol")).isInstanceOf(List.class);
        List<Usuario> usuariosPendientesDeRol = (List<Usuario>) modelAndView.getModel().get("pendientes_de_rol");
        assertThat(usuariosPendientesDeRol).hasSize(5);
    }

    @Test(expected = NoHayUsuariosPendientesDeRol.class)
    public void queEnvieALaVistaDeAsignacionDeRolConUnMensajeDeErrorPorqueNoHayUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        givenNoExistenUsuariosPendientesDeRol();
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(ADMIN);
        whenAccedeALaVistaDeAsignacionDeRoles(request);
        whenObtieneLaListaDeLosUsuariosPendientesDeRol();
        thenSeMuestraLaVistaConMensajeDeError(this.modelAndView, "No hay usuarios pendientes de rol");
    }

    private void givenNoExistenUsuariosPendientesDeRol() throws NoHayUsuariosPendientesDeRol {
        doThrow(NoHayUsuariosPendientesDeRol.class).when(servicioUsuario).obtenerListaDeUsuariosPendienteDeRol();
    }

    private void thenSeMuestraLaVistaConMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
        assertThat(modelAndView.getModel().get("error_no_hay_pendientes_de_rol")).isEqualTo(error);
    }
}
