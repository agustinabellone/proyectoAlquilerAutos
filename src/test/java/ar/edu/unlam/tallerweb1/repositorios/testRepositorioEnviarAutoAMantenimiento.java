package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class testRepositorioEnviarAutoAMantenimiento extends SpringTest {

    private static final Marca FORD = new Marca();
    private static final Modelo FOCUS = new Modelo();
    @Autowired
    private RepositorioAuto repositorioAuto;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAutoCorrectamente() {
        Auto aGuardar = givenExisteUnAuto();
        Long id = whenLoGuardo(aGuardar);
        thenLoPuedoBuscar(id);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarAutosPorMarca() {
        givenExistenAutosConMarca(FORD, 3);
        List<Auto> autosEncontrados = whenBuscoAutosPorMarca(FORD);
        thenEncuentro(autosEncontrados, 3);
    }


    @Test
    @Rollback
    @Transactional
    public void queSePuedeBuscarAutosPorModelo() {
        givenExisteUnAutoConModelo(FOCUS, 3);
        List<Auto> autosObtenidos = whenBuscoAutoPorModelo(FOCUS);
        thenEncuentro(autosObtenidos,3);
    }



    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAutoEnMantenimientoYLaFechaEnQueSeEnvia(){
        Auto paraMantenimientio = givenExisteUnAuto();
        LocalDate localDate = givenLaFechaActual();

        Auto buscado = whenLoGuardoEnMatenimiento(paraMantenimientio,localDate);
        thenEncuentroElAuto(buscado);
    }

    private void thenEncuentroElAuto(Auto buscado) {
        Auto auto = repositorioAuto.buscarPor(buscado.getId());
        assertThat(auto).isNotNull();
    }

    private LocalDate givenLaFechaActual() {
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    private Auto whenLoGuardoEnMatenimiento(Auto paraMantenimientio, LocalDate localDate) {
        repositorioAuto.guardarEnMantenimiento(paraMantenimientio, localDate);
        return repositorioAuto.buscarPor(paraMantenimientio.getId());
    }

    private void givenExistenAutosConMarca(Marca marca, int cantidadDeAutos) {
        for (int i = 0; i < cantidadDeAutos; i++) {
            Auto auto = new Auto();
            auto.setMarca(marca);
            session().save(auto);
            session().save(marca);
        }
    }

    private Auto givenExisteUnAuto() {
        Auto auto = new Auto();
        session().save(auto);
        return auto;
    }

    private void givenExisteUnAutoConModelo(Modelo modelo, int cantidadDeAutosConEseModelo) {
        for (int i = 0; i <cantidadDeAutosConEseModelo; i++) {
            Auto auto = new Auto();
            auto.setModelo(modelo);
            session().save(auto);
            session().save(modelo);
        }
    }


    private List<Auto> whenBuscoAutosPorMarca(Marca marca) {
        return repositorioAuto.buscarPorMarca(marca);
    }

    private List<Auto> whenBuscoAutoPorModelo(Modelo modelo) {
        return repositorioAuto.buscarPorModelo(modelo);
    }


    private Long whenLoGuardo(Auto aGuardar) {
        session().save(aGuardar);
        return aGuardar.getId();
    }

    private void thenLoPuedoBuscar(Long id) {
        Auto auto = repositorioAuto.buscarPor(id);
        assertThat(auto).isNotNull();
    }

    private void thenEncuentro(List<Auto> autosEncontrados, int cantidadDeAutosEncontrados) {
        assertThat(autosEncontrados).hasSize(cantidadDeAutosEncontrados);
    }
}
