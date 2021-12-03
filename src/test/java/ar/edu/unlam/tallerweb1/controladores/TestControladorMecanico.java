package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestControladorMecanico {

    private static String MECANICO;
    private HttpServletRequest request;
    private HttpSession session;
    private ControladorMecanico controlador;
    private ModelAndView modelAndView;
    private ServicioDeAuto servicioDeAuto;
    private HttpServletRequest mecanico;

    @Before
    public void init() {
        MECANICO = "mecanico";
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorMecanico(servicioDeAuto);
        modelAndView = new ModelAndView();
        mecanico = givenAccedeAlaVistaDeAutosParaMantenimiento(MECANICO);
    }

    @Test
    public void queUnUsuarioMecanicoPuedaAccederALaVistaAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExisteUnaListaDeAutosParaMantenimiento(5);
        whenAccedeALaVistaDeAutosParaMantenimiento(mecanico);
        thenSeMuestraLaVistaConUnaListaDeAutosParaMantenimiento(this.modelAndView, "en-mantenimiento");
    }

    private void givenExisteUnaListaDeAutosParaMantenimiento(int cantidad) throws NoHayAutosEnMantenientoException {
        List<Auto> paraMantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setId((long) i);
            auto.setSituacion(Situacion.EN_MANTENIMIENTO);
            paraMantenimiento.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnMantenimiento()).thenReturn(paraMantenimiento);
    }

    private void whenAccedeALaVistaDeAutosParaMantenimiento(HttpServletRequest mecanico) {
        this.modelAndView = controlador.mostrarListadoDeAutosParaMantenimiento(mecanico);
    }

    private void thenSeMuestraLaVistaConUnaListaDeAutosParaMantenimiento(ModelAndView modelAndView, String vista) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
        assertThat(modelAndView.getModel().get("para_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("para_mantenimiento")).isInstanceOf(List.class);
        List<Auto> enMantenimiento = (List<Auto>) modelAndView.getModel().get("para_mantenimiento");
        assertThat(enMantenimiento).hasSize(5);
    }

    @Test
    public void queUnUsuarioMecanicoAlAccederALaVistaVeaUnMensajeDeAvisoQueNoHayAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExitenAutosParaMantenimiento();
        whenAccedeALaVistaDeAutosParaMantenimiento(mecanico);
        thenSeMuestraLaVistaConUnMensajeDeError(this.modelAndView,"en-mantenimiento","No hay autos para mantenimiento actualmente");
    }

    private void givenNoExitenAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        doThrow(NoHayAutosEnMantenientoException.class).when(servicioDeAuto).obtenerAutosEnMantenimiento();
    }

    private void thenSeMuestraLaVistaConUnMensajeDeError(ModelAndView modelAndView, String vista, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
        assertThat(modelAndView.getModel().get("error")).isNotNull();
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    private HttpServletRequest givenAccedeAlaVistaDeAutosParaMantenimiento(String mecanico) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(mecanico);
        return request;
    }
}
