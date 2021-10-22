package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class testContraldorAdmin {

    private static final String ADMIN = "ADMIN";
    private ControladorAdmin controladorAdmin = new ControladorAdmin();

    @Test
    public void queElUsuarioAdministradorPuedaAccederAlPanelPrincipal() {
        givenExisteUnUsuarioConRol(ADMIN);
        ModelAndView modelAndView = whenAccedeAlPanelPrincipal();
        thenSeMuestraElPanelPrincipal(modelAndView);
    }

    private void givenExisteUnUsuarioConRol(String admin) {

    }

    private ModelAndView whenAccedeAlPanelPrincipal() {
        return controladorAdmin.irAlPanelPrincipal();
    }

    private void thenSeMuestraElPanelPrincipal(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("panel-principal");
    }
}
