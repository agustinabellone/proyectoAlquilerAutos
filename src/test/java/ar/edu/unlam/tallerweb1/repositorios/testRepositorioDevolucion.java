package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

public class testRepositorioDevolucion extends SpringTest {

    @Autowired
    private RepositorioDevolucion repositorioDevolucion;


    @Test
    @Transactional
    @Rollback
    public void puedoEncontrarUnAutoPorSuEstadoDisponible() {
        Auto a1 = givenExisteUnAuto();
        Auto a2 = givenExisteUnAuto();
        Auto a3 = givenExisteUnAuto();
        givenGuardoUnAuto(a1);
        givenGuardoUnAuto(a2);
        givenGuardoUnAuto(a3);
        List<Auto> listaAuto = whenBuscoPorSuEstadoDisponible(true);
        Integer cantidadEsperada = 3;
        thenObtengoLosAutosConSuEstadoDisponible(listaAuto, cantidadEsperada);

    }

    private Auto givenExisteUnAuto() {
        Auto auto = new Auto();
        return auto;
    }

    private void thenObtengoLosAutosConSuEstadoDisponible(List<Auto> listaAuto, Integer cantidadEsperada) {
        assertThat(listaAuto).hasSize(cantidadEsperada);
    }

    private void givenGuardoUnAuto(Auto auto) {
        repositorioDevolucion.guardar(auto);
    }

    private List<Auto> whenBuscoPorSuEstadoDisponible(boolean verdadero) {
        return repositorioDevolucion.buscarPorSuEstadoDisponible(verdadero);
    }


}
