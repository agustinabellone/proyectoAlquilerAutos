package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class testControladorAdministrador {
    private ControladorAdministrador controlador;
    private ModelAndView modelAndView;

    @Before
    public void init(){
        controlador = new ControladorAdministrador();
        modelAndView = new ModelAndView();
    }

    @Test
    public void alAccederALaVistaPrincipalNoPuedePorqueNoEstaAsignadoElRolDeAdministrador() {
        givenExisteUnUsuarioSinRolDeAdministrador();
        whenAccedeALaVistaPrincipal();
        thenloMandaAlLoginConMensajeDeError(this.modelAndView,"No tienes los permisos necesarios");
    }

    private void givenExisteUnUsuarioSinRolDeAdministrador() {
    }

    private void whenAccedeALaVistaPrincipal() {
        this.modelAndView = controlador.irALaVistaPrincipal();
    }

    private void thenloMandaAlLoginConMensajeDeError(ModelAndView modelAndView, String error) {
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
        assertThat(modelAndView.getModel().get("error")).isEqualTo(error);
    }
}
