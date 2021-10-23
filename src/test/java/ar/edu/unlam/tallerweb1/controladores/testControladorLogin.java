package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testControladorLogin {

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";

    private ModelAndView mav;

    private ServicioLogin servicioLogin;
    private ControladorLogin controladorLogin;
    private ServicioUsuario servicioUsuario;
    private MockHttpServletRequest request = new MockHttpServletRequest();

    @Before
    public void init(){
        servicioLogin = mock(ServicioLogin.class);
        servicioUsuario = mock(ServicioUsuario.class);
        controladorLogin = new ControladorLogin(servicioLogin, servicioUsuario);
    }

    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        DatosLogin datosLogin = new DatosLogin(EMAIL, CLAVE);
        whenElClienteIngresa(datosLogin);
        thenElIngresoEsExitoso();
    }

    private void givenUsuarioExiste() {}

    private void whenElClienteIngresa(DatosLogin datosLogin) {
        mav = controladorLogin.ingresar(datosLogin, request);
    }

    private void thenElIngresoEsExitoso() {
        assertThat(mav.getViewName()).isEqualTo("redirect:/main");
    }

    ////////////////////////////////////////////////////

    @Test
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        DatosLogin datosLogin = new DatosLogin(EMAIL_INCORRECTO, CLAVE);
        doThrow(ClienteNoExisteException.class)
                .when(servicioLogin)
                .ingresar(datosLogin);
        whenElClienteIngresa(datosLogin);
        thenElIngresoFalla("El usuario no existe");
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }

}
