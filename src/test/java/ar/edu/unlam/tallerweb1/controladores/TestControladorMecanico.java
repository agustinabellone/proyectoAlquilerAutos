package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
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
    private Long id_mecanico;

    @Before
    public void init() {
        MECANICO = "mecanico";
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servicioDeAuto = mock(ServicioDeAuto.class);
        controlador = new ControladorMecanico(servicioDeAuto);
        modelAndView = new ModelAndView();
        request = givenExisteUnUsuarioConRol(MECANICO);
    }

    @Test
    public void queUnUsuarioMecanicoPuedaAccederALaVistaAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExisteUnaListaDeAutosParaMantenimiento(5);
        whenAccedeALaVistaDeAutosParaMantenimiento(request);
        thenSeMuestraLaVistaConUnaListaDeAutosParaMantenimiento(this.modelAndView, "en-mantenimiento", request);
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

    private void thenSeMuestraLaVistaConUnaListaDeAutosParaMantenimiento(ModelAndView modelAndView, String vista, HttpServletRequest mecanico) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
        assertThat(modelAndView.getModel().get("mecanico")).isEqualTo(mecanico.getSession().getAttribute("id"));
        assertThat(modelAndView.getModel().get("para_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("para_mantenimiento")).isInstanceOf(List.class);
        List<Auto> enMantenimiento = (List<Auto>) modelAndView.getModel().get("para_mantenimiento");
        assertThat(enMantenimiento).hasSize(5);
    }

    @Test
    public void queUnUsuarioMecanicoAlAccederALaVistaVeaUnMensajeDeAvisoQueNoHayAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExitenAutosParaMantenimiento();
        whenAccedeALaVistaDeAutosParaMantenimiento(request);
        thenSeMuestraLaVistaConUnMensajeDeError(this.modelAndView, "en-mantenimiento", "No hay autos para mantenimiento actualmente");
    }

    private void givenNoExitenAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        doThrow(NoHayAutosEnMantenientoException.class).when(servicioDeAuto).obtenerAutosEnMantenimiento();
    }

    private void thenSeMuestraLaVistaConUnMensajeDeError(ModelAndView modelAndView, String vista, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
        assertThat(modelAndView.getModel().get("error")).isNotNull();
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queUnMecanicoAlHacerClickEnElBotonDeRevisarSeAgregueAUnaListaDeAutosParaRevisar() throws AutoNoExistente {
        String patente = givenExisteUnAutoEnMantenimiento(Situacion.EN_MANTENIMIENTO);
        whenSeMandaARevision(patente, request);
        thenSeEnviaElAutoARevisionYRedirigeALaVistaDeAutosParaMantenimiento(this.modelAndView, patente, id_mecanico);
    }

    private void thenSeEnviaElAutoARevisionYRedirigeALaVistaDeAutosParaMantenimiento(ModelAndView modelAndView, String patente, Long id_mecanico) throws AutoNoExistente {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/para-mantenimiento");
        assertThat(request.getSession().getAttribute("patente")).isEqualTo(patente);
        verify(servicioDeAuto,times(1)).buscarAutoPorPatente(anyString());
        verify(servicioDeAuto,times(1)).enviarARevision(anyString(),anyLong());
    }

    private String givenExisteUnAutoEnMantenimiento(Situacion enMantenimiento) throws AutoNoExistente {
        Auto auto = new Auto();
        auto.setPatente("AA123AA");
        when(servicioDeAuto.buscarAutoPorPatente(auto.getPatente())).thenReturn(auto);
        when(request.getSession().getAttribute("patente")).thenReturn(auto.getPatente());
        return auto.getPatente();
    }

    private void whenSeMandaARevision(String patente, HttpServletRequest mecanico) {
        this.modelAndView = controlador.enviarARevision(patente, mecanico);
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(String mecanico) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(mecanico);
        when(request.getSession().getAttribute("id")).thenReturn(1l);
        return request;
    }

    @Test
    public void queSeMuestreUnMensajeDeErrorDeQueNoExisteElAutoQueSeQuiereEnviar() throws AutoNoExistente {
        givenNoExisteElAutoPorPatente();
        whenSeMandaARevision("AA123AA",request);
        thenSeMuestraLaVistaConUnMensajeDeError(this.modelAndView,"No existe el auto que queres mandar");
    }

    private void givenNoExisteElAutoPorPatente() throws AutoNoExistente {
        doThrow(AutoNoExistente.class).when(servicioDeAuto).buscarAutoPorPatente(anyString());
        when(request.getSession().getAttribute("error")).thenReturn("No existe el auto que queres mandar");
    }

    private void thenSeMuestraLaVistaConUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/para-mantenimiento");
        assertThat(request.getSession().getAttribute("error")).isEqualTo(error);
    }
}
