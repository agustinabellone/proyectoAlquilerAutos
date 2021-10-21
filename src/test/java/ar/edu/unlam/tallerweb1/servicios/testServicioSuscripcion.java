package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class testServicioSuscripcion {

    private RepositorioSuscripcion repositorioSuscripcion = mock(RepositorioSuscripcion.class);
    private ServicioSuscripcion servicioSuscripcion= new ServicioSuscripcionImpl(repositorioSuscripcion);
    private static final Long ID_USUARIO =123L;
    private static final Long ID_TIPO=1L;
    private static final Usuario USUARIO =new Usuario(ID_USUARIO);
    private static final TipoSuscripcion TIPO_SUSCRIPCION=new TipoSuscripcion(ID_TIPO);

    @Test
    public void unClienteSeSuscribeExitosamente(){

        Usuario usuario = givenExisteUnCliente();
        TipoSuscripcion tipo = givenExisteUnTipoDeSuscripcion();
        Suscripcion suscripcion = whenUnClienteSeSuscribe(USUARIO, TIPO_SUSCRIPCION);
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
        TipoSuscripcion nuevo_TipoSuscripcion=new TipoSuscripcion(3L);
        whenUnClienteMejoraSuSuscripcion(suscripcion, nuevo_TipoSuscripcion);
        theLaMejoraEsExitosa(suscripcion, nuevo_TipoSuscripcion);

    }

    @Test(expected = ClienteYaSuscriptoException.class)
    public void siElClienteYaEstaSuscriptoFallaLaSuscripcion(){
        Suscripcion suscripcion = giveExisteUnaSuscripcion();
        Usuario usuario = suscripcion.getUsuario();
        TipoSuscripcion tipoSuscripcion = suscripcion.getTipoSuscripcion();
        when(repositorioSuscripcion.buscarPorCliente(usuario)).thenReturn(new Suscripcion());
        Suscripcion nueva_suscripcion = whenUnClienteSeSuscribe(usuario, tipoSuscripcion);
        thenLaSuscripcionFalla(nueva_suscripcion);
    }

    private void thenLaSuscripcionFalla(Suscripcion nueva_suscripcion) {
        assertThat(nueva_suscripcion).isNull();
    }

    private void whenUnClienteMejoraSuSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_TipoSuscripcion) {
        servicioSuscripcion.mejorarNivelSuscripcion(suscripcion, nuevo_TipoSuscripcion);
    }

    private void theLaMejoraEsExitosa(Suscripcion suscripcion, TipoSuscripcion nuevo_TipoSuscripcion) {
        assertThat(suscripcion.getTipoSuscripcion()).isEqualTo(nuevo_TipoSuscripcion);
    }

    private Suscripcion giveExisteUnaSuscripcion() {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setTipoSuscripcion(TIPO_SUSCRIPCION);
        suscripcion.setUsuario(USUARIO);
        return suscripcion;
    }

    private void whenUnClienteRenuevaLaSuscripcion(Suscripcion suscripcion) {
        servicioSuscripcion.renovarAutomaticamenteSuscripcion(suscripcion.getId());
    }

    private void theLaRenovacionEsExitosa(Suscripcion suscripcion) {
        assertThat(suscripcion.getRenovacion()).isTrue();
    }

    private Usuario givenExisteUnCliente() {
        return new Usuario();
    }

    private TipoSuscripcion givenExisteUnTipoDeSuscripcion() {
        return new TipoSuscripcion();
    }

    private Suscripcion whenUnClienteSeSuscribe(Usuario usuario, TipoSuscripcion tipoSuscripcion) {
        return servicioSuscripcion.suscribir(usuario, tipoSuscripcion);
    }

    private void thenLaSuscripcionEsExitosa(Suscripcion suscripcion) {
        assertThat(suscripcion).isNotNull();
        verify(repositorioSuscripcion).guardar(suscripcion);
    }
}
