package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioEnviarAutoAMantenimiento extends SpringTest {

    @Autowired
    private RepositorioAuto repositorioAuto;
    private LocalDate localDate = LocalDate.now();

    @Test @Rollback @Transactional
    public void queSePuedaEnviarUnAutoDisponibleAMantenimiento() {
        Long id_auto = givenExisteUnAutoDisponible();
        Auto actualizado = whenLoEnvioAMantenimiento(id_auto, Situacion.EN_MANTENIMIENTO);
        thenObtengoElAuto(actualizado);
    }

    private Long givenExisteUnAutoDisponible() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.DISPONIBLE);
        session().save(auto);
        return auto.getId();
    }

    private Auto whenLoEnvioAMantenimiento(Long id_auto, Situacion enMantenimiento) {
        return repositorioAuto.enviarAMantenimiento(id_auto, enMantenimiento, localDate);
    }

    private void thenObtengoElAuto(Auto actualizado) {
        assertThat(actualizado).isNotNull();
        assertThat(actualizado.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
    }
}
