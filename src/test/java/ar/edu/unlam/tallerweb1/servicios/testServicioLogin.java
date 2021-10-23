package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.*;

public class testServicioLogin {

    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioLogin servicioLogin = new ServicioLoginImpl(repositorioUsuario);

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";


    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        DatosLogin datosLogin = new DatosLogin(EMAIL, CLAVE);
        boolean ingreso = whenElClienteIngresa(datosLogin);
        thenElIngresoEsExitoso(ingreso);
    }

    private void givenUsuarioExiste() {
        when(repositorioUsuario.buscarPorEmail(EMAIL)).thenReturn(new Usuario());
    }

    private boolean whenElClienteIngresa(DatosLogin datosLogin) {
        return servicioLogin.ingresar(datosLogin);
    }

    private void thenElIngresoEsExitoso(boolean ingreso) {
        assertThat(ingreso).isTrue();
    }

    @Test(expected = ClienteNoExisteException.class)
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        DatosLogin datosLogin = new DatosLogin(EMAIL_INCORRECTO, CLAVE);
        doThrow(ClienteNoExisteException.class).when(repositorioUsuario).buscarPorEmail(EMAIL_INCORRECTO);
        boolean ingreso = whenElClienteIngresa(datosLogin);
        thenElIngresoFalla(ingreso);
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(boolean ingreso) {
        assertThat(ingreso).isFalse();
    }

}
