package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

public class testRepositorioEnviarAutoAMantenimiento extends SpringTest {

    public static final String PATENTE = "ABC123";

    @Autowired
    private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarPorPatente() {
        givenExisteUnAutoConPatente(PATENTE);
        Auto buscado = whenBuscoUnAutoConPatente(PATENTE);
        thenEncuentroEl(buscado);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarPorModelo(){}

    private void givenExisteUnAutoConPatente(String patente) {
        Auto buscado = new Auto();
        buscado.setPatente(patente);
        session().save(buscado);
    }

    private Auto whenBuscoUnAutoConPatente(String patente) {
        return repositorioEnviarAutoAMantenimiento.buscarPor(patente);
    }

    private void thenEncuentroEl(Auto buscado) {
        assertThat(buscado).isNotNull();
    }
}
