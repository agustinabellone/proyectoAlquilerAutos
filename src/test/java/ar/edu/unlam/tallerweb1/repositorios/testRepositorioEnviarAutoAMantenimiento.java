package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class testRepositorioEnviarAutoAMantenimiento extends SpringTest {

    public static final String PATENTE = "ABC123";
    public static final String FIESTA = "Fiesta";
    public static final String FOCUS = "Focus";
    public static final String FORD = "Ford";
    public static final String TOYOTA = "Toyota";

    @Autowired
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarPorPatente() {
        givenExisteUnAutoConPatente(PATENTE);
        Auto encontrado = whenBuscoUnAutoConPatente(PATENTE);
        thenEncuentroElAutoPorPatente(encontrado, PATENTE);
    }

    private void givenExisteUnAutoConPatente(String patente) {
        Auto buscado = new Auto();
        buscado.setPatente(patente);
        session().save(buscado);
    }

    private Auto whenBuscoUnAutoConPatente(String patente) {
        return repositorioEnviarAutoAMantenimiento.buscarPor(patente);
    }

    private void thenEncuentroElAutoPorPatente(Auto encontrado, String patente) {
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getPatente()).isEqualTo(patente);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarPorModelo() {
        givenExistenAutosConModelo(FIESTA, 3L);
        givenExistenAutosConModelo(FOCUS, 2L);
        List<Auto> autosEncontrados = whenBuscoAutosConModelo(FOCUS);
        thenEncuentroLosAutosPorModelo(autosEncontrados, 2);
    }

    private void givenExistenAutosConModelo(String modelo, Long cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto buscado = new Auto();
            buscado.setModelo(modelo);
            session().save(buscado);
        }
    }

    private List<Auto> whenBuscoAutosConModelo(String modelo) {
        return repositorioEnviarAutoAMantenimiento.buscarPorModelo(modelo);
    }

    private void thenEncuentroLosAutosPorModelo(List<Auto> autosEncontrados, int cantidadDeAutosEncontrados) {
        assertThat(autosEncontrados).hasSize(cantidadDeAutosEncontrados);
        for (Auto auto : autosEncontrados) {
            assertThat(auto.getModelo()).isEqualTo(FOCUS);
        }
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarPorMarca() {
        givenExistenAutosConMarca(FORD, 3);
        givenExistenAutosConMarca(TOYOTA, 2);
        List<Auto> autosEncontrados = whenBuscoAutosPorMarca(FORD);
        thenEncuentroLosAutosPorMarca(autosEncontrados, 3);
    }

    private void givenExistenAutosConMarca(String marca, int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto buscado = new Auto();
            buscado.setMarca(marca);
            session().save(buscado);
        }
    }

    private List<Auto> whenBuscoAutosPorMarca(String marca) {
        return repositorioEnviarAutoAMantenimiento.buscarPorMarca(marca);
    }

    private void thenEncuentroLosAutosPorMarca(List<Auto> autosEncontrados, int cantidadDeAutosEncontrados) {
        assertThat(autosEncontrados).hasSize(cantidadDeAutosEncontrados);
        for (Auto auto : autosEncontrados) {
            assertThat(auto.getMarca()).isEqualTo(FORD);
        }
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnAutoPorId() {
        Auto existente = givenExisteUnAutoConId();
        Auto encontrado = whenBuscoUnAutoConId(existente);
        thenEncuentroElAutoPorId(encontrado);
    }

    private Auto givenExisteUnAutoConId() {
        Auto existente = new Auto();
        session().save(existente);
        return existente;
    }

    private Auto whenBuscoUnAutoConId(Auto buscado) {
        return repositorioEnviarAutoAMantenimiento.buscarPorId(buscado.getId());
    }

    private void thenEncuentroElAutoPorId(Auto encontrado) {
        assertThat(encontrado).isNotNull();
    }
}
