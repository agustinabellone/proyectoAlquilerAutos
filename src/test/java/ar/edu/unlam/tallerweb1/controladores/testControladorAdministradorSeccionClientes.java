package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
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
import static org.mockito.Mockito.*;

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
        basico.setDescripcion("basico");
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
    public void queElUsuarioAdministradorPuedaVerUnListaDeLosClientesSuscriptosAlPlanBasico() throws NoHayClientesSuscriptosAlPlanBasico {
        Suscripcion suscripcion = givenQueExisteUnaSuscripcionConPlanBasico(basico);
        givenQueExistenUsuariosSuscriptosAlPlan(suscripcion, 5);
        HttpServletRequest usuarioAdministrador = givenQueExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeSuscriptos(usuarioAdministrador);
        List<Usuario> usuariosSuscriptos = whenMuestroLosUsuariosEnUnaLista();
        thenSeMuestraLaListaConLos(modelAndView, usuariosSuscriptos, suscripcion, 5);
    }

    @Test(expected = NoHayClientesSuscriptosAlPlanBasico.class)
    public void queElUsuarioAdministrdprNoPuedaVerLosClientesSuscriptosAlPlanBasicoPorQueNoHay() throws NoHayClientesSuscriptosAlPlanBasico {
        givenNoExistenUsuariosSuscriptos();
        HttpServletRequest usuarioAdministrador = givenQueExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeSuscriptos(usuarioAdministrador);
        List<Usuario> usuariosSuscriptos = whenMuestroLosUsuariosEnUnaLista();
        thenSeMuestraLaVistaConMensajeDeErrorYLaListaVacia(usuariosSuscriptos, this.modelAndView, "No hay clientes suscriptos actualmente");
    }

    private void givenNoExistenUsuariosSuscriptos() throws NoHayClientesSuscriptosAlPlanBasico {
        when(servicioUsuario.obtenerUsuariosSuscriptosAlPlanBasico()).thenThrow(NoHayClientesSuscriptosAlPlanBasico.class);
    }

    private void givenQueExistenUsuariosSuscriptosAlPlan(Suscripcion suscripcion, int cantidad) throws NoHayClientesSuscriptosAlPlanBasico {
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

    private List<Usuario> whenMuestroLosUsuariosEnUnaLista() throws NoHayClientesSuscriptosAlPlanBasico {
        return controlador.obtenerListaDeUsuariosSuscriptosAlPlanBasico();
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

    private void thenSeMuestraLaListaConLos(ModelAndView modelAndView, List<Usuario> usuariosSuscriptos, Suscripcion suscripcion, int cantidad_esperada) {
        assertThat(usuariosSuscriptos).hasSize(cantidad_esperada);
        assertThat(suscripcion.getTipoSuscripcion().getDescripcion()).isEqualTo("basico");
        for (Usuario usuario : usuariosSuscriptos) {
            assertThat(usuario.getId()).isEqualTo(suscripcion.getUsuario().getId());
        }
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");

        assertThat(modelAndView.getModel().get("clientes_suscriptos_plan_basico")).isNotNull();
        assertThat(modelAndView.getModel().get("clientes_suscriptos_plan_basico")).isInstanceOf(List.class);

        List<Usuario> usuarios = (List<Usuario>) modelAndView.getModel().get("clientes_suscriptos_plan_basico");
        assertThat(usuarios).hasSize(cantidad_esperada);
    }

    private void thenSeMuestraLaVistaConMensajeDeErrorYLaListaVacia(List<Usuario> usuariosSuscriptos, ModelAndView modelAndView, String error) {
        assertThat(usuariosSuscriptos).isNull();
        assertThat(modelAndView.getModel().get("error_no_hay_usuarios_suscriptos")).isEqualTo(error);
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
    }
}
