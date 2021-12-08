package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class TestControladorMecanico {

    private String MECANICO = "mecanico";
    private ModelAndView modelAndView;
    private HttpServletRequest request;
    private HttpSession session;
    private ControladorMecanico controlador;
    private ServicioDeAuto servicioAuto;
    private ServicioUsuario servicioUsuario;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioAuto = mock(ServicioDeAuto.class);
        servicioUsuario = mock(ServicioUsuario.class);
        modelAndView = new ModelAndView();
        controlador = new ControladorMecanico(servicioAuto, servicioUsuario);
        request = givenExisteUnUsuarioConRol(MECANICO);
    }

    @Test
    public void queUnMecanicoPuedaAccederASuPantallaPrincipal() {
        whenAccedeAlaPantallaPrincipal(request);
        thenSeMuestraLaVista("redirect:/pantalla-principal", this.modelAndView);
    }

    private HttpServletRequest givenExisteUnUsuarioConRol(String mecanico) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(mecanico);
        when(request.getSession().getAttribute("nombre")).thenReturn("mecanico");
        when(request.getSession().getAttribute("id")).thenReturn(1l);
        return request;
    }

    private void whenAccedeAlaPantallaPrincipal(HttpServletRequest mecanico) {
        this.modelAndView = controlador.irAPantallaPrincipal(mecanico);
    }

    private void thenSeMuestraLaVista(String vista, ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo(vista);
    }

    @Test
    public void queElMecanicoCuandoAccedeALaPantallaPrincipalVeaUnaListaConLosAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosEnMantenimiento(5, Situacion.EN_MANTENIMIENTO);
        whenEntraAlaPantallaPrincipal(request);
        thenSeMuestraLaVista("pantalla-principal-mecanico", this.modelAndView);
        thenSeMuestraUnaListaConAutosEnMantenimientp(this.modelAndView);
    }

    private void givenExistenAutosEnMantenimiento(int cantidad, Situacion enMantenimiento) throws NoHayAutosEnMantenientoException {
        List<Auto> paraMantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(enMantenimiento);
            paraMantenimiento.add(auto);
        }
        when(servicioAuto.obtenerAutosEnMantenimiento()).thenReturn(paraMantenimiento);
    }

    private void whenEntraAlaPantallaPrincipal(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosParaMantenimiento(request);
    }

    private void thenSeMuestraUnaListaConAutosEnMantenimientp(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("lista_autos_mantenimiento")).isNotNull();
        assertThat(modelAndView.getModel().get("lista_autos_mantenimiento")).isInstanceOf(List.class);
        List<Auto> enMantenimiento = (List<Auto>) modelAndView.getModel().get("lista_autos_mantenimiento");
        assertThat(enMantenimiento).hasSize(5);
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorCuandoNoHayanAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosParaMantenimiento();
        whenEntraAlaPantallaPrincipal(request);
        thenSeMuestraLaVista("pantalla-principal-mecanico", this.modelAndView);
        thenSeMuestraUnMensajeDeError("No hay autos para mantenimiento actualmente", this.modelAndView);
    }

    private void givenNoExistenAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        doThrow(NoHayAutosEnMantenientoException.class).when(servicioAuto).obtenerAutosEnMantenimiento();
    }

    private void thenSeMuestraUnMensajeDeError(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queUnMencanicoPuedaEnviarUnAutoASuListaDeRevisionMostrandoUnMensajeDeExito() throws AutoNoExistente, UsuarioNoExistente {
        Long id_auto = givenQueExisteUnAutoEnMantenimiento(Situacion.EN_MANTENIMIENTO);
        Usuario mecanico = givenExisteUnUsuarioMecanico();
        whenElServicioLLamaAEnviarUnAutoARevision(id_auto, mecanico, LocalDate.now());
        whenUnMencanicoEnviaUnAutoEnMantenimientoARevision(id_auto, request);
        thenSeMuestraLaVista("envio-a-revision", this.modelAndView);
        thenSeMuestraUnMensajeDeExisto("El auto se envio correctamente", this.modelAndView);
    }

    private void whenElServicioLLamaAEnviarUnAutoARevision(Long id_auto, Usuario mecanico, LocalDate now) throws UsuarioNoExistente, AutoNoExistente {
        Auto enRevision = new Auto();
        enRevision.setSituacion(Situacion.EN_REVISION);
        Revision revision = new Revision();
        revision.setAuto(enRevision);
        when(servicioAuto.enviarARevision(any(), any(), any())).thenReturn(revision);
    }

    private Usuario givenExisteUnUsuarioMecanico() {
        Usuario mecanico = new Usuario();
        mecanico.setRol("mecanico");
        when(servicioUsuario.buscarPorId(anyLong())).thenReturn(mecanico);
        return mecanico;
    }

    private Long givenQueExisteUnAutoEnMantenimiento(Situacion enMantenimiento) throws AutoNoExistente {
        Auto paraMantenimiento = new Auto();
        paraMantenimiento.setSituacion(enMantenimiento);
        when(servicioAuto.buscarAutoPorId(anyLong())).thenReturn(paraMantenimiento);
        return paraMantenimiento.getId();
    }

    private void whenUnMencanicoEnviaUnAutoEnMantenimientoARevision(Long id_auto, HttpServletRequest request) {
        this.modelAndView = controlador.enviarAutoARevision(id_auto, request);
    }

    private void thenSeMuestraUnMensajeDeExisto(String mensaje, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("envio_exitoso")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("auto_en_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("auto_en_revision")).isInstanceOf(Auto.class);
        Auto enRevision = (Auto) modelAndView.getModel().get("auto_en_revision");
        assertThat(enRevision.getSituacion()).isEqualTo(Situacion.EN_REVISION);
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorCuandoEnvieUnAutoARevisionQueNoExiste() throws AutoNoExistente {
        givenNoExisteUnAutoEnRevision();
        whenUnMencanicoEnviaUnAutoEnMantenimientoARevision(1l, request);
        thenSeMuestraLaVista("envio-a-revision", this.modelAndView);
        thenSeMuestraUnMensajeDeErrorEnLaVista("No existe el auto que queres enviar a revision", this.modelAndView);
    }

    private void givenNoExisteUnAutoEnRevision() throws AutoNoExistente {
        doThrow(AutoNoExistente.class).when(servicioAuto).buscarAutoPorId(anyLong());
    }

    private void thenSeMuestraUnMensajeDeErrorEnLaVista(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error_no_existe_el_auto")).isEqualTo(error);
    }

    @Test
    public void queUnMencanicoPuedaVerUnaListaDeAutosEnRevision() throws NoHayAutosParaRevision {
        givenExistenAutosEnRevision(5, Situacion.EN_REVISION);
        whenSolicitaVerSuListaDeAutosEnRevision(request);
        thenSeMuestraLaVista("autos-en-revision", this.modelAndView);
        thenSeMuestraUnListaDeAutosEnRevision(this.modelAndView);
    }

    private void givenExistenAutosEnRevision(int cantidad, Situacion enRevision) throws NoHayAutosParaRevision {
        List<Auto> paraRevision = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto revision = new Auto();
            revision.setSituacion(enRevision);
            paraRevision.add(revision);
        }
        when(servicioAuto.obtenerAutosEnRevision()).thenReturn(paraRevision);
    }

    private void whenSolicitaVerSuListaDeAutosEnRevision(HttpServletRequest request) {
        this.modelAndView = controlador.mostrarAutosParaRevision(request);
    }

    private void thenSeMuestraUnListaDeAutosEnRevision(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("lista_de_autos_en_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("lista_de_autos_en_revision")).isInstanceOf(List.class);
        List<Auto> enRevision = (List<Auto>) modelAndView.getModel().get("lista_de_autos_en_revision");
        assertThat(enRevision).hasSize(5);
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorCuandoNoHayanAutosParaRevision() throws NoHayAutosParaRevision {
        givenNoExistenAutosParaRevision();
        whenSolicitaVerSuListaDeAutosEnRevision(request);
        thenSeMuestraLaVista("autos-en-revision", this.modelAndView);
        thenSeMuestraErrorEnLaVista("No hay autos para revision actualmente", this.modelAndView);
    }

    private void givenNoExistenAutosParaRevision() throws NoHayAutosParaRevision {
        doThrow(NoHayAutosParaRevision.class).when(servicioAuto).obtenerAutosEnRevision();
    }

    private void thenSeMuestraErrorEnLaVista(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queUnMecanicoPuedaVerLaVistaDelFormularioACompletarParaFinalizarLaRevisionDelAuto() throws AutoNoExistente {
        Long id_auto = givenExisteUnAutoEnRevision(Situacion.EN_REVISION);
        whenUnMecanicoSolicitaLaVistaParaCompletarElFormularioDelAuto(id_auto, request);
        thenSeMuestraLaVista("formulario-completar-revision", this.modelAndView);
        thenSeMuestraElAutoQueSeMostraraEnElFormulario(this.modelAndView);
    }


    private Long givenExisteUnAutoEnRevision(Situacion enRevision) throws AutoNoExistente {
        Auto paraRevision = new Auto();
        paraRevision.setSituacion(enRevision);
        when(servicioAuto.buscarAutoPorId(anyLong())).thenReturn(paraRevision);
        return null;
    }

    private void whenUnMecanicoSolicitaLaVistaParaCompletarElFormularioDelAuto(Long id_auto, HttpServletRequest request) {
        this.modelAndView = controlador.mostrarFormularioParaFinaLizarLaRevision(id_auto, request);
    }

    private void thenSeMuestraElAutoQueSeMostraraEnElFormulario(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("auto_para_formulario")).isNotNull();
        assertThat(modelAndView.getModel().get("auto_para_formulario")).isInstanceOf(Auto.class);
        Auto enRevision = (Auto) modelAndView.getModel().get("auto_para_formulario");
        assertThat(enRevision.getSituacion()).isEqualTo(Situacion.EN_REVISION);
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorAlCompletarUnFormularioDeUnaAutoNoExistente() throws AutoNoExistente {
        givenNoExisteUnAutoEnRevision();
        whenUnMecanicoSolicitaLaVistaParaCompletarElFormularioDelAuto(1l, request);
        thenSeMuestraLaVista("formulario-completar-revision", this.modelAndView);
        thenSeMuestraUnError("No existe el auto con el cual queres completar el formulario de revision", this.modelAndView);
    }

    private void thenSeMuestraUnError(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queUnMecanicoAlFinalizarLaRevisionSeCambieLaSituacionDelAutoADisponibleYSeSeteeElNuevoLimiteDeKilometraje() throws AutoNoExistente {
        Long id_auto = givenExisteUnAutoParaFinalizarElFormulario();
        String comentario = givenExisteUnComentario();
        whenElServicioLlamaAFinzalizarLaRevision(comentario, LocalDate.now());
        whenFinalizaElFormulario(id_auto, request, comentario);
        thenSeMuestraLaVista("finaliza-formulario-revision", this.modelAndView);
        thenSeMuestraUnMensajeAlFinalizarElFormulario("Se envio correctamente el formulario y el auto esta diponibles para alquiler nuevamente", this.modelAndView);
    }

    private String givenExisteUnComentario() {
        String comentario = "Se cambio una rueda";
        when(servicioAuto.estaVacioElComentario(anyString())).thenReturn(comentario);
        return comentario;
    }

    private Long givenExisteUnAutoParaFinalizarElFormulario() throws AutoNoExistente {
        Auto enRevision = new Auto();
        enRevision.setSituacion(Situacion.EN_REVISION);
        when(servicioAuto.buscarAutoPorId(anyLong())).thenReturn(enRevision);
        return enRevision.getId();
    }

    private void whenElServicioLlamaAFinzalizarLaRevision(String comentario, LocalDate now) {
        Usuario usuario = new Usuario();
        usuario.setRol("mecanico");
        Revision revision = new Revision();
        Auto disponible = new Auto();
        disponible.setSituacion(Situacion.DISPONIBLE);
        revision.setAuto(disponible);
        revision.setComentario(comentario);
        revision.setFechaFinRevision(now);
        revision.setUsuario(usuario);
        when(servicioAuto.finalizarRevision(any(), any(), any())).thenReturn(revision);
    }

    private void whenFinalizaElFormulario(Long id_auto, HttpServletRequest request, String comentario) {
        this.modelAndView = controlador.finalizarFormularioDeRevision(id_auto, comentario, request);
    }

    private void thenSeMuestraUnMensajeAlFinalizarElFormulario(String mensaje, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("formulario_exitoso")).isEqualTo(mensaje);

        assertThat(modelAndView.getModel().get("auto_con_situacion_actualizada")).isNotNull();
        assertThat(modelAndView.getModel().get("auto_con_situacion_actualizada")).isInstanceOf(Auto.class);
        Auto disponible = (Auto) modelAndView.getModel().get("auto_con_situacion_actualizada");
        assertThat(disponible.getSituacion()).isEqualTo(Situacion.DISPONIBLE);

        assertThat(modelAndView.getModel().get("usuario_mecanico")).isNotNull();
        assertThat(modelAndView.getModel().get("usuario_mecanico")).isInstanceOf(Usuario.class);
        Usuario mecanico = (Usuario) modelAndView.getModel().get("usuario_mecanico");
        assertThat(mecanico.getRol()).isEqualTo("mecanico");

        assertThat(modelAndView.getModel().get("fecha_fin_revision")).isNotNull();
        assertThat(modelAndView.getModel().get("fecha_fin_revision")).isInstanceOf(LocalDate.class);
        LocalDate fecha_fin_revision = (LocalDate) modelAndView.getModel().get("fecha_fin_revision");
        assertThat(fecha_fin_revision).isEqualTo(LocalDate.now());
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorAlFinzalizarUnaRevisionConUnAutoInexistente() throws AutoNoExistente {
        givenNoExisteUnAutoEnRevision();
        String comentario = givenExisteUnComentario();
        whenElServicioLlamaAFinzalizarLaRevision(comentario, LocalDate.now());
        whenFinalizaElFormulario(1l, request, comentario);
        thenSeMuestraLaVista("finaliza-formulario-revision", this.modelAndView);
        thenMuestraUnMensajeDeError("No existe el auto con el cual vas a finlizar la revision", this.modelAndView);
    }

    private void thenMuestraUnMensajeDeError(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error_no_existe_auto")).isEqualTo(error);
    }

    @Test
    public void queElMecanicoVeaUnMensaejDeErrorCuandoSeEnvieeUnAutoARevisionConElIDDelMecanicoIncorrecto() throws AutoNoExistente, UsuarioNoExistente {
        givenNoExisteUnUsuario();
        whenUnMencanicoEnviaUnAutoEnMantenimientoARevision(1l, request);
        thenLaSolicitudFalla("No existe el usuario con el cual vas a reviar el auto", this.modelAndView);
    }

    private void givenNoExisteUnUsuario() throws UsuarioNoExistente, AutoNoExistente {
        doThrow(UsuarioNoExistente.class).when(servicioAuto).enviarARevision(any(), any(), any());
    }

    private void thenLaSolicitudFalla(String s, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error_no_existe_el_usuario")).isEqualTo(s);
    }

    @Test
    public void queUnMecanicoVeaUnMensajeDeErrorAlQuererFinalizarConUnComentarioVacio() {
        String comentario = givenQueElComentarioEstaVacio();
        whenFinalizaElFormulario(1l, request, comentario);
        thenSeMuestraLaVista("finaliza-formulario-revision", this.modelAndView);
        thenSeMuestraUnMsjDeError("La descripcion no puede estar vacia", this.modelAndView);
    }

    private String givenQueElComentarioEstaVacio() {
        return null;
    }

    private void thenSeMuestraUnMsjDeError(String error, ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("error_comentario_vacio")).isEqualTo(error);
    }
}
