package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testControladorRegistro {

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String CLAVE_DISTINTA = "87521458";
    public static final String CLAVE_LONGITUD_INCORRECTA = "8752";

    private ModelAndView mav;

    private ServicioRegistro servicioRegistro;
    private ServicioUsuario servicioUsuario;
    private ControladorRegistro controladorRegistro;

    @Before
    public void init(){
        servicioRegistro = mock(ServicioRegistro.class);
        servicioUsuario = mock(ServicioUsuario.class);
        controladorRegistro = new ControladorRegistro(servicioRegistro, servicioUsuario);
    }


    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = new DatosRegistro(EMAIL, CLAVE, CLAVE);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElRegistroEsExitoso();
    }

    private void givenUsuarioNoExiste() {}

    private void whenRegistroUnUsuarioConClaves(DatosRegistro datosRegistro) {
        mav = controladorRegistro.registrar(datosRegistro);
    }

    private void thenElRegistroEsExitoso() {
        assertThat(mav.getViewName()).isEqualTo("redirect:/login");
    }

    ////////////////////////////////////////////////////////////

    @Test
    public void siLasClavesSonDistintasElRegistroFalla(){
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = new DatosRegistro(EMAIL, CLAVE, CLAVE_DISTINTA);
        doThrow(ClavesDistintasException.class)
                .when(servicioRegistro)
                .registrar(datosRegistro);
        whenRegistroUnUsuarioConClaves(datosRegistro);
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
        DatosRegistro datosRegistro = new DatosRegistro(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        doThrow(ClaveLongitudIncorrectaException.class)
                .when(servicioRegistro)
                .registrar(datosRegistro);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
    }
}
