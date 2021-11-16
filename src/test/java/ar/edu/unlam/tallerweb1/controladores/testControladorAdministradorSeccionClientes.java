package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptos;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
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

public class testControladorAdministradorSeccionClientes {

    private static final String ADMIN = "admin";
    private static final String INVITADO = "invitado";
    private HttpServletRequest request;
    private HttpSession session;
    private ModelAndView modelAndView;
    private Suscripcion suscripcion;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ControladorAdministrador controlador;

    @Before
    public void init(){
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        modelAndView = new ModelAndView();
        suscripcion = new Suscripcion();
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        servicioSuscripcion = mock(ServicioSuscripcion.class);
        controlador = new ControladorAdministrador(servicioAlquiler, servicioDeAuto, servicioSuscripcion);
    }

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
    public void queElUsuarioAdmistradorPuedaVerUnaListaDeLosClientesQueEstanSuscriptos() throws NoHayClientesSuscriptos {
        givenExistenClientesSuscriptos(3);
        HttpServletRequest administrador = givenQueExisteUnUsuarioConRol(ADMIN);
        givenIngresaALaVistaDeLosCLientesSuscriptos(administrador);
        List<Suscripcion> listaDeClientesSuscriptos = whenObtieneLaListaDeLosClientesSuscriptos();
        thenSeMuestraLaVistaConLaLista(this.modelAndView);
    }

    @Test(expected = NoHayClientesSuscriptos.class)
    public void queElUsuarioAdminstradorVeaUnMensajeDeErrorDeQueTodaviaNoHayClientesSuscriptos() throws NoHayClientesSuscriptos {
        givenNoExistenClientesSuscriptos();
        HttpServletRequest administrador = givenQueExisteUnUsuarioConRol(ADMIN);
        givenIngresaALaVistaDeLosCLientesSuscriptos(administrador);
        whenObtieneLaListaDeLosClientesSuscriptos();
        thenSeMuestraLaVistaConMensajeDeError(this.modelAndView, "No hay clientes suscriptos actualmente");
    }

    private void givenNoExistenClientesSuscriptos() throws NoHayClientesSuscriptos {
        doThrow(NoHayClientesSuscriptos.class).when(servicioSuscripcion).obtenerClientesSuscriptos();
    }

    private void thenSeMuestraLaVistaConMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
        assertThat(modelAndView.getModel().get("error_no_hay_clientes_suscriptos")).isEqualTo(error);
    }

    private HttpServletRequest givenQueExisteUnUsuarioConRol(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(anyString())).thenReturn(rol);
        return request;
    }

    private void givenIngresaALaVistaDeLosCLientesSuscriptos(HttpServletRequest administrador) {
        whenIngresaALaSeccionDeClienteSuscriptosEl(administrador);
    }

    private void givenExistenClientesSuscriptos(int cantidad) throws NoHayClientesSuscriptos {
        List<Suscripcion> listaDeUsuariosSuscriptos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            suscripcion.setUsuario(new Usuario());
            listaDeUsuariosSuscriptos.add(suscripcion);
        }
        when(servicioSuscripcion.obtenerClientesSuscriptos()).thenReturn(listaDeUsuariosSuscriptos);
    }

    private void whenIngresaALaSeccionDeClienteSuscriptosEl(HttpServletRequest administrador) {
        this.modelAndView = controlador.mostrarClientesSuscriptos(administrador);
    }

    private List<Suscripcion> whenObtieneLaListaDeLosClientesSuscriptos() throws NoHayClientesSuscriptos {
        return controlador.obtenerListaDeClientesSuscriptos();
    }

    private void thenSeMuestraLaVistaDeLosClientesSuscritos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
    }

    private void thenLoEnviaAlLoginConMensajeDeError(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().get("errorSinPermisos")).isEqualTo("No tienes los permisos necesarios para acceder a esta pagina");
    }

    private void thenSeMuestraLaVistaConLaLista(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("clientes-suscriptos");
        assertThat(modelAndView.getModel().get("lista_de_suscriptos")).isNotNull();
        assertThat(modelAndView.getModel().get("lista_de_suscriptos")).isInstanceOf(List.class);
        List<Suscripcion> usuarios = (List<Suscripcion>) modelAndView.getModel().get("lista_de_suscriptos");
        assertThat(usuarios).hasSize(3);
    }
}
