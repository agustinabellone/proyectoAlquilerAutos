package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.controladores.DatosSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testServicioSuscripcion {

    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private ServicioSuscripcion servicioSuscripcion= new ServicioSuscripcionImpl(repositorioSuscripcion);

    @Test
    public void unClienteSeSuscribeExitosamente(){

        Cliente cliente = givenExisteUnCliente();
        TipoSuscripcion tipo = givenExisteUnTipoDeSuscripcion();
        DatosSuscripcion datosSuscripcion = new DatosSuscripcion(cliente, tipo);
        Suscripcion suscripcion = whenUnClienteSeSuscribe(datosSuscripcion);
        thenLaSuscripcionEsExitosa(suscripcion);

    }

    @Test
    public void unClienteRenuevaLaSuscripcion(){

        Suscripcion suscripcion = giveExisteUnaSuscripcion();
        suscripcion.setRenovacion(false);
        whenUnClienteRenuevaLaSuscripcion(suscripcion);
        theLaRenovacionEsExitosa(suscripcion);

    }

    @Test
    public void unClienteMejoraSuSucripcion(){
        Suscripcion suscripcion = giveExisteUnaSuscripcion();
        Long nuevo_tipo=3L;
        whenUnClienteMejoraSuSuscripcion(suscripcion, nuevo_tipo);
        theLaMejoraEsExitosa(suscripcion, nuevo_tipo);

    }

    @Test(expected = ClienteYaSuscriptoException.class)
    public void siElClienteYaEstaSuscriptoFallaLaSuscripcion(){
        Suscripcion suscripcion = giveExisteUnaSuscripcion();
        Long cliente_id = suscripcion.getCliente_id();
        Cliente cliente = new Cliente(cliente_id);
        when(repositorioSuscripcion.buscarPorCliente(cliente_id)).thenReturn(new Suscripcion());
        Suscripcion nueva_suscripcion = whenUnClienteSeSuscribe(new DatosSuscripcion(cliente, new TipoSuscripcion()));
        thenLaSuscripcionFalla(nueva_suscripcion);
    }

    private void thenLaSuscripcionFalla(Suscripcion nueva_suscripcion) {
        assertThat(nueva_suscripcion).isNull();
    }

    private void whenUnClienteMejoraSuSuscripcion(Suscripcion suscripcion, Long nuevo_tipo) {
        servicioSuscripcion.mejorarNivelSuscripcion(suscripcion, nuevo_tipo);
    }

    private void theLaMejoraEsExitosa(Suscripcion suscripcion, Long nuevo_tipo) {
        assertThat(suscripcion.getTipo_id()).isEqualTo(nuevo_tipo);
    }

    private Suscripcion giveExisteUnaSuscripcion() {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setTipo_id(1L);
        suscripcion.setCliente_id(5L);
        return suscripcion;
    }

    private void whenUnClienteRenuevaLaSuscripcion(Suscripcion suscripcion) {
        servicioSuscripcion.renovarSuscripcion(suscripcion);
    }

    private void theLaRenovacionEsExitosa(Suscripcion suscripcion) {
        assertThat(suscripcion.getRenovacion()).isTrue();
    }

    private Cliente givenExisteUnCliente() {
        return new Cliente();
    }

    private TipoSuscripcion givenExisteUnTipoDeSuscripcion() {
        return new TipoSuscripcion();
    }

    private Suscripcion whenUnClienteSeSuscribe(DatosSuscripcion datosSuscripcion) {
        return servicioSuscripcion.suscribir(datosSuscripcion);
    }

    private void thenLaSuscripcionEsExitosa(Suscripcion suscripcion) {
        assertThat(suscripcion).isNotNull();
        verify(repositorioSuscripcion).guardar(suscripcion);
    }
}
