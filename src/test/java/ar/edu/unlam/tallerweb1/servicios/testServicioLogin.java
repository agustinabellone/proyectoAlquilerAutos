package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.*;

public class testServicioLogin {

    private RepositorioCliente repositorioCliente = mock(RepositorioCliente.class);
    private ServicioLogin servicioLogin = new ServicioLoginImpl(repositorioCliente);

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";


    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        boolean ingreso = whenElClienteIngresa(EMAIL, CLAVE);
        thenElIngresoEsExitoso(ingreso);
    }

    private void givenUsuarioExiste() {
        when(repositorioCliente.buscarPorEmail(EMAIL)).thenReturn(new Cliente());
    }

    private boolean whenElClienteIngresa(String email, String clave) {
        return servicioLogin.ingresar(new DatosLogin(email, clave));
    }

    private void thenElIngresoEsExitoso(boolean ingreso) {
        assertThat(ingreso).isTrue();
    }

    @Test(expected = ClienteNoExisteException.class)
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        doThrow(ClienteNoExisteException.class).when(repositorioCliente).buscarPorEmail(EMAIL_INCORRECTO);
        boolean ingreso = whenElClienteIngresa(EMAIL_INCORRECTO, CLAVE);
        thenElIngresoFalla(ingreso);
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(boolean ingreso) {
        assertThat(ingreso).isFalse();
    }

}
