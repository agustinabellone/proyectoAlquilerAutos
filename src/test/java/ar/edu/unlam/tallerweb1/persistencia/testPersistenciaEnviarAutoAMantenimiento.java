package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEnviarAutoAMantenimiento;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class testPersistenciaEnviarAutoAMantenimiento extends SpringTest {
    //insertar, modificar, eliminar
    @Autowired
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaGuardarUnAuto() {
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
