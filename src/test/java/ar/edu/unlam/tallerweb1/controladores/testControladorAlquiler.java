package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class testControladorAlquiler {
    Cliente cliente = new Cliente();
    Auto auto = new Auto();
    ControladorAlquiler controladorAlquiler= new ControladorAlquiler();
    final String fechaInicio = "10/01/21";
    final String fechaSalida = "18/01/21";

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto,fechaInicio, fechaSalida );

    @Test
    public void queSePuedaAlquilarUnAuto(){
        givenExisteUnAutoYUnCliente();
        Alquiler alquiler = whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerEsExitoso(alquiler);
    }

    private void givenExisteUnAutoYUnCliente() {
    }

    private Alquiler whenUnClienteAlquilaUnAuto(DatosAlquiler datosAlquiler) {
        return controladorAlquiler.alquilarAuto(datosAlquiler);
    }

    private void thenElAlquilerEsExitoso(Alquiler alquiler) {
        assertThat(alquiler).isNotNull();
    }

    ///////////////////////////////////////////

    @Test
    public void noPuedeHaberDosAlquileresIguales(){
        givenExisteUnAlquiler(datosAlquiler);
        Alquiler alquiler = whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerFalla(alquiler);
        
    }

    private Alquiler givenExisteUnAlquiler(DatosAlquiler datosAlquiler) {
        return controladorAlquiler.alquilarAuto(datosAlquiler);
    }

    private void thenElAlquilerFalla(Alquiler alquiler) {
        assertThat(alquiler).isNull();
    }

}
