package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class testControladorEnviarAutoAMentenimineto {

    private static final String ADMIN = "admin";
    private static final String OTRO = "otro";
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession sesion = mock(HttpSession.class);
    private ServicioDeAuto servicioDeAuto = mock(ServicioDeAuto.class);
    private ControladorEnviarAutoAMantenimiento controlador = new ControladorEnviarAutoAMantenimiento(servicioDeAuto);
    private ModelAndView modelAndView = new ModelAndView();

    @Test
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimiento() throws AutoNoExistente, AutoYaExistente {
        HttpServletRequest usuarioAdmin = givenUnUsuarioConRol(ADMIN);
        Auto aEnviar = givenUnAuto();
        whenEnvioElAutoAMantenimiento(usuarioAdmin, aEnviar);
        thenElEnvioEsExitoso(this.modelAndView);
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnAutoAMantenimiento() {
        HttpServletRequest usuarioSinRolDeAdmin = givenUnUsuarioConRol(OTRO);
        Auto aEnviar = givenUnAuto();
        whenEnvioElAutoAMantenimiento(usuarioSinRolDeAdmin, aEnviar);
        thenElEnvioFalla(this.modelAndView);
    }

    @Test
    public void queNoSePuedaMandarUnAutoNoExistenteAMantenimiento() throws AutoNoExistente {
        Auto noExistente = givenNoExisteUnAuto();
        HttpServletRequest usuarioAdmin = givenUnUsuarioConRol(ADMIN);
        whenEnvioElAutoAMantenimiento(usuarioAdmin, noExistente);
        thenElEnvioFallaPorQueelAutoNoExiste(this.modelAndView);
    }


    @Test
    public void queNoSePuedaEnviarDosVecesAlMismoAutoAMantenimiento() throws AutoYaExistente {
        Auto existente = givenExisteUnAutoEnMantenimiento();
        HttpServletRequest usuarioAdmin = givenUnUsuarioConRol(ADMIN);
        whenEnvioElAutoAMantenimiento(usuarioAdmin,existente);
        thenElEnvioFallaPorQueElAutoYaExisteEnMantenimiento(this.modelAndView);
    }

    private void thenElEnvioFallaPorQueElAutoYaExisteEnMantenimiento(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No podes enviar un auto que ya esta en mantenimiento");
    }

    private Auto givenExisteUnAutoEnMantenimiento() throws AutoYaExistente {
        when(servicioDeAuto.enviarAutoMantenimiento(anyObject())).thenThrow(AutoYaExistente.class);
        return new Auto();
    }

    private Auto givenNoExisteUnAuto() throws AutoNoExistente {
        when(servicioDeAuto.buscarAutoPorId(anyLong())).thenThrow(AutoNoExistente.class);
        return new Auto();
    }


    private Auto givenUnAuto() {
        return new Auto();
    }

    private HttpServletRequest givenUnUsuarioConRol(String admin) {
        when(request.getSession()).thenReturn(sesion);
        when(request.getSession().getAttribute(anyString())).thenReturn(admin);
        return request;
    }

    private void whenEnvioElAutoAMantenimiento(HttpServletRequest request, Auto aEnviar) {
        this.modelAndView = controlador.enviarAutoAMantenimiento(request, aEnviar.getId());
    }

    private void thenElEnvioEsExitoso(ModelAndView modelAndView) throws AutoNoExistente, AutoYaExistente {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("Se envio correctamente un auto a mantenimiento");
        verify(servicioDeAuto, times(1)).buscarAutoPorId(anyLong());
        verify(servicioDeAuto,times(1)).enviarAutoMantenimiento(anyObject());
    }

    private void thenElEnvioFalla(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("home");
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No tenes permiso para realizar esta accion");
    }

    private void thenElEnvioFallaPorQueelAutoNoExiste(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo("No existe el auto que queres mandar");
    }
}
