package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorio.TablaAlquiler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class testControladorAlquiler {

    Cliente cliente = new Cliente();
    Auto auto = new Auto();

    ControladorAlquiler controladorAlquiler= new ControladorAlquiler();

    private static final String fechaInicio = "10/01/21";
    private static final String fechaSalida = "18/01/21";

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida );

    private ModelAndView mav;

    @Before
    public void init() {

        TablaAlquiler.getInstance().reset();
    }

    @Test
    public void queSePuedaAlquilarUnAuto(){
        givenExisteUnAutoYUnCliente();
        whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerEsExitoso();
    }

    private void givenExisteUnAutoYUnCliente() {
    }

    private void whenUnClienteAlquilaUnAuto(DatosAlquiler datosAlquiler) {

        mav =  controladorAlquiler.alquilarAuto(datosAlquiler);

    }

    private void thenElAlquilerEsExitoso() {

        assertThat(mav.getViewName()).isEqualTo("home"); //Vista placeholder
    }

    ///////////////////////////////////////////

    @Test
    public void noPuedeHaberDosAlquileresIguales(){
        givenExisteUnAlquiler(datosAlquiler);
        whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerFalla();
        
    }

    private void givenExisteUnAlquiler(DatosAlquiler datosAlquiler) {

        controladorAlquiler.alquilarAuto(datosAlquiler);

    }

    private void thenElAlquilerFalla() {

        assertThat(mav.getViewName()).isEqualTo("ir-alquiler-auto"); //Vista placeholder

    }

}
