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

public class testRepositorioAdministrador extends SpringTest {

    @Autowired
    private RepositorioAlquiler repositorioAlquiler;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarAutosAlquiladosParaQuePuedanSerObtenidos() {
        givenExistenAutosAlquilados(Situacion.OCUPADO, 10);
        List<Auto> autosAlquilados = whenBuscoAutosAlquilados(Situacion.OCUPADO);
        thenObtengoLaListaConLosAutosAlquilados(autosAlquilados, 10);
    }

    private void givenExistenAutosAlquilados(Situacion alquilado, int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setSituacion(alquilado);
            session().save(auto);
        }
    }

    private List<Auto> whenBuscoAutosAlquilados(Situacion alquilado) {
        return repositorioAlquiler.buscarAutosAlquilados(alquilado);
    }

    private void thenObtengoLaListaConLosAutosAlquilados(List<Auto> autosAlquilados, int autosEncontrados) {
        assertThat(autosAlquilados).hasSize(autosEncontrados);
        for (Auto auto :
                autosAlquilados) {
            assertThat(auto.getSituacion()).isEqualTo(Situacion.OCUPADO);

        }
    }
}
