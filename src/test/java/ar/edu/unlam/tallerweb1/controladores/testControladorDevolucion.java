package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class testControladorDevolucion {

    Auto auto = new Auto();
    private ControladorDevolucion controladorDevolucion = new ControladorDevolucion();

    @Test
    public void testQueDevuelveUnAutoCorrectamente() {
        Alquiler alquiler = givenQueExisteUnAlquiler();
        ModelAndView mav = whenElAutoSeDevuelva(alquiler);
        thenElAutoSeDevuelveCorrectamente(mav);
    }

    @Test
    public void testQueDevuelveUnAutoIncorrectamente() {
        Alquiler alquiler = givenQueNoExistaUnAlquiler();
        ModelAndView mav = whenElAutoSeDevuelva(alquiler);
        thenElAutoSeDevuelveIncorrectamente(mav);
    }

    private void thenElAutoSeDevuelveCorrectamente(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("alquilerFinalizado");
    }

    private ModelAndView whenElAutoSeDevuelva(Alquiler alquiler) {
        return controladorDevolucion.devolver(alquiler);
    }

    private Alquiler givenQueExisteUnAlquiler() {
        return new Alquiler(auto);
    }

    private void thenElAutoSeDevuelveIncorrectamente(ModelAndView mav) {
        assertThat(mav.getModel().get("Error")).isEqualTo("Error de Devolucion");
        assertThat(mav.getViewName()).isEqualTo("errorDevolucion");
    }

    private Alquiler givenQueNoExistaUnAlquiler() {
        return null;
    }


}

