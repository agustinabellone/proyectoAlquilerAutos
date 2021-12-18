package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.modelo.EstadoUsuario;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TestControladorAsignacionDeRol {
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
    public void queUnAdministradorAlAccederALaPantallaDeAsignacionesDeRolDebeVisualizarUnaListaDeLosEmpleadosQueRequieranUnRol() throws NoHayEmpladosException {
        givenExistenEmpleadosSinRol(5, "empleado");
        whenAccedeAlaPanatallaDeAsignacionDeRol(request);
        thenVisualizaLaVista(this.modelAndView, "asignacion-de-rol");
        thenVisualizaLosEmpleadosPendientesDeRol(this.modelAndView);
    }

    private void givenExistenEmpleadosSinRol(int cantidad, String empleado) throws NoHayEmpladosException {
        List<Usuario> usuarioList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(empleado);
            usuarioList.add(usuario);
        }
        when(servicioUsuario.obtenerListaDeUsuariosPorRol(anyString())).thenReturn(usuarioList);
    }

    private void whenAccedeAlaPanatallaDeAsignacionDeRol(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarEmpleadosPendientesDeRol(request);
    }

    private void thenVisualizaLaVista(ModelAndView modelAndView, String vista) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }

    private void thenVisualizaLosEmpleadosPendientesDeRol(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("empleados_pendientes_de_rol")).isNotNull();
        assertThat(modelAndView.getModel().get("empleados_pendientes_de_rol")).isInstanceOf(List.class);
        List<Usuario> usuarioList = (List<Usuario>) modelAndView.getModel().get("empleados_pendientes_de_rol");
        assertThat(usuarioList).hasSize(5);
    }

    @Test
    public void queUnAdministradorAlAccederALaPantallaDeAsignacionesDeRolDebeVisualizarUnMensajeDeErrorDeQueNoHayPendientesDeRol() throws NoHayEmpladosException {
        givenQueNoExistenUsuariosPendientesDeRol();
        whenAccedeAlaPanatallaDeAsignacionDeRol(request);
        thenVisualizaLaVista(this.modelAndView, "asignacion-de-rol");
        thenVisualizaUnMensajedeError("No hay empleados pendientes de rol actualmente", "error_no_hay_empledos_pendientes_de_rol");
    }

    private void givenQueNoExistenUsuariosPendientesDeRol() throws NoHayEmpladosException {
        doThrow(NoHayEmpladosException.class).when(servicioUsuario).obtenerListaDeUsuariosPorRol(anyString());
    }

    private void thenVisualizaUnMensajedeError(String error, String nombre_error) {
        assertThat(modelAndView.getModel().get(nombre_error)).isEqualTo(error);
    }

    @Test
    public void alSeleccionarUnRolSeMuestraUnMensajeDeExito() throws NoHayEmpladosException {
        Usuario empleado = givenExisteUnEmpleadoParaAsignarleElRol("empleado");
        whenSeLeAsignaElRolAlUsuario(empleado.getId(), request);
        thenVisualizaLaVista(this.modelAndView, "asignacion-de-rol");
        thenVisualizaAlEmpleado(this.modelAndView, empleado);
    }

    private Usuario givenExisteUnEmpleadoParaAsignarleElRol(String empleado) throws NoHayEmpladosException {
        Usuario sinRol = new Usuario();
        sinRol.setId(1l);
        sinRol.setRol(empleado);
        sinRol.setEmail("mecanico@tallerweb.com");
        when(servicioUsuario.buscarPorId(sinRol.getId())).thenReturn(sinRol);
        return sinRol;
    }

    private void whenSeLeAsignaElRolAlUsuario(Long id, HttpServletRequest request) {
        this.modelAndView = controlador.asignarRol(id, request);
    }

    private void thenVisualizaAlEmpleado(ModelAndView modelAndView, Usuario empleado) {
        assertThat(modelAndView.getModel().get("usuario_con_rol_asignado")).isNotNull();
        assertThat(modelAndView.getModel().get("usuario_con_rol_asignado")).isInstanceOf(Usuario.class);
        Usuario conRolAsignado = (Usuario) modelAndView.getModel().get("usuario_con_rol_asignado");
        assertThat(conRolAsignado.getEmail()).isEqualTo(empleado.getEmail());
        assertThat(conRolAsignado.getRol()).isEqualTo(empleado.getRol());
    }
}
