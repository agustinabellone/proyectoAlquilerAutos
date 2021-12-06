package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaEnRevision;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioMecanico {

    private String MECANICO;
    private RepositorioAuto repositorioDeAuto;
    private ServicioDeAuto servicioDeAuto;
    private RepositorioUsuario repositorioUsuario;
    @Before
    public void init() {
        MECANICO = "mecanico";
        repositorioUsuario = mock(RepositorioUsuario.class);
        repositorioDeAuto = mock(RepositorioAuto.class);
        servicioDeAuto = new ServicioDeAutoImpl(repositorioDeAuto,repositorioUsuario);
    }

    @Test(expected = NoHayAutosEnMantenientoException.class)
    public void lanzarUnaExceptionSiNoHayAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenNoExistenAutosParaMantenimiento();
        whenObtieneLaListaDeAutosParaMatenimiento();
    }

    private void givenNoExistenAutosParaMantenimiento() {
    }

    private List<Auto> whenObtieneLaListaDeAutosParaMatenimiento() throws NoHayAutosEnMantenientoException {
        return servicioDeAuto.obtenerAutosEnMantenimiento();
    }

    @Test
    public void queSePuedaObtenerUnaListaDeAutosParaMantenimiento() throws NoHayAutosEnMantenientoException {
        givenExistenAutosParaMantenimiento(5, Situacion.EN_MANTENIMIENTO);
        List<Auto> paraMantenimiento = whenObtieneLaListaDeAutosParaMatenimiento();
        thenObtengoLaListaConLosAutosParaMatenimiento(paraMantenimiento);
    }

    private void givenExistenAutosParaMantenimiento(int cantidad, Situacion enMantenimiento) {
        List<Auto> para_mantenimiento = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setId((long) i);
            auto.setSituacion(enMantenimiento);
            para_mantenimiento.add(auto);
        }
        when(repositorioDeAuto.buscarAutosEnMantenimiento(enMantenimiento)).thenReturn(para_mantenimiento);
    }

    private void thenObtengoLaListaConLosAutosParaMatenimiento(List<Auto> paraMantenimiento) {
        assertThat(paraMantenimiento).hasSize(5);
        for (Auto auto :
                paraMantenimiento) {
            assertThat(auto.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        }
    }

    @Test
    public void queAlEnviarUnAutoARevisionSuEstadoCambieAEnRevisionYTambienSeEnvieElMecanicoQueTomoEseAuto() throws AutoYaEnRevision, AutoNoExistente {
        Long id_mecanico = givenExisteUnMecanico(MECANICO);
        Auto auto = givenExisteUnAutoEnMantenimiento();
        givenMockeaLaSituacion();
        Auto enRevision = whenLoEnvioARevision(auto, id_mecanico);
        thenDevuelveElAutoConElEstadoCambiado(enRevision);
    }

    private Long givenExisteUnMecanico(String mecanico) {
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setRol(mecanico);
        when(repositorioUsuario.buscarPorId(anyLong())).thenReturn(usuario);
        return usuario.getId();
    }

    private Auto givenExisteUnAutoEnMantenimiento() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.EN_MANTENIMIENTO);
        when(repositorioDeAuto.buscarPorPatente(anyString())).thenReturn(auto);
        return auto;
    }

    private void givenMockeaLaSituacion() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.EN_REVISION);
        when(repositorioDeAuto.buscarPor(anyLong())).thenReturn(auto);
    }

    private Auto whenLoEnvioARevision(Auto auto, Long mecanico) throws AutoYaEnRevision, AutoNoExistente {
        return servicioDeAuto.enviarARevision(auto.getPatente(), mecanico);
    }

    private void thenDevuelveElAutoConElEstadoCambiado(Auto enRevision) {
        assertThat(enRevision.getSituacion()).isEqualTo(Situacion.EN_REVISION);
    }

    @Test (expected = AutoNoExistente.class)
    public void lanzarUnaExceptionSiElAutoAEnviarAReivisionNoExiste() throws AutoYaEnRevision, AutoNoExistente {
        givenNoExiteUnAutoParaMantenimiento();
        whenLoEnvioARevision(new Auto(),1l);
    }

    private void givenNoExiteUnAutoParaMantenimiento() {

    }

    @Test
    public void queSePuedanObtenerAutosPorPatente(){
        Auto auto = givenExistenAutos();
        Auto buscado = whenLoBuscoPorPatente(auto.getPatente());
        thenObtengoElAutoBuscado(buscado,auto.getPatente());
    }

    private Auto givenExistenAutos() {
        Auto auto = new Auto();
        auto.setPatente("AA123AA");
        when(repositorioDeAuto.buscarPorPatente(anyString())).thenReturn(auto);
        return auto;
    }

    private Auto whenLoBuscoPorPatente(String patente) {
        return repositorioDeAuto.buscarPorPatente(patente);
    }

    private void thenObtengoElAutoBuscado(Auto buscado, String patente) {
        assertThat(buscado.getPatente()).isEqualTo(patente);
    }
}
