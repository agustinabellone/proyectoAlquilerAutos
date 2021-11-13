package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testControladorAdministradorSeccionClientes {

    private static final String ADMIN = "admin";
    private static final String INVITADO = "invitado";
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    private ControladorAdministrador controlador = new ControladorAdministrador();
    private ModelAndView modelAndView = new ModelAndView();
    private TipoSuscripcion basico = new TipoSuscripcion(1l);
    private TipoSuscripcion oro = new TipoSuscripcion(2l);
    private TipoSuscripcion diamante = new TipoSuscripcion(3l);

    @Test
    public void queElAdminstradorPuedaAccederALaVistaDeLosClientesSuscriptos() {
        HttpServletRequest administrador = givenQueExisteUnUsuarioConRol(ADMIN);
        whenIngresaALaSeccionDeClienteSuscriptosEl(administrador);
        thenSeMuestraLaVistaDeLosClientesSuscritos(this.modelAndView);
    }

    @Test
    public void queUnUsuarioNoAdministradorNoPuedaAccederALaVistaDeClientesSuscriptos() {
        HttpServletRequest administrador = givenQueExisteUnUsuarioConRol(INVITADO);
        whenIngresaALaSeccionDeClienteSuscriptosEl(administrador);
        thenLoEnviaAlLoginConMensajeDeError(this.modelAndView);
    }

    @Test
    public void queElUsuarioAdmistradorPuedaVerUnaListaDeLosClientesQueEstanSuscriptos() {

    }


    private HttpServletRequest givenQueExisteUnUsuarioConRol(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(anyString())).thenReturn(rol);
        return request;
    }

    private void givenIngresaALaVistaDeLosCLientesSuscriptos(HttpServletRequest administrador) {
        whenIngresaALaSeccionDeClienteSuscriptosEl(administrador);
    }

    private void whenIngresaALaSeccionDeClienteSuscriptosEl(HttpServletRequest administrador) {
        this.modelAndView = controlador.mostrarClientesSuscriptos(administrador);
    }

    private void thenSeMuestraLaVistaDeLosClientesSuscritos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
    }

    private void thenLoEnviaAlLoginConMensajeDeError(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().get("errorSinPermisos")).isEqualTo("No tienes los permisos necesarios para acceder a esta pagina");
    }
}
