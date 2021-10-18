package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaInicio = LocalDate.of(2021, 1, 15);
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaSalida = LocalDate.of(2021, 1, 18);


    @Autowired
    private RepositorioAlquiler repositorioAlquiler;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioAuto repositorioAuto;

    @Test
    @Transactional
    @Rollback
    public void guardarUnAlquilerDeberiaPersistirlo(){
        DatosRegistro datosRegistro = new DatosRegistro("agus@gmail.com", "12345678", "12345678");
        Cliente cliente = givenExisteUnCliente(datosRegistro);

        Auto auto = new Auto((long)123, "", "", "", "", null, null);
        givenExisteUnAuto(auto);

        DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida);
        Alquiler alquiler = givenExisteUnAlquiler(datosAlquiler);
        Long id = whenGuardoUnAlquiler(alquiler);
        thenEncuentroElAlquiler(id);
    }

    private void givenExisteUnAuto(Auto auto) {
        repositorioAuto.guardar(auto);
    }

    private Cliente givenExisteUnCliente(DatosRegistro datosRegistro) {
        Cliente cliente = new Cliente(datosRegistro);
        repositorioCliente.guardar(cliente);
        return cliente;
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
