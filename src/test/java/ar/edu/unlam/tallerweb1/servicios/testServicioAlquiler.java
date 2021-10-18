package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class testServicioAlquiler {

    Cliente cliente = new Cliente();
    Auto auto = new Auto((long)123, "", "", "", "", null, null);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaInicio = LocalDate.of(2021, 1, 15);
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaSalida = LocalDate.of(2021, 1, 18);

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida);

    private RepositorioAlquiler repositorioAlquiler = mock(RepositorioAlquiler.class);
    private ServicioAlquiler servicioAlquiler = new ServicioAlquilerImpl(repositorioAlquiler);


    @Test
    public void queSeCreeCorrectamenteUnAlquiler(){
        givenExisteUnAutoYUnCliente();
        Alquiler alquiler= whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerEsExitoso(alquiler);
    }

    @Test(expected = AutoYaAlquiladoException.class)
    public void queNoSePuedaAlquilarDosVecesElMismoAuto(){
        givenExisteUnAlquiler(datosAlquiler);
        doThrow(AutoYaAlquiladoException.class).when(repositorioAlquiler).buscarAutoPorId(auto.getId());
        Alquiler alquiler= whenUnClienteAlquilaUnAutoYaAlquilado(datosAlquiler);
        thenElAlquilerFalla(alquiler);
    }

    private void givenExisteUnAlquiler(DatosAlquiler DA) {
        servicioAlquiler.AlquilarAuto(DA);
    }

    private Alquiler whenUnClienteAlquilaUnAutoYaAlquilado(DatosAlquiler DA) {
        return servicioAlquiler.AlquilarAuto(DA);
    }

    private void thenElAlquilerFalla(Alquiler alquiler) {
        assertThat(alquiler).isNull();
    }

    private void givenExisteUnAutoYUnCliente() {}

    private Alquiler whenUnClienteAlquilaUnAuto(DatosAlquiler DA) {
        return servicioAlquiler.AlquilarAuto(DA);
    }

    private void thenElAlquilerEsExitoso(Alquiler a) {
        assertThat(a).isNotNull();
    }

}
