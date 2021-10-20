package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static org.assertj.core.api.Assertions.*;

public class testServicioDevolucion {

    Auto auto = new Auto();
    private ServicioDevolucion servicioDevolucion = new ServicioDevolucionImpl();

    @Test
    public void testQueSeDevuelveCorrectamenteUnAutoAlFinalizarAlquiler() {
        Alquiler alquiler = givenExisteUnAlquiler();
        whenSeDevuelveUnAuto(alquiler);
        thenUnAutoEsDevueltoCorrectamente(alquiler);
    }

    @Test (expected = NullPointerException.class)
    public void testQueSeDevuelveIncorrectamenteUnAutoAlFinalizarAlquilerInexistente() {
        Alquiler alquiler = givenNoExisteUnAlquiler();
        whenSeDevuelveUnAuto(alquiler);
        thenUnAutoEsDevueltoIncorrectamente(alquiler);
    }

    private void thenUnAutoEsDevueltoIncorrectamente(Alquiler alquiler) {
        assertThat(alquiler).isNull();
    }

    private Alquiler givenNoExisteUnAlquiler() {
        return null;
    }

    private void thenUnAutoEsDevueltoCorrectamente(Alquiler alquiler) {
        assertThat(alquiler.getAuto().getSituacion().DISPONIBLE;
    }

    private void whenSeDevuelveUnAuto(Alquiler alquiler) {
        servicioDevolucion.finalizarAlquiler(alquiler);
    }

    private Alquiler givenExisteUnAlquiler() {
        return new Alquiler(auto);
    }


}
