package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class testControladorEnviarAutoAMentenimineto {
    private static final String ADMIN = "admin";
    private static final String INVITADO = "invitado";
    private ControladorEnviarAutoAMantenimiento controlador = new ControladorEnviarAutoAMantenimiento();
    private ModelAndView modelAnView = new ModelAndView();

    @Test
    public void queUnUsuarioAdministradorPuedaEnviarUnAutoAMantenimiento() {
        Auto aEnviar = givenExisteUnAuto();
        Usuario conRolAdmin = givenExisteUnUsuarioConRol(ADMIN);

        whenElUsuarioAdministradorEnviaElAutoAMantenimiento(conRolAdmin, aEnviar);

        thenElEnvioEsExitoso(this.modelAnView);
    }

    private Auto givenExisteUnAuto() {
        return new Auto();
    }

    private Usuario givenExisteUnUsuarioConRol(String rol) {
        Usuario conRol = new Usuario();
        conRol.setRol(rol);
        return conRol;
    }

    private void whenElUsuarioAdministradorEnviaElAutoAMantenimiento(Usuario conRolAdmin, Auto aEnviar) {
        this.modelAnView = controlador.enviarAutoAMantenimiento(conRolAdmin, aEnviar);
    }

    private void thenElEnvioEsExitoso(ModelAndView modelAnView) {
        assertThat(modelAnView.getViewName()).isEqualTo("lista-de-autos");
        assertThat(modelAnView.getModel().get("mensaje")).isEqualTo("Se envio un auto correctamente a mantenimiento");
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnAutoAMantenimiento(){
        Auto aEnviar = givenExisteUnAuto();
        Usuario conRol = givenExisteUnUsuarioConRol(INVITADO);
        whenElUsuarioAdministradorEnviaElAutoAMantenimiento(conRol,aEnviar);
        thenElEnvioFalla(this.modelAnView);
    }

    private void thenElEnvioFalla(ModelAndView modelAnView) {
        assertThat(modelAnView.getViewName()).isEqualTo("home");
        assertThat(modelAnView.getModel().get("mensaje")).isEqualTo("No tienes permisos para realizar esta accion");
    }
}
