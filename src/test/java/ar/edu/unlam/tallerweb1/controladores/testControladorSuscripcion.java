package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testControladorSuscripcion {

    private static final Long ID_CLIENTE=123L;
    private static final Long ID_TIPO=1L;

    private Cliente CLIENTE = new Cliente(ID_CLIENTE) ;
    private TipoSuscripcion TIPO_SUSCRIPCION = new TipoSuscripcion(ID_TIPO, "") ;

    private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
    private ControladorSuscripcion controladorSuscripcion = new ControladorSuscripcion(servicioSuscripcion);

    private ModelAndView mav;


    @Test
    public void queUnClienteSePuedaSuscribirExitosamente(){
        givenExisteUnClienteyUnTipoSuscripcion();
        whenUnClienteSeSuscribe(CLIENTE, TIPO_SUSCRIPCION);
        thenLaSuscripcionEsExitosa();
    }

    private void givenExisteUnClienteyUnTipoSuscripcion() {
    }

    private void whenUnClienteSeSuscribe(Cliente cliente, TipoSuscripcion tipoSuscripcion) {


        mav =  controladorSuscripcion.suscribirCliente(cliente.getId(), tipoSuscripcion.getId());

    }

    private void  thenLaSuscripcionEsExitosa() {

        assertThat(mav.getViewName()).isEqualTo("home"); //Vista placeholder
    }

    ///////////////////////////////////////////

    // NECESITO EL REPOSITORIO DE CLIENTE Y TIPO SUSCRIPCION
/*
    @Test
    public void unClienteNoPuedeSuscribirseDosVeces(){
        givenExisteUnaSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        doThrow(ClienteYaSuscriptoException.class)
                .when(servicioSuscripcion)
                .suscribir(CLIENTE, TIPO_SUSCRIPCION);
        whenUnClienteSeSuscribe(CLIENTE, TIPO_SUSCRIPCION);
        thenLaSuscripcionFalla();

    }
*/
    private Suscripcion givenExisteUnaSuscripcion(Cliente cliente, TipoSuscripcion tipoSuscripcion) {

        return new Suscripcion(cliente, tipoSuscripcion);
    }

    private void thenLaSuscripcionFalla() {

        assertThat(mav.getViewName()).isEqualTo("ir-a-suscribir"); //Vista placeholder
    }

    @Test
    public void unClienteRenuevaLaSuscripcionExitosamente(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionEsExitosa();

    }

    private void whenUnClienteRenuevaLaSuscripcion(Suscripcion suscripcion) {
        mav = controladorSuscripcion.renovarSuscripcion(suscripcion.getCliente().getId());
    }

    private void thenLasRenovacionEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("home");
    }
/*
    @Test
    public void unClienteNoPuedeRenovarDosVecesUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        doThrow(SuscripcionYaRenovadaException.class)
                .when(servicioSuscripcion)
                .renovarSuscripcion(suscripcion);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionNoEsExitosa();

    }*/

    private void thenLasRenovacionNoEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("perfil");
    }


}