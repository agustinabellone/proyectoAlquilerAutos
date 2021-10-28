package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class testServicioEnviarAutoAMantenimiento {

    private RepositorioAuto repositorioAuto = mock(RepositorioAuto.class);
    private ServicioDeAuto servicioDeAuto = new ServicioDeAutoImpl(repositorioAuto);
    private Auto auto = new Auto();

    @Test
    public void queSePuedaEnviarUnAutoAMantenimiento() throws AutoNoExistente, AutoYaExistente {
        Auto aEnviar = givenExisteUnAuto();
        Auto enviado = whenSeEnviaAMantenimiento(aEnviar);
        thenElEnvioEsExitoso(enviado);
    }

    private Auto givenExisteUnAuto() {
        when(repositorioAuto.buscarPor(auto.getId())).thenReturn(auto);
        return auto;
    }

    private Auto whenSeEnviaAMantenimiento(Auto aEnviar) throws AutoNoExistente, AutoYaExistente {
        when(repositorioAuto.guardarEnMantenimiento(anyObject())).thenReturn(new Auto());
        return servicioDeAuto.enviarAutoMantenimiento(aEnviar);
    }

    private void thenElEnvioEsExitoso(Auto enviado) {
        assertThat(enviado).isNotNull();
        assertThat(enviado.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        verify(repositorioAuto,times(1)).buscarPor(anyLong());
        verify(repositorioAuto,times(1)).guardarEnMantenimiento(anyObject());
    }

    @Test
    public void queSePuedaObtenerUnAutoPorSuId() throws AutoNoExistente {
        Auto aBuscar = givenExisteUnAuto();
        Auto encontrado = whenLoBusco(aBuscar);
        thenLoEncuentro(encontrado);
    }

    private Auto whenLoBusco(Auto aBuscar) throws AutoNoExistente {
        return servicioDeAuto.buscarAutoPorId(aBuscar.getId());
    }

    private void thenLoEncuentro(Auto encontrado) {
        assertThat(encontrado).isNotNull();
        verify(repositorioAuto,times(1)).buscarPor(anyLong());
    }
}
