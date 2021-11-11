package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
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
import static org.mockito.Mockito.*;

public class testControladorAdministradorSeccionClientes {

    private static final String ADMIN = "admin";
    private static final String CLIENTE = "cliente";
    private static final Long BASICO = 1L;
    private static final Long ORO = 2L;
    private static final Long DIAMANTE = 3L;
    private HttpSession session;
    private HttpServletRequest servletRequest;
    private ServicioSuscripcion servicioSuscripcion;
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;
    private TipoSuscripcion basico;
    private TipoSuscripcion oro;
    private TipoSuscripcion diamante;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        servletRequest = mock(HttpServletRequest.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion);
        modelAndView = new ModelAndView();
        basico = new TipoSuscripcion();
        basico.setId(BASICO);
        basico.setDescripcion("basico");
        oro.setId(ORO);
        oro.setDescripcion("oro");
        diamante.setId(DIAMANTE);
        diamante.setDescripcion("diamante");
    }

    @Test
    public void queElUsuarioAdministradorPuedaAccederALaVistaSuscriptos() {
        HttpServletRequest usuarioAdministrador = givenQueExisteUnUsuario(ADMIN);
        whenAccedeALaVistaDeSuscriptos(usuarioAdministrador);
        thenSeMuestraLaVistaDeClientesSuscriptos(this.modelAndView, usuarioAdministrador);
    }

    private HttpServletRequest givenQueExisteUnUsuario(String admin) {
        return null;
    }

    @Test
    public void queUnUsuarioInvitadoNoPuedaAccederALaVistaSuscriptosYSeMuestreUnMensajeDeError() {
        HttpServletRequest usuarioCliente = givenQueExisteUnUsuario(CLIENTE);
        whenAccedeALaVistaDeSuscriptos(usuarioCliente);
        thenSeMuestraLaVistaDeClientesSusCriptosConMsjDeError(this.modelAndView, "No tienes los permisos necesarios para acceder a esta pagina");
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
