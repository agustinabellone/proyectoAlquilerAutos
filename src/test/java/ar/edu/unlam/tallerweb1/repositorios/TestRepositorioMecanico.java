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

public class TestRepositorioMecanico extends SpringTest {

    @Autowired
    private RepositorioAuto repositorioAuto;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaDeAutosEnMantemiento() {
        givenExistenAutosParamantenimiento(5);
        List<Auto> enMantenimiento = whenBuscoAutosEnSituacionEnMantenimiento();
        thenObtengoLaListaDeAutosParaMatenimiento(enMantenimiento);
    }

    private void givenExistenAutosParamantenimiento(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.EN_MANTENIMIENTO);
            session().save(auto);
        }
    }

    private List<Auto> whenBuscoAutosEnSituacionEnMantenimiento() {
        return repositorioAuto.buscarAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO);
    }

    private void thenObtengoLaListaDeAutosParaMatenimiento(List<Auto> enMantenimiento) {
        assertThat(enMantenimiento).hasSize(5);
        for (Auto auto :
                enMantenimiento) {
            assertThat(auto.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        }
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarAutosPorPatente(){
        Auto auto = givenUnExisteAuto();
        Auto buscado = whenBuscoUnAutoPorPatente(auto.getPatente());
        thenObtengoElAutosBuscado(buscado,auto);
    }

    private Auto givenUnExisteAuto() {
        Auto auto = new Auto();
        auto.setPatente("AA123AA");
        session().save(auto);
        return auto;
    }

    private Auto whenBuscoUnAutoPorPatente(String patente) {
        return repositorioAuto.buscarPorPatente(patente);
    }

    private void thenObtengoElAutosBuscado(Auto buscado, Auto auto) {
        assertThat(buscado.getPatente()).isEqualTo(auto.getPatente());
    }
}
