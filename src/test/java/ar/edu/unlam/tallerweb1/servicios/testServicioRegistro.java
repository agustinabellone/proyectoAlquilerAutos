package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;

public class testServicioRegistro {

    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioRegistro servicioRegistro = new ServicioRegistroImpl(repositorioUsuario);

    public static final String EMAIL = "agus@gmail.com";
    public static final String CLAVE = "12345678";
    public static final String CLAVE_DISTINTA = "87521458";
    public static final String CLAVE_LONGITUD_INCORRECTA = "8752";


    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){
        givenNoExisteUsuario();
        Usuario clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroEsExitoso(clienteRegistrado);
    }

    private void givenNoExisteUsuario() {}

    private Usuario whenRegistroUnUsuario(String email, String clave, String repiteClave) {
        return servicioRegistro.registrar(new DatosRegistro(email, clave, repiteClave));
    }

    private void thenElRegistroEsExitoso(Usuario clienteRegistrado) {
        assertThat(clienteRegistrado).isNotNull();
    }


    /////////////////////////////////////////////

    @Test(expected = ClavesDistintasException.class)
    public void siLasClavesSonDistintasElRegistroFalla(){
        givenNoExisteUsuario();
        Usuario clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE_DISTINTA);
        thenElRegistroFalla(clienteRegistrado);
    }

    private void thenElRegistroFalla(Usuario clienteRegistrado) {
        assertThat(clienteRegistrado).isNull();
    }

    ////////////////////////////////////////////////

    @Test(expected = ClaveLongitudIncorrectaException.class)
    public void siLaClaveTieneMenosDeOchoCaracteresElRegistroFalla(){
        givenNoExisteUsuario();
        Usuario clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        thenElRegistroFalla(clienteRegistrado);
    }

    ///////////////////////////////////////////

    @Test(expected = ClienteYaExisteException.class)
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario();
        doThrow(ClienteYaExisteException .class).when(repositorioUsuario).buscarPorEmail(EMAIL);
        Usuario clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroFalla(clienteRegistrado);
    }

    private void givenExisteUsuario() {}

    ////////////////////////////////////////////

    @Test
    public void siRegistroUsuarioTieneMismoEmailQueDatosRegistro(){
        givenNoExisteUsuario();
        Usuario clienteRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        whenElUsuarioTieneEmailDeRegistro(clienteRegistrado);
    }

    private void whenElUsuarioTieneEmailDeRegistro(Usuario clienteRegistrado) {
        assertThat(clienteRegistrado.getEmail()).isEqualTo(EMAIL);
    }

}