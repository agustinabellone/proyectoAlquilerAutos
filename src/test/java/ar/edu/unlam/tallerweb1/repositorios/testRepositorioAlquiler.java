package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class testRepositorioAlquiler extends SpringTest {

    Cliente cliente = new Cliente();
    Auto auto = new Auto((long)123, "", "", "", "", null, null);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaInicio = LocalDate.of(2021, 1, 15);
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaSalida = LocalDate.of(2021, 1, 18);

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida);

    @Autowired
    private RepositorioAlquiler repositorioAlquiler;

    @Test
    @Transactional
    @Rollback
    public void guardarUnAlquilerDeberiaPersistirlo(){
        Alquiler alquiler = givenExisteUnAlquiler(datosAlquiler);
        Long id = whenGuardoUnAlquiler(alquiler);
        thenEncuentroElAlquiler(id);
    }

    private Alquiler givenExisteUnAlquiler(DatosAlquiler datosAlquiler) {
        Alquiler alquiler = new Alquiler(datosAlquiler);
        return alquiler;
    }

    private Long whenGuardoUnAlquiler(Alquiler alquiler) {
        repositorioAlquiler.guardar(alquiler);
        return alquiler.getId();
    }

    private void thenEncuentroElAlquiler(Long id) {
        Alquiler alquiler = repositorioAlquiler.buscarAlquilerPorId(id);
        assertThat(alquiler).isNotNull();
    }

}
