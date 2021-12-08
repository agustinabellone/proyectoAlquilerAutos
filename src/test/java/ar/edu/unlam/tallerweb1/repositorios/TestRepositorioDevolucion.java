package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioDevolucion extends SpringTest {

    @Autowired
    private RepositorioDevolucion repositorioDevolucion;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaFinalizarUnAlquiler() {
        Solicitud finalizacion = givenExisteUnAlquilerParaFinalizar();
        Alquiler alquiler = whenFinaLizoElLAquilerDeCliente(finalizacion);
        thenElAquilerActualizado(alquiler);
    }

    private Alquiler whenFinaLizoElLAquilerDeCliente(Solicitud finalizacion) {
        repositorioDevolucion.finalizarAlquilerCliente(finalizacion.getAlquiler(), finalizacion);
        return repositorioDevolucion.obtenerAlquilerPorId(finalizacion.getAlquiler().getId());
    }

    private void thenElAquilerActualizado(Alquiler alquiler) {
        assertThat(alquiler.getAuto().getSituacion()).isEqualTo(Situacion.DISPONIBLE);
    }


    private Solicitud givenExisteUnAlquilerParaFinalizar() {
        Usuario usuario = new Usuario();
        usuario.setRol("encargado");
        session().save(usuario);

        Usuario cliente = new Usuario();
        cliente.setRol("cliente");
        session().save(cliente);

        Auto auto = new Auto();
        auto.setSituacion(Situacion.OCUPADO);
        session().save(auto);

        Garage garage = new Garage();
        garage.setEncargado(usuario);
        session().save(garage);

        Alquiler alquiler = new Alquiler();
        alquiler.setEncargado(usuario);
        alquiler.setUsuario(cliente);
        alquiler.setAuto(auto);
        alquiler.setGarageLlegada(garage);
        alquiler.setEstado(Estado.PENDIENTE);
        session().save(alquiler);

        Solicitud solicitud = new Solicitud();
        solicitud.setAlquiler(alquiler);
        solicitud.setEstadoSolicitud(EstadoSolicitud.PENDIENTE);
        session().save(solicitud);
        return solicitud;
    }
}
