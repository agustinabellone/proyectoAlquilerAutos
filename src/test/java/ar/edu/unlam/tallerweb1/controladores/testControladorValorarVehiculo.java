package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;

public class testControladorValorarVehiculo {
    private ModelAndView mav;
    private static Auto auto = new Auto();
    private static Cliente cliente=new Cliente();


    @Test
    public void queUnUsuarioPuedaValorarUnVehiculoQueUtilizo(){
        givenQueExisteVehiculoQueUtilizo();
        whenValoroVehiculo();
        thenLaValoracionEsExitosa();

    }

    private void givenQueExisteVehiculoQueUtilizo() {
    }

    private void whenValoroVehiculo() {
        mav=ControladorValorarVehiculo.valorarVehiculo(numeroValoracion,comentario);
    }

    private void thenLaValoracionEsExitosa() {
      /*  assertThat(mav.getViewName()).isEqualTo("redirect:/login");*/

    }













}
