package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class testControladorLogin {

    private ServicioLogin servicioLogin = mock(ServicioLogin.class);
    private ControladorLogin controladorLogin = new ControladorLogin(servicioLogin);

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";

    private ModelAndView mav;

    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        whenElClienteIngresa(EMAIL, CLAVE);
        thenElIngresoEsExitoso();
    }

    private void givenUsuarioExiste() {}

    private void whenElClienteIngresa(String email, String clave) {
        DatosLogin datosLogin = new DatosLogin(email, clave);
        mav = controladorLogin.ingresar(datosLogin);

    }

    private void thenElIngresoEsExitoso() {
        assertThat(mav.getViewName()).isEqualTo("redirect:/main");
    }

    ////////////////////////////////////////////////////

    @Test
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        whenElClienteIngresa(EMAIL_INCORRECTO, CLAVE);
        thenElIngresoFalla("El usuario no existe");
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }

}
