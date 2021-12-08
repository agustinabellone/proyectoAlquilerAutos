package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioMecanico {

    private ServicioDeAutoImpl servicioAuto;
    private RepositorioAuto repositorioAuto;
    private RepositorioUsuario repositorioUsuario;

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuario.class);
        repositorioAuto = mock(RepositorioAuto.class);
        servicioAuto = new ServicioDeAutoImpl(repositorioAuto, repositorioUsuario);
    }

    @Test(expected = NoHayAutosEnMantenientoException.class)
    public void lanzarUnaExceptionCuandoNoHayanAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        givenQueNoExistenAutoEnMantenimiento();
        whenUnMencanicoSolicitaUnaListaDeAutosEnMantenimiento();
    }

    private void givenQueNoExistenAutoEnMantenimiento() {
    }

    private List<Auto> whenUnMencanicoSolicitaUnaListaDeAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return servicioAuto.obtenerAutosEnMantenimiento();
    }

    @Test
    public void queSePuedaObtenerUnaListaConAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosParaMantenimiento(5);
        List<Auto> paraMantenimiento = whenUnMencanicoSolicitaUnaListaDeAutosEnMantenimiento();
        thenObtengoUnaListaConLaCantidadEsperada(paraMantenimiento, 5);
    }

    private void givenExistenAutosParaMantenimiento(int cantidad) {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.EN_MANTENIMIENTO);
            autoList.add(auto);
        }
        when(repositorioAuto.buscarAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO)).thenReturn(autoList);
    }

    private void thenObtengoUnaListaConLaCantidadEsperada(List<Auto> paraMantenimiento, int cantidad_esperada) {
        assertThat(paraMantenimiento).hasSize(cantidad_esperada);
    }

    @Test(expected = AutoNoExistente.class)
    public void lanzarUnaExceptionCuandoElAutoNoExiste() throws AutoNoExistente {
        givenNoExisteUnAuto();
        whenBuscoUnAutoPorId(1l);
    }

    private void givenNoExisteUnAuto() {
    }

    private Auto whenBuscoUnAutoPorId(Long buscado) throws AutoNoExistente {
        return servicioAuto.buscarAutoPorId(1l);
    }

    @Test
    public void queSePuedaObtenerUnAutoPorId() throws AutoNoExistente {
        Long buscado = givenExisteUnAuto();
        Auto encontrado = whenBuscoUnAutoPorId(buscado);
        thenObtengoElAutoBuscado(encontrado, buscado);
    }

    private void thenObtengoElAutoBuscado(Auto encontrado, Long buscado) {
        assertThat(encontrado.getId()).isEqualTo(buscado);
    }

    private Long givenExisteUnAuto() {
        Auto auto = new Auto();
        auto.setId(1l);
        when(repositorioAuto.buscarPor(anyLong())).thenReturn(auto);
        return auto.getId();
    }

    @Test
    public void queSePuedaEnviarUnAutoARevision() throws AutoNoExistente, UsuarioNoExistente {
        Auto paraRevision = givenExisteUnAutoParaMantenimiento();
        Usuario mecanico = givenExisteUnMecanico();
        givenSeLlamaAlRepoParaEnviarARevision(paraRevision,mecanico);
        Revision nueva = whenEnvioElAutoARevisionConElMecanicoYLaFechaActual(paraRevision, mecanico, LocalDate.now());
        thenObtengoElAutoConLaSituacionActualizadaConUnObjetoRevision(nueva);
    }

    private void givenSeLlamaAlRepoParaEnviarARevision(Auto paraRevision, Usuario mecanico) {
        Revision revision = new Revision();
        paraRevision.setSituacion(Situacion.EN_REVISION);
        revision.setAuto(paraRevision);
        revision.setUsuario(mecanico);
        when(repositorioAuto.enviarARevision(paraRevision,mecanico,LocalDate.now())).thenReturn(revision);
    }

    private Usuario givenExisteUnMecanico() {
        Usuario mecanico = new Usuario();
        mecanico.setRol("mecanico");
        when(repositorioUsuario.buscarPorId(anyLong())).thenReturn(mecanico);
        return mecanico;
    }

    private Auto givenExisteUnAutoParaMantenimiento() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.EN_MANTENIMIENTO);
        when(repositorioAuto.buscarPor(anyLong())).thenReturn(auto);
        return auto;
    }

    private Revision whenEnvioElAutoARevisionConElMecanicoYLaFechaActual(Auto paraRevision, Usuario mecanico, LocalDate fecha_de_envio) throws AutoNoExistente, UsuarioNoExistente {
        return servicioAuto.enviarARevision(paraRevision, mecanico, fecha_de_envio);
    }

    private void thenObtengoElAutoConLaSituacionActualizadaConUnObjetoRevision(Revision nueva) {
        assertThat(nueva.getAuto().getSituacion()).isEqualTo(Situacion.EN_REVISION);
    }

    @Test(expected = UsuarioNoExistente.class)
    public void lanzarUnaExceptionCuandoElUsuarioMecanicoNoExista() throws UsuarioNoExistente, AutoNoExistente {
        Auto paraRevision = givenExisteUnAutoParaMantenimiento();
        givenQueNoExisteUnUsuario();
        whenEnvioElAutoARevisionConElMecanicoYLaFechaActual(paraRevision,new Usuario(),LocalDate.now());

    }

    private void givenQueNoExisteUnUsuario() {

    }
    
    @Test(expected = NoHayAutosParaRevision.class)
    public void lanzarUnaExpetionCuandoSeObtengaUnaListaDeAutosEnRevisionQueNoExisten() throws NoHayAutosParaRevision {
        givenNoExistenAutosParaRevision();
        whenElMecanicoSolicitaUnaListaDeAutosParaRevision();
    }

    private void givenNoExistenAutosParaRevision() {
    }

    private List<Auto> whenElMecanicoSolicitaUnaListaDeAutosParaRevision() throws NoHayAutosParaRevision {
        return servicioAuto.obtenerAutosEnRevision();
    }

    @Test
    public void queSePuedaObtenerUnaListaDeAutosParaRevision() throws NoHayAutosParaRevision {
        givenQueExistenAutosParaRevision(5);
        List<Auto> paraRevision = whenElMecanicoSolicitaUnaListaDeAutosParaRevision();
        thenObtengolaListaConLaCantidadEsperada(paraRevision,5);
    }

    private void givenQueExistenAutosParaRevision(int cantidad) {
        List<Auto> autoList = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.EN_REVISION);
            autoList.add(auto);
        }
        when(repositorioAuto.obtenerAutosEnRevision()).thenReturn(autoList);
    }

    private void thenObtengolaListaConLaCantidadEsperada(List<Auto> paraRevision, int cantidad_esperada) {
        assertThat(paraRevision).hasSize(cantidad_esperada);
    }

}
