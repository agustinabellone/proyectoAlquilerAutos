package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAdministradorSeccionAutos extends SpringTest {

    @Autowired
    private RepositorioAlquiler repositorioAlquiler;
    @Autowired
    private RepositorioAuto repositorioAuto;

    @Test
    @Rollback
    @Transactional
    public void queSePuedanBuscarAutosEnSituacionDeAlquilados() {
        givenExistenAutos(Situacion.OCUPADO, 10);
        List<Auto> autosAlquilados = whenBuscoAutosAlquilados(Situacion.OCUPADO);
        thenObtengoLaListaConAutos(autosAlquilados, 10);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanBuscarAutosEnSituacionDeDisponible() {
        givenExistenAutos(Situacion.DISPONIBLE, 10);
        List<Auto> autosDisponibles = whenBuscoAutosDisponibles(Situacion.DISPONIBLE);
        thenObtengoLaListaConAutos(autosDisponibles, 10);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanBuscarAutosEnSituacionDeEnMantenimiento() {
        givenExistenAutos(Situacion.EN_MANTENIMIENTO, 10);
        List<Auto> autosEnMantenimiento = whenBuscoAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO);
        thenObtengoLaListaConAutos(autosEnMantenimiento, 10);
    }

    private List<Auto> whenBuscoAutosEnMantenimiento(Situacion enMantenimiento) {
        return repositorioAuto.buscarAutosEnMantenimiento(enMantenimiento);
    }

    private void givenExistenAutos(Situacion situacion, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(situacion);
            session().save(auto);
        }
    }

    private List<Auto> whenBuscoAutosDisponibles(Situacion disponible) {
        return repositorioAlquiler.obtenerAutosDisponibles();
    }

    private List<Auto> whenBuscoAutosAlquilados(Situacion alquilado) {
        return repositorioAlquiler.buscarAutosAlquilados(alquilado);
    }

    private void thenObtengoLaListaConAutos(List<Auto> listaObtenida, int cantidad_esperada) {
        assertThat(listaObtenida).hasSize(cantidad_esperada);
    }
}
