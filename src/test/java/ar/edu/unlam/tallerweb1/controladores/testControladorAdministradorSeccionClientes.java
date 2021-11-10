package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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

public class testControladorAdministradorSeccionClientes {

    private static final String ADMIN = "admin";
    private static final String CLIENTE = "cliente";
    private static final Long BASICO = 1L;
    private HttpSession session;
    private HttpServletRequest servletRequest;
    private ServicioUsuario servicioUsuario;
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;
    private TipoSuscripcion basico;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        servletRequest = mock(HttpServletRequest.class);
        servicioUsuario = mock(ServicioUsuario.class);
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioUsuario);
        modelAndView = new ModelAndView();
        basico = new TipoSuscripcion();
        basico.setId(BASICO);
    }

    @Test
    public void queElUsuarioAdministradorPuedaAccederALaVistaSuscriptos() {
        HttpServletRequest usuarioAdministrador = givenQueExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeSuscriptos(usuarioAdministrador);
        thenSeMuestraLaVistaDeClientesSuscriptos(this.modelAndView, usuarioAdministrador);
    }

    @Test
    public void queUnUsuarioInvitadoNoPuedaAccederALaVistaSuscriptosYSeMuestreUnMensajeDeError() {
        HttpServletRequest usuarioCliente = givenQueExisteUnUsuario(CLIENTE);
        whenAccedeALaVistaDeSuscriptos(usuarioCliente);
        thenSeMuestraLaVistaDeClientesSusCriptosConMsjDeError(this.modelAndView, "No tienes los permisos necesarios para acceder a esta pagina");
    }

    @Test
    public void queElUsuarioAdministradorPuedaVerUnListaDeLosClientesSuscriptosAlPlanBasico() {
        Suscripcion suscripcion = givenQueExisteUnaSuscripcionConPlanBasico(basico);
        givenQueExistenUsuariosSuscriptosAlPlan(suscripcion, 5);
        HttpServletRequest usuarioAdministrador = givenQueExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeSuscriptos(usuarioAdministrador);
        List<Usuario> usuariosSuscriptos = whenMuestroLosUsuariosEnUnaLista();
        thenSeMuestraLaListaConLos(usuariosSuscriptos,suscripcion,5);
    }

    private void thenSeMuestraLaListaConLos(List<Usuario> usuariosSuscriptos, Suscripcion suscripcion, int cantidad_esperada) {
        assertThat(usuariosSuscriptos).hasSize(cantidad_esperada);
        for (Usuario usuario: usuariosSuscriptos) {
            assertThat(usuario.getId()).isEqualTo(suscripcion.getUsuario().getId());
        }
    }

    private List<Usuario> whenMuestroLosUsuariosEnUnaLista() {
        return controlador.obtenerListaDeUsuariosSuscriptosAlPlanBasico();
    }

    private void givenQueExistenUsuariosSuscriptosAlPlan(Suscripcion suscripcion, int cantidad) {
        List<Usuario> listaDeUsuariosSuscriptos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            suscripcion.setUsuario(usuario);
            listaDeUsuariosSuscriptos.add(usuario);
        }
        when(servicioUsuario.obtenerUsuariosSuscriptosAlPlanBasico()).thenReturn(listaDeUsuariosSuscriptos);
    }

    private Suscripcion givenQueExisteUnaSuscripcionConPlanBasico(TipoSuscripcion basico) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setTipoSuscripcion(basico);
        return suscripcion;
    }

    private HttpServletRequest givenQueExisteUnUsuario(String rol) {
        when(servletRequest.getSession()).thenReturn(session);
        when(servletRequest.getSession().getAttribute("rol")).thenReturn(rol);
        return servletRequest;
    }

    private void whenAccedeALaVistaDeSuscriptos(HttpServletRequest usuarioAdministrador) {
        this.modelAndView = controlador.mostrarClientesSuscriptos(usuarioAdministrador);
    }

    private void thenSeMuestraLaVistaDeClientesSuscriptos(ModelAndView modelAndView, HttpServletRequest usuarioAdministrador) {
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
        assertThat(usuarioAdministrador.getSession().getAttribute("rol")).isEqualTo("admin");
    }

    private void thenSeMuestraLaVistaDeClientesSusCriptosConMsjDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().get("errorSinPermisos")).isEqualTo(error);
    }
}
