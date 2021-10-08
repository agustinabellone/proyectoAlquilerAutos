package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcionImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcionImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.orm.hibernate3.SpringTransactionFactory;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testControladorSuscripcion {

    private static final Long ID_CLIENTE=123L;
    private static final Long ID_TIPO=1L;

    private Cliente cliente = new Cliente(ID_CLIENTE) ;
    private TipoSuscripcion tipoSuscripcion = new TipoSuscripcion(ID_TIPO, "") ;

    private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
    private ControladorSuscripcion controladorSuscripcion= new ControladorSuscripcion(servicioSuscripcion);

    private ModelAndView mav;


    @Test
    public void queUnClienteSePuedaSuscribirExitosamente(){
        givenExisteUnClienteyUnTipoSuscripcion();
        whenUnClienteSeSuscribe(ID_CLIENTE, ID_TIPO);
        thenLaSuscripcionEsExitosa();
    }

    private void givenExisteUnClienteyUnTipoSuscripcion() {
    }

    private void whenUnClienteSeSuscribe(Long id_cliente, Long id_tipo) {


        mav =  controladorSuscripcion.suscribirCliente(id_cliente, id_tipo);

    }

    private void  thenLaSuscripcionEsExitosa() {

        assertThat(mav.getViewName()).isEqualTo("home"); //Vista placeholder
    }

    ///////////////////////////////////////////

    @Test
    public void unClienteNoPuedeSuscribirseDosVeces(){
        givenExisteUnaSuscripcion(ID_CLIENTE, ID_TIPO);
        doThrow(ClienteYaSuscriptoException.class)
                .when(servicioSuscripcion)
                .suscribir(ID_CLIENTE, ID_TIPO);
        whenUnClienteSeSuscribe(ID_CLIENTE, ID_TIPO);
        thenLaSuscripcionFalla();

    }

    private Suscripcion givenExisteUnaSuscripcion(Long id_cliente, Long id_tipo) {

        return new Suscripcion(id_cliente, id_tipo);
    }

    private void thenLaSuscripcionFalla() {

        assertThat(mav.getViewName()).isEqualTo("ir-a-suscribir"); //Vista placeholder
    }

    @Test
    public void unClienteRenuevaLaSuscripcionExitosamente(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(ID_CLIENTE, ID_TIPO);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionEsExitosa();

    }

    private void whenUnClienteRenuevaLaSuscripcion(Suscripcion suscripcion) {
        mav = controladorSuscripcion.renovarSuscripcion(suscripcion);
    }

    private void thenLasRenovacionEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("home");
    }

    @Test
    public void unClienteNoPuedeRenovarDosVecesUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteUnaSuscripcion(ID_CLIENTE, ID_TIPO);
        doThrow(SuscripcionYaRenovadaException.class)
                .when(servicioSuscripcion)
                .renovarSuscripcion(suscripcion);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        thenLasRenovacionNoEsExitosa();

    }

    private void thenLasRenovacionNoEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("perfil");
    }


}