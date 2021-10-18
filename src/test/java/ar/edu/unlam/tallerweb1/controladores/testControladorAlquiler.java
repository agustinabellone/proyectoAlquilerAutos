package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class testControladorAlquiler {

    Cliente cliente = new Cliente();
    Auto auto = new Auto((long)123, "", "", "", "", null, null);

    private ServicioAlquiler servicioAlquiler = mock(ServicioAlquiler.class);
    private ControladorAlquiler controladorAlquiler = new ControladorAlquiler(servicioAlquiler);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaInicio = LocalDate.of(2021, 1, 15);
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate fechaSalida = LocalDate.of(2021, 1, 18);

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida );

    private ModelAndView mav;

    @Test
    public void queSePuedaAlquilarUnAuto(){
        givenExisteUnAutoYUnCliente();
        whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerEsExitoso();
    }

    private void givenExisteUnAutoYUnCliente() {}

    private void whenUnClienteAlquilaUnAuto(DatosAlquiler datosAlquiler) {
        mav =  controladorAlquiler.alquilarAuto(datosAlquiler);
    }

    private void thenElAlquilerEsExitoso() {

        assertThat(mav.getViewName()).isEqualTo("pantallaAlquilerRealizado");
    }

    ///////////////////////////////////////////

    @Test
    public void noPuedeHaberDosAlquileresIguales(){
        givenExisteUnAlquiler(datosAlquiler);
        doThrow(AutoYaAlquiladoException.class)
                .when(servicioAlquiler)
                .AlquilarAuto(datosAlquiler);
        whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerFalla();

    }

    private void givenExisteUnAlquiler(DatosAlquiler datosAlquiler) {
        controladorAlquiler.alquilarAuto(datosAlquiler);
    }

    private void thenElAlquilerFalla() {
        assertThat(mav.getViewName()).isEqualTo("alquilarAutoConfirmacion");
    }

}
