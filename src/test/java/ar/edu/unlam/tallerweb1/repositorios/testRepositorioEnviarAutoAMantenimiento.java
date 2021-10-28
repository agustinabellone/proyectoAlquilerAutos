package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Mantenimiento;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDate;
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
        return repositorioEnviarAutoAMantenimiento.buscarPorPatente(patente);
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
            buscado.setModelo(new Modelo());
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
            buscado.setMarca(new Marca());
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


    private void givenExistenAutosConAnioDeFabriacion(LocalDate anioDeFabricacion, int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto buscado = new Auto();
            buscado.setAñoFabricación(anioDeFabricacion);
            session().save(buscado);
        }
    }

    private List<Auto> whenBuscoUnAutoConAnioDeFabricacion(int anioDeFabricacion) {
        return repositorioEnviarAutoAMantenimiento.buscarPorAnioDeFabricacion(anioDeFabricacion);
    }

    private void thenEncuentroElAutoPorAnioDeFabricacion(List<Auto> encontrado, int cantidadDeAutosEncontrados) {
        assertThat(encontrado).hasSize(cantidadDeAutosEncontrados);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAutoEnMantenimiento() {
        Auto existente = givenExisteUnAuto();
        Mantenimiento creado = givenExisteUnMantenimiento();
        Auto guardado = whenGuardoUnAutoEnMantenimiento(existente);
        thenLoPuedoBuscarPorId(guardado);
    }

    private Auto givenExisteUnAuto() {
        Auto auto = new Auto();
        session().save(auto);
        return auto;
    }

    private Mantenimiento givenExisteUnMantenimiento() {
        Mantenimiento mantenimiento = new Mantenimiento();
        session().save(mantenimiento);
        return mantenimiento;
    }

    private Auto whenGuardoUnAutoEnMantenimiento(Auto existente) {
        return repositorioEnviarAutoAMantenimiento.guardarAutoMantenimiento(existente);
    }

    private void thenLoPuedoBuscarPorId(Auto guardado) {
        assertThat(session().get(Auto.class, guardado.getId())).isNotNull();
        assertThat(guardado).isEqualTo("EN MANTENIMIENTO");
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerAutosEnMantenimiento() {
        givenExisteUnAutoenMantenimiento( 3);
        List<Auto> autosObtenidos = whenObtengoLosAutosEnMantenimiento();
        thenObtengoLosAutosEnMantenimiento(autosObtenidos, 3);
    }

    private void givenExisteUnAutoenMantenimiento(int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto enMantenimiento = new Auto();
            enMantenimiento.setPatente("ABC" + i);
           // enMantenimiento.setEstado("EN MANTENIMIENTO");
            session().save(enMantenimiento);
        }
    }

    private List<Auto> whenObtengoLosAutosEnMantenimiento() {
        return repositorioEnviarAutoAMantenimiento.obtenerAutosEnMantenimiento();
    }

    private void thenObtengoLosAutosEnMantenimiento(List<Auto> autosObtenidos, int cantidadDeAutoosObtenidos) {
        assertThat(autosObtenidos).hasSize(3);
        for (Auto auto : autosObtenidos) {
            assertThat(auto).isNotNull();
            assertThat(auto).isEqualTo("EN MANTENIMIENTO");
        }
    }
}