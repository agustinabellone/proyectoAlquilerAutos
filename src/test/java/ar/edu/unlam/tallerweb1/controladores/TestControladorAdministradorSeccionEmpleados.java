package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayEncargadosException;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TestControladorAdministradorSeccionEmpleados {

    private HttpServletRequest request;
    private HttpSession session;

    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    private static final String INVITADO = "cliente";
    private static final String ADMIN = "admin";

    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        servicioUsuario = mock(ServicioUsuario.class);
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion, servicioUsuario);
        modelAndView = new ModelAndView();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void queElUsuarioAdministradroPuedaAccederALaVistaDeEmpleados() {
        HttpServletRequest administrador = givenExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeEmpleados(administrador);
        thenSeMuestraLaVista("encargados-devolucion", this.modelAndView);
    }

    @Test
    public void queElUsuarioInvitadoNoPuedaAccederALaVistaDeEmpleados() {
        HttpServletRequest cliente = givenExisteUnUsuario(INVITADO);
        whenAccedeALaVistaDeEmpleados(cliente);
        thenSeMuestraLaVista("login", this.modelAndView);
        thenSeMuestraUnMensajeDeError("No tienes los permisos necesarios para acceder a esta pagina", this.modelAndView, "errorSinPermisos");
    }

    @Test
    public void queElUsuarioAdministradorPuedaVerUnaListaDeLosEmpleadosEncargados() throws NoHayEncargadosException {
        givenExisteUnaListaDeEmplados("encargadosDevolucion", 2);
        HttpServletRequest administrador = givenExisteUnUsuario(ADMIN);
        givenAccedeALaVistaDeEmpleados(administrador);
        whenObtieneUnaListaDeUsuarios("encargadosDevoluvion");
        thenSeMuestraLaVista("encargados-devolucion", this.modelAndView);
        thenSeMuestraLaLista(this.modelAndView);
    }

    @Test(expected = NoHayEncargadosException.class)
    public void queElUsuarioAdministradorNoPuedaVeruUnaListaDeEncargadosPorqueNoExisten() throws NoHayEncargadosException {
        givenNoExistenEncargadosDeLaDevolucionDeLosAutos();
        HttpServletRequest administrador = givenExisteUnUsuario(ADMIN);
        givenAccedeALaVistaDeEmpleados(administrador);
        whenObtieneUnaListaDeUsuarios("encargadosDevoluvion");
        thenSeMuestraLaVista("encargados-devolucion", this.modelAndView);
        thenSeMuestraUnMensajeDeError("No hay encargardos de devolucion actualmente", this.modelAndView, "error_no_hay_encargados");
    }

    private HttpServletRequest givenExisteUnUsuario(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(rol);
        return request;
    }

    private void givenAccedeALaVistaDeEmpleados(HttpServletRequest administrador) {
        this.whenAccedeALaVistaDeEmpleados(administrador);
    }

    private void givenExisteUnaListaDeEmplados(String encargadosDevoluvion, int cantidad) throws NoHayEncargadosException {
        List<Usuario> listaDeUsuariosEncargadosDeDevolucion = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol(encargadosDevoluvion);
            listaDeUsuariosEncargadosDeDevolucion.add(usuario);
        }
        when(servicioUsuario.obtenerListaDeUsuariosPorRol(anyString())).thenReturn(listaDeUsuariosEncargadosDeDevolucion);
    }

    private void givenNoExistenEncargadosDeLaDevolucionDeLosAutos() throws NoHayEncargadosException {
        doThrow(NoHayEncargadosException.class).when(servicioUsuario).obtenerListaDeUsuariosPorRol(anyString());
    }

    private void whenAccedeALaVistaDeEmpleados(HttpServletRequest administrador) {
        this.modelAndView = controlador.mostrarEmpleadosEncargadosDeDevolucion(administrador);
    }

    private List<Usuario> whenObtieneUnaListaDeUsuarios(String rol) throws NoHayEncargadosException {
        return controlador.obtenerListaDeUsuariosPorRol(rol);
    }

    private void thenSeMuestraLaVista(String vista, ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }


    private void thenSeMuestraUnMensajeDeError(String error, ModelAndView modelAndView, String nombre_del_error) {
        assertThat(modelAndView.getModel().get(nombre_del_error)).isEqualTo(error);
    }

    private void thenSeMuestraLaLista(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("encargados_devolucion")).isNotNull();
        assertThat(modelAndView.getModel().get("encargados_devolucion")).isInstanceOf(List.class);
        List<Usuario> usuariosEncargados = (List<Usuario>) modelAndView.getModel().get("encargados_devolucion");
        assertThat(usuariosEncargados).hasSize(2);
    }
}
