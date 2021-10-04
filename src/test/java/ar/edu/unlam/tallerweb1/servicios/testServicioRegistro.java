package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorio.TablaCliente;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class testServicioRegistro {

    private ServicioRegistro servicioRegistro = new ServicioRegistro();

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String CLAVE_DISTINTA = "87521458";
    public static final String CLAVE_LONGITUD_INCORRECTA = "8752";

    @Before
    public void init(){
        TablaCliente.getInstance().reset(); //DESPUES SE VA A HACER DE OTRA FORMA
    }

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){
        givenNoExisteUsuario();
        Cliente clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroEsExitoso(clienteRegistrado);
    }

    private void givenNoExisteUsuario() {}

    private Cliente whenRegistroUnUsuario(String email, String clave, String repiteClave) {
        return servicioRegistro.registrar(new DatosRegistro(email, clave, repiteClave));
    }

    private void thenElRegistroEsExitoso(Cliente clienteRegistrado) {
        assertThat(clienteRegistrado).isNotNull();
    }


    /////////////////////////////////////////////

    @Test(expected = ClavesDistintasException.class)
    public void siLasClavesSonDistintasElRegistroFalla(){
        givenNoExisteUsuario();
        Cliente clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE_DISTINTA);
        thenElRegistroFalla(clienteRegistrado);
    }

    private void thenElRegistroFalla(Cliente clienteRegistrado) {
        assertThat(clienteRegistrado).isNull();
    }

    ////////////////////////////////////////////////

    @Test(expected = ClaveLongitudIncorrectaException.class)
    public void siLaClaveTieneMenosDeOchoCaracteresElRegistroFalla(){
        givenNoExisteUsuario();
        Cliente clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        thenElRegistroFalla(clienteRegistrado);
    }

    ///////////////////////////////////////////

    @Test(expected = ClienteYaExisteException.class)
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario(EMAIL, CLAVE);
        Cliente clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroFalla(clienteRegistrado);
    }

    private void givenExisteUsuario(String email, String clave) {
        servicioRegistro.registrar(new DatosRegistro(email, clave, clave));
    }

    ////////////////////////////////////////////

    @Test
    public void siRegistroUsuarioTieneMismoEmailQueDatosRegistro(){
        givenNoExisteUsuario();
        Cliente clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        whenElUsuarioTieneEmailDeRegistro(clienteRegistrado);
    }

    private void whenElUsuarioTieneEmailDeRegistro(Cliente clienteRegistrado) {
        assertThat(clienteRegistrado.getEmail()).isEqualTo(EMAIL);
    }

}