package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class testServicioAlquiler {

    Cliente cliente = new Cliente();
    Auto auto = new Auto();

    private static final String fechaInicio = "10/01/21";
    private static final String fechaSalida = "18/01/21";

    DatosAlquiler datosAlquiler = new DatosAlquiler(cliente, auto, fechaInicio, fechaSalida );

    private ServicioAlquilar servicioAlquiler= new ServicioAlquilarImpls();



    @Test
    public void queSeCreeCorrectamenteUnAlquiler(){

        givenExisteUnAutoYUnCliente();
        Alquiler alquiler= whenUnClienteAlquilaUnAuto(datosAlquiler);
        thenElAlquilerEsExitoso(alquiler);

    }

    @Test(expected = AutoYaAlquiladoException.class)
    public void queNoSePuedaAlquilarDosVecesElMismoAuto(){

        givenExisteUnAlquiler(datosAlquiler);
        Alquiler alquiler= whenUnClienteAlquilaElMismoAuto(datosAlquiler);
        thenElAlquilerFalla(alquiler);

    }

    private void givenExisteUnAlquiler(DatosAlquiler DA) {
        servicioAlquiler.AlquilarAuto(DA);
    }

    private Alquiler whenUnClienteAlquilaElMismoAuto(DatosAlquiler DA) {

        return servicioAlquiler.AlquilarAuto(DA);
    }

    private void thenElAlquilerFalla(Alquiler alquiler) {

        assertThat(alquiler).isNull();

    }

    private void givenExisteUnAutoYUnCliente() {
    }

    private Alquiler whenUnClienteAlquilaUnAuto(DatosAlquiler DA) {

        return servicioAlquiler.AlquilarAuto(DA);

    }

    private void thenElAlquilerEsExitoso(Alquiler a) {

        assertThat(a).isNotNull();
    }

}
