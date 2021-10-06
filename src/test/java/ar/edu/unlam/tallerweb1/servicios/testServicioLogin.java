package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.repositorio.TablaCliente;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class testServicioLogin {

    private ServicioLogin servicioLogin = new ServicioLogin();
    private ServicioRegistro servicioRegistro = new ServicioRegistro();

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";

    @Before
    public void init(){
        TablaCliente.getInstance().reset(); //DESPUES SE VA A HACER DE OTRA FORMA
    }

    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        Boolean ingresoUsuario = whenElClienteIngresa(EMAIL, CLAVE);
        thenElIngresoEsExitoso(ingresoUsuario);
    }

    private void givenUsuarioExiste() {
        DatosRegistro datosRegistro = new DatosRegistro(EMAIL, CLAVE, CLAVE);
        servicioRegistro.registrar(datosRegistro);
    }

    private boolean whenElClienteIngresa(String email, String clave) {
        return servicioLogin.ingresar(new DatosLogin(email, clave));
    }

    private void thenElIngresoEsExitoso(Boolean ingresoUsuario) {
        assertThat(ingresoUsuario).isTrue();
    }

    @Test(expected = ClienteNoExisteException.class)
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        Boolean ingresoUsuario = whenElClienteIngresa(EMAIL_INCORRECTO, CLAVE);
        thenElIngresoFalla(ingresoUsuario);
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(Boolean ingresoUsuario) {
        assertThat(ingresoUsuario).isFalse();
    }

}
