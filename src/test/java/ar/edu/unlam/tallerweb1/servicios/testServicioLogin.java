package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorio.RepositorioCliente;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class testServicioLogin {

    private RepositorioCliente repositorioCliente = mock(RepositorioCliente.class);
    private ServicioLogin servicioLogin = new ServicioLoginImpl(repositorioCliente);

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String EMAIL_INCORRECTO = "agustina@gmail.com";


    @Test
    public void siElClienteExisteElIngresoEsExitoso(){
        givenUsuarioExiste();
        Cliente cliente = whenElClienteIngresa(EMAIL, CLAVE);
        thenElIngresoEsExitoso(cliente);
    }

    private void givenUsuarioExiste() {}

    private Cliente whenElClienteIngresa(String email, String clave) {
        return servicioLogin.ingresar(new DatosLogin(email, clave));
    }

    private void thenElIngresoEsExitoso(Cliente cliente) {
        assertThat(cliente).isNotNull();
    }

    @Test(expected = ClienteNoExisteException.class)
    public void siElClienteNoExisteElIngresoFalla(){
        givenUsuarioNoExiste();
        doThrow(ClienteNoExisteException.class).when(repositorioCliente).buscarPorEmail(EMAIL_INCORRECTO);
        Cliente cliente = whenElClienteIngresa(EMAIL_INCORRECTO, CLAVE);
        thenElIngresoFalla(cliente);
    }

    private void givenUsuarioNoExiste() {}

    private void thenElIngresoFalla(Cliente cliente) {
        assertThat(cliente).isNull();
    }

}
