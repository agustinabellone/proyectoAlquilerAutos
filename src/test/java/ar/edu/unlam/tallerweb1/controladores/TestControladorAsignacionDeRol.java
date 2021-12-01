package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.Exceptions.NoSeAsignoElRol;
import ar.edu.unlam.tallerweb1.modelo.Rol;
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
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
        whenAccedeALaVistaDeAsignacionDeRoles(request);
        thenSeMuestraLaVistaCorrectamente(this.modelAndView, request);
    }

    private HttpServletRequest givenExisteUnUsuarioConRolDe(Rol rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(rol);
        return request;
    }

    private void whenAccedeALaVistaDeAsignacionDeRoles(HttpServletRequest request) {
        this.modelAndView = controladorAdministrador.mostrarAsignacionDeRol(request);
    }

    private void thenSeMuestraLaVistaCorrectamente(ModelAndView modelAndView, HttpServletRequest request) {
        assertThat(modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo(Rol.ADMIN);
    }

    @Test
    public void queSePuedaVerUnaListaDeLosUsuariosQueRequierenAsignacionDeRol() throws NoHayUsuariosPendientesDeRol {
        givenExistenUsuariosPendientesDeRol(5);
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
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
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
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

    @Test
    public void queUnAdministradorPuedaConfirmarLaSeleccionDelRolDeUnEmpleado() throws NoHayUsuariosPendientesDeRol, NoSeAsignoElRol {
        Long pendienteDeRol = givenExisteUnUsuarioPendienteDeRol();
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
        givenAccedeAlaVistaDeAsignacionDeRol(request);
        whenSeleccionaElRolDelEmpleado(Rol.MECANICO.ordinal(), pendienteDeRol, request);
        thenSeMuestraLaVistaCorrectamente(this.modelAndView, request);
        thenObtieneElRolCorrectamente(this.modelAndView, Rol.MECANICO, pendienteDeRol);
    }

    private Long givenExisteUnUsuarioPendienteDeRol() throws NoSeAsignoElRol {
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.EMPLEADO);
        usuario.setId(1l);
        usuario.setEmail("eze@tallerweb.com");
        usuario.setClave("12345678");
        usuario.setNombre("eze");
        when(servicioUsuario.buscarPorId(usuario.getId())).thenReturn(usuario);
        usuario.setRol(Rol.MECANICO);
        when(servicioUsuario.asignarRol(any(),anyLong())).thenReturn(usuario);
        return usuario.getId();
    }

    private void whenSeleccionaElRolDelEmpleado(Integer rol, Long pendienteDeRol, HttpServletRequest request) {
        this.modelAndView = controladorAdministrador.asignarRolAlEmpleado(rol, pendienteDeRol, request);
    }

    private void givenAccedeAlaVistaDeAsignacionDeRol(HttpServletRequest request) {
        whenAccedeALaVistaDeAsignacionDeRoles(request);
    }

    private void thenObtieneElRolCorrectamente(ModelAndView modelAndView, Rol mecanico, Long pendienteDeRol) {
        assertThat(modelAndView.getModel().get("rol")).isNotNull();
        assertThat(modelAndView.getModel().get("rol")).isInstanceOf(Rol.class);
        assertThat(modelAndView.getModel().get("rol")).isEqualTo(mecanico);
        assertThat(modelAndView.getModel().get("usuario")).isNotNull();
        assertThat(modelAndView.getModel().get("usuario")).isInstanceOf(Usuario.class);
        Usuario usuario = (Usuario) modelAndView.getModel().get("usuario");
        assertThat(usuario.getId()).isEqualTo(pendienteDeRol);
        assertThat(usuario.getRol()).isEqualTo(mecanico);

    }

    @Test
    public void queElAdministradorVeaUnMensajeDeErrorPoequeNoPudoAsignarElRolCorrectamente() throws NoSeAsignoElRol {
        givenNoExisteUnUsuarioPendienteDeRol();
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
        givenAccedeAlaVistaDeAsignacionDeRol(request);
        whenSeleccionaElRolDelEmpleado(Rol.MECANICO.ordinal(), null, request);
        thenSeMuestraUnMensajeDeError(this.modelAndView, "No se pudo asignar el rol correctamente");
    }

    private void givenNoExisteUnUsuarioPendienteDeRol() throws NoSeAsignoElRol {
        when(servicioUsuario.buscarPorId(anyLong())).thenReturn(null);
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.MECANICO);
        when(servicioUsuario.asignarRol(any(), anyLong())).thenReturn(usuario);
    }

    private void thenSeMuestraUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
        assertThat(modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
    }

    @Test(expected = NoSeAsignoElRol.class)
    public void queElAdministradorNoPuedaAsignarUnRolYSeMuestreUnMensajeDeError() throws NoSeAsignoElRol {
        givenNoSeAsignaElRolCorrectamente();
        Long usuario = givenExisteUnUsuarioPendienteDeRol();
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(Rol.ADMIN);
        givenAccedeAlaVistaDeAsignacionDeRol(request);
        whenSeleccionaElRolDelEmpleado(Rol.MECANICO.ordinal(), usuario, request);
        thenSeMuestraUnMensajeDeError(this.modelAndView, "No se pudo asignar el rol correctamente");
    }

    private void givenNoSeAsignaElRolCorrectamente() throws NoSeAsignoElRol {
        doThrow(NoSeAsignoElRol.class).when(servicioUsuario).asignarRol(any(), anyLong());
    }

}
