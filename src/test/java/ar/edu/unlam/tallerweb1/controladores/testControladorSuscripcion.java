package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testControladorSuscripcion {

    private static final Long ID_USUARIO =123L;
    private static final Long ID_TIPO=1L;

    private Usuario Usuario = new Usuario(ID_USUARIO) ;
    private TipoSuscripcion TIPO_SUSCRIPCION = new TipoSuscripcion(ID_TIPO, "") ;

    private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
    private ControladorSuscripcion controladorSuscripcion = new ControladorSuscripcion(servicioSuscripcion);

    private ModelAndView mav;


    @Test
    public void queUnClienteSePuedaSuscribirExitosamente(){
        givenExisteUnClienteyUnTipoSuscripcion();
        whenUnClienteSeSuscribe(Usuario, TIPO_SUSCRIPCION);
        thenLaSuscripcionEsExitosa();
    }

    private void givenExisteUnClienteyUnTipoSuscripcion() {
    }

    private void whenUnClienteSeSuscribe(Usuario usuario, TipoSuscripcion tipoSuscripcion) {


        mav =  controladorSuscripcion.suscribirUsuario(usuario.getId(), tipoSuscripcion.getId());

    }

    private void  thenLaSuscripcionEsExitosa() {

        assertThat(mav.getViewName()).isEqualTo("home"); //Vista placeholder
    }

    ///////////////////////////////////////////

    // NECESITO EL REPOSITORIO DE Usuario Y TIPO SUSCRIPCION
/*
    @Test
    public void unClienteNoPuedeSuscribirseDosVeces(){
        givenExisteUnaSuscripcion(Usuario, TIPO_SUSCRIPCION);
        doThrow(ClienteYaSuscriptoException.class)
                .when(servicioSuscripcion)
                .suscribir(Usuario, TIPO_SUSCRIPCION);
        whenUnClienteSeSuscribe(Usuario, TIPO_SUSCRIPCION);
        thenLaSuscripcionFalla();

    }
*/
    private Suscripcion givenExisteUnaSuscripcion(Usuario usuario, TipoSuscripcion tipoSuscripcion) {

        return new Suscripcion(usuario, tipoSuscripcion);
    }

    private void thenLaSuscripcionFalla() {

        assertThat(mav.getViewName()).isEqualTo("ir-a-suscribir"); //Vista placeholder
    }

    @Test
    public void unClienteRenuevaLaSuscripcionExitosamente(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(Usuario, TIPO_SUSCRIPCION);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionEsExitosa();

    }

    private void whenUnClienteRenuevaLaSuscripcion(Suscripcion suscripcion) {
        mav = controladorSuscripcion.renovarSuscripcion(suscripcion.getUsuario().getId());
    }

    private void thenLasRenovacionEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("home");
    }
/*
    @Test
    public void unClienteNoPuedeRenovarDosVecesUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(Usuario, TIPO_SUSCRIPCION);
        doThrow(SuscripcionYaRenovadaException.class)
                .when(servicioSuscripcion)
                .renovarAutomaticamenteSuscripcion(suscripcion.getId());
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionNoEsExitosa();

    }*/

    private void thenLasRenovacionNoEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("perfil");
    }


}