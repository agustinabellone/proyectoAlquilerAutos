package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorio.TablaAlquiler;
import ar.edu.unlam.tallerweb1.repositorio.TablaSuscripcion;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

public class testControladorSuscripcion {

    private static final Long ID_CLIENTE=(long)123;

    private Cliente cliente = new Cliente(ID_CLIENTE) ;
    private TipoSuscripcion tipoSuscripcion = new TipoSuscripcion() ;

    private ControladorSuscripcion controladorSuscripcion= new ControladorSuscripcion();

    private DatosSuscripcion datosSuscripcion = new DatosSuscripcion(cliente, tipoSuscripcion );

    private ModelAndView mav;

    @Before
    public void init() {
        TablaSuscripcion.getInstance().reset();

    }

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