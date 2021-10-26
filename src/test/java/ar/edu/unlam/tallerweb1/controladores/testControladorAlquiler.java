package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class testControladorAlquiler {

    Usuario usuario = new Usuario();
    Auto auto = new Auto((long)123, "", "", "", "", null, null);

    private ServicioAlquiler servicioAlquiler = mock(ServicioAlquiler.class);
    private ControladorAlquiler controladorAlquiler = new ControladorAlquiler(servicioAlquiler);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate salida = LocalDate.of(2021, 1, 15);
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static final LocalDate ingreso = LocalDate.of(2021, 1, 18);

    DatosAlquiler datosAlquiler = new DatosAlquiler(usuario, auto, salida, ingreso);

    private ModelAndView mav;

    @Test
    public void queSePuedaAlquilarUnAuto(){
        givenExisteUnAutoYUnCliente();
        whenUnClienteAlquilaUnAuto(usuario, auto, salida, ingreso);
        thenElAlquilerEsExitoso();
    }

    private void givenExisteUnAutoYUnCliente() {}

    private void whenUnClienteAlquilaUnAuto(Usuario usuario, Auto auto, LocalDate salida, LocalDate ingreso) {

        mav =  controladorAlquiler.alquilarAuto(usuario.getId(), auto.getId(), salida, ingreso);
    }

    private void thenElAlquilerEsExitoso() {

        assertThat(mav.getViewName()).isEqualTo("pantallaAlquilerRealizado");
    }

    ///////////////////////////////////////////

    @Test
    public void noPuedeHaberDosAlquileresIguales(){
        givenExisteUnAlquiler(usuario, auto, salida, ingreso);
        doThrow(AutoYaAlquiladoException.class)
                .when(servicioAlquiler)
                .AlquilarAuto(datosAlquiler);
        whenUnClienteAlquilaUnAuto(usuario, auto, salida, ingreso);
        thenElAlquilerFalla();

    }

    private void givenExisteUnAlquiler(Usuario usuario, Auto auto, LocalDate salida, LocalDate ingreso) {
        controladorAlquiler.alquilarAuto(usuario.getId(), auto.getId(), salida, ingreso);
    }

    private void thenElAlquilerFalla() {
        assertThat(mav.getViewName()).isEqualTo("alquilarAutoConfirmacion");
    }

}
