package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaEnRevision;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosParaRevision;
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
    public void queUnMecanicoAlHacerClickEnElBotonDeRevisarSeAgregueAUnaListaDeAutosParaRevisar() throws AutoNoExistente, AutoYaEnRevision {
        String patente = givenExisteUnAutoEnMantenimiento(Situacion.EN_MANTENIMIENTO);
        whenSeMandaARevision(patente, request);
        thenSeEnviaElAutoARevisionYRedirigeALaVistaDeAutosParaMantenimiento(this.modelAndView, patente, id_mecanico);
    }

    private void thenSeEnviaElAutoARevisionYRedirigeALaVistaDeAutosParaMantenimiento(ModelAndView modelAndView, String patente, Long id_mecanico) throws AutoNoExistente, AutoYaEnRevision {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/para-mantenimiento");
        assertThat(request.getSession().getAttribute("patente")).isEqualTo(patente);
        verify(servicioDeAuto, times(1)).buscarAutoPorPatente(anyString());
        verify(servicioDeAuto, times(1)).enviarARevision(anyString(), anyLong());
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
        givenNoExisteElAuto();
        whenSeMandaARevision("AA123AA", request);
        thenSeMuestraLaVistaConUnMensajeDeError(this.modelAndView, "No existe el auto que queres mandar");
    }

    private void givenNoExisteElAuto() throws AutoNoExistente {
        doThrow(AutoNoExistente.class).when(servicioDeAuto).buscarAutoPorPatente(anyString());
        when(request.getSession().getAttribute("error")).thenReturn("No existe el auto que queres mandar");
    }

    private void thenSeMuestraLaVistaConUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/para-mantenimiento");
        assertThat(request.getSession().getAttribute("error")).isEqualTo(error);
    }

    @Test
    public void queSeMuestreUnMensajeDeErrorCuandoElMecanicoIntenteEnviarARevisionUnAutoQueYaEstaEnRevision() throws AutoYaEnRevision, AutoNoExistente {
        givenExisteUnautoEnRevision();
        whenSeMandaARevision("AA123AA", request);
        thenSeMuestraLaVistaConUnMensajeDeError(this.modelAndView, "No se puede enviar un auto a revision que ya esta enviado");
    }

    private void givenExisteUnautoEnRevision() throws AutoYaEnRevision, AutoNoExistente {
        when(servicioDeAuto.buscarAutoPorPatente(anyString())).thenReturn(new Auto());
        doThrow(AutoYaEnRevision.class).when(servicioDeAuto).enviarARevision(anyString(), anyLong());
        when(request.getSession().getAttribute("error")).thenReturn("No se puede enviar un auto a revision que ya esta enviado");
    }

    @Test
    public void queElUsuarioAlHacerClickEnLaSeccionDeAutosParaRevisarSeMuestreLaVistaConSuListaDeAutosParaRevisar() throws NoHayAutosParaRevision {
        givenExistenAutosParaRevisar(Situacion.EN_REVISION, 10);
        whenAccedeALaVistaDeAutosParaRevisar(request);
        thenSeMuestraLaVistaConLaListaDeAutosParaRevisar(this.modelAndView);
    }

    private void givenExistenAutosParaRevisar(Situacion enRevision, int cantidad) throws NoHayAutosParaRevision {
        List<Auto> paraRevision = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.EN_REVISION);
            paraRevision.add(auto);
        }
        when(servicioDeAuto.obtenerAutosEnRevision()).thenReturn(paraRevision);
    }

    private void whenAccedeALaVistaDeAutosParaRevisar(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarListaDeAutosEnRevision(request);
    }

    private void thenSeMuestraLaVistaConLaListaDeAutosParaRevisar(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("en-revision");
        assertThat(modelAndView.getModel().get("para_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("para_revision")).isInstanceOf(List.class);
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("para_revision");
        assertThat(autoList).hasSize(10);
    }

    @Test
    public void queAlObtenerUnaListaDeAutosEnRevisionSeMuestreUnMensajeDeErrorYaQueNoHayAutosParaRevision() throws NoHayAutosParaRevision {
        givenNoExistenAutosParaRevision();
        whenAccedeALaVistaDeAutosParaRevisar(request);
        thenSeMuestraUnMensajeDeError(this.modelAndView, "No hay autos para revision actualmente");
    }

    private void givenNoExistenAutosParaRevision() throws NoHayAutosParaRevision {
        doThrow(NoHayAutosParaRevision.class).when(servicioDeAuto).obtenerAutosEnRevision();
    }

    private void thenSeMuestraUnMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getModel().get("error_no_hay_autos_para_revision")).isEqualTo(error);
        assertThat(modelAndView.getViewName()).isEqualTo("en-revision");
    }

    @Test
    public void queElMecanicoAlHacerClickEnElBotonDeRevisarSeMuestreUnFormulario() throws NoHayAutosParaRevision, AutoNoExistente {
        Long id_auto = givenExisteUnAutoParaRevisar(Situacion.EN_REVISION);
        whenAccedeAlFormularioDeRevision(id_auto, request);
        thenSeMuestraLaVistaConElFormulario(this.modelAndView, id_auto);
    }

    private Long givenExisteUnAutoParaRevisar(Situacion enRevision) throws AutoNoExistente {
        Auto auto = new Auto();
        auto.setId(1l);
        auto.setSituacion(enRevision);
        when(servicioDeAuto.buscarAutoPorId(auto.getId())).thenReturn(auto);
        return auto.getId();
    }

    private void whenAccedeAlFormularioDeRevision(Long id_auto, HttpServletRequest request) {
        this.modelAndView = controlador.completarFormularioDeRevision(id_auto, request);
    }

    private void thenSeMuestraLaVistaConElFormulario(ModelAndView modelAndView, Long id_auto) {
        assertThat(modelAndView.getViewName()).isEqualTo("formulario-revision");
        assertThat(modelAndView.getModel().get("auto_para_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("auto_para_revision")).isInstanceOf(Auto.class);
        Auto auto = (Auto) modelAndView.getModel().get("auto_para_revision");
        assertThat(auto.getId()).isEqualTo(id_auto);
    }

    @Test
    public void queElMecanicoVeaUnMensajeDeErrorAlRevisarUnAutoQueNoExisteYLoMandeALaVistaDeAutosParaRevisar() throws AutoNoExistente {
        givenNoExisteElAutoARevisar();
        whenAccedeAlFormularioDeRevision(1l, request);
        thenSeMuestraUnMensajeDeErrorYRedirigeAAutosParaMantenimiento(this.modelAndView, request);
    }

    private void givenNoExisteElAutoARevisar() throws AutoNoExistente {
        doThrow(AutoNoExistente.class).when(servicioDeAuto).buscarAutoPorId(anyLong());
    }

    private void thenSeMuestraUnMensajeDeErrorYRedirigeAAutosParaMantenimiento(ModelAndView modelAndView, HttpServletRequest request) {
        assertThat(modelAndView.getViewName()).isEqualTo("en-revision");
        assertThat(modelAndView.getModel().get("error_no_existe_auto")).isEqualTo("No existe el auto que queres revisar");
    }
}
