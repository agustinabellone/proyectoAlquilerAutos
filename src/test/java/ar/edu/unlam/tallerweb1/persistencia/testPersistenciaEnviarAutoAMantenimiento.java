package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEnviarAutoAMantenimiento;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class testPersistenciaEnviarAutoAMantenimiento extends SpringTest {
    //insertar, modificar, eliminar
    @Autowired
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAuto() {
        Auto existente = givenExisteUnAuto();
        Long idDelAuto = whenGuardoElAuto(existente);
        thenPuedoBuscarloPorId(idDelAuto);
    }

    private Auto givenExisteUnAuto() {
        return new Auto();
    }

    private Long whenGuardoElAuto(Auto existente) {
        session().save(existente);
        return existente.getId();
    }

    private void thenPuedoBuscarloPorId(Long idDelAuto) {
        assertThat(session().get(Auto.class,idDelAuto)).isNotNull();
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnMatenimiento() {
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAutoEnMantenimiento() {

    }
}
