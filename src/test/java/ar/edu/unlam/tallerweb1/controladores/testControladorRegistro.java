package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class testControladorRegistro {

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String CLAVE_DISTINTA = "87521458";
    public static final String CLAVE_LONGITUD_INCORRECTA = "8752";

    private ModelAndView mav;

    private ServicioRegistro servicioRegistro = mock(ServicioRegistro.class);
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioRegistro);

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){
        givenUsuarioNoExiste();
        whenRegistroUnUsuarioConClaves(EMAIL, CLAVE, CLAVE);
        thenElRegistroEsExitoso();
    }

    private void givenUsuarioNoExiste() {}

    private void whenRegistroUnUsuarioConClaves(String email, String clave, String repiteClave) {
        DatosRegistro datosRegistro = new DatosRegistro(email, clave, repiteClave);
        mav = controladorRegistro.registrar(datosRegistro);
    }

    private void thenElRegistroEsExitoso() {
        assertThat(mav.getViewName()).isEqualTo("redirect:/login");
    }

    ////////////////////////////////////////////////////////////

    @Test
    public void siLasClavesSonDistintasElRegistroFalla(){
        givenUsuarioNoExiste();
        whenRegistroUnUsuarioConClaves(EMAIL, CLAVE, CLAVE_DISTINTA);
        thenElRegistroFalla("Las claves deben ser iguales");
    }


    private void thenElRegistroFalla(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("registro");
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }

    ////////////////////////////////////////////////

    @Test
    public void siLaClaveTieneMenosDeOchoCaracteresElRegistroFalla(){
        givenUsuarioNoExiste();
        whenRegistroUnUsuarioConClaves(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
    }
}
