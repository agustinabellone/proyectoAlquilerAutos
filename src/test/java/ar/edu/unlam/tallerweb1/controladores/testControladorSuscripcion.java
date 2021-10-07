package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcionImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcionImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
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

    private DatosSuscripcion datosSuscripcion = new DatosSuscripcion(cliente, tipoSuscripcion );

    private ModelAndView mav;


    @Test
    public void queUnClienteSePuedaSuscribirExitosamente(){
        givenExisteUnClienteyUnTipoSuscripcion();
        whenUnClienteSeSuscribe(datosSuscripcion);
        thenLaSuscripcionEsExitosa();
    }

    private void givenExisteUnClienteyUnTipoSuscripcion() {
    }

    private void whenUnClienteSeSuscribe(DatosSuscripcion datosSuscripcion) {

        mav =  controladorSuscripcion.suscribirCliente(datosSuscripcion);

    }

    private void  thenLaSuscripcionEsExitosa() {

        assertThat(mav.getViewName()).isEqualTo("home"); //Vista placeholder
    }

    ///////////////////////////////////////////

    @Test
    public void unClienteNoPuedeSuscribirseDosVeces(){
        givenExisteUnaSuscripcion(datosSuscripcion);
        doThrow(ClienteYaSuscriptoException.class)
                .when(servicioSuscripcion)
                .suscribir(datosSuscripcion);
        whenUnClienteSeSuscribe(datosSuscripcion);
        thenLaSuscripcionFalla();

    }

    private void givenExisteUnaSuscripcion(DatosSuscripcion datosSuscripcion) {

        controladorSuscripcion.suscribirCliente(datosSuscripcion);
    }

    private void thenLaSuscripcionFalla() {

        assertThat(mav.getViewName()).isEqualTo("ir-a-suscribir"); //Vista placeholder
    }



}