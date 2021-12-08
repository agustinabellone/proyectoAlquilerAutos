package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Situacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioMecanico extends SpringTest {

    @Autowired
    private RepositorioAuto repositorioAuto;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaDeAutosParaMantenimiento() {
        givenExistenAutosParaMantenimiento(5);
        List<Auto> autosEnMantenimienti = whenBusoAutosParaMantenimiento();
        thenObtengoLaListaConLaCantidadEsperada(autosEnMantenimienti, 5);
    }

    private void givenExistenAutosParaMantenimiento(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Auto auto = new Auto();
            auto.setSituacion(Situacion.EN_MANTENIMIENTO);
            session().save(auto);
        }
    }

    private List<Auto> whenBusoAutosParaMantenimiento() {
        return repositorioAuto.buscarAutosEnMantenimiento(Situacion.EN_MANTENIMIENTO);
    }

    private void thenObtengoLaListaConLaCantidadEsperada(List<Auto> autosEnMantenimienti, int cantidad) {
        assertThat(autosEnMantenimienti).hasSize(cantidad);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanEnviarUnAutoAMantenimiento() {
        Auto paraMantenimiento = givenExisteUnAutoDisponible();
        Auto actualizado = whenGuardoUnautoEnMantenimiento(paraMantenimiento.getId(), Situacion.EN_MANTENIMIENTO, LocalDate.now());
        thenObtengoElAutoAcutalizado(actualizado);

    }

    private Auto givenExisteUnAutoDisponible() {
        Auto paraMantenimiento = new Auto();
        paraMantenimiento.setSituacion(Situacion.DISPONIBLE);
        session().save(paraMantenimiento);
        return paraMantenimiento;
    }

    private Auto whenGuardoUnautoEnMantenimiento(Long id, Situacion situacion, LocalDate fecha_inicio_mantenimiento) {
        return repositorioAuto.enviarAMantenimiento(id, situacion, fecha_inicio_mantenimiento);
    }

    private void thenObtengoElAutoAcutalizado(Auto actualizado) {
        assertThat(actualizado.getSituacion()).isEqualTo(Situacion.EN_MANTENIMIENTO);
        assertThat(actualizado.getFecha_inicio_mantenimiento()).isEqualTo(LocalDate.now());
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaEnviarUnAutoARevision() {
        Auto aEnviar = givenExisteUnAutoParaMantenimiento();
        Usuario usuario = givenExisteUnUsuarioMecanico();
        LocalDate fecha_de_envio = LocalDate.now();
        Revision revision = whenSeEnviaUnAutoARevision(aEnviar, usuario, fecha_de_envio);
        thenObtengoUnaNuevaRevisionConElAutoActualizado(revision);
    }

    private Auto givenExisteUnAutoParaMantenimiento() {
        Auto auto = new Auto();
        auto.setSituacion(Situacion.EN_MANTENIMIENTO);
        session().save(auto);
        return auto;
    }

    private Usuario givenExisteUnUsuarioMecanico() {
        Usuario usuario = new Usuario();
        usuario.setRol("mecanico");
        session().save(usuario);
        return usuario;
    }

    private Revision whenSeEnviaUnAutoARevision(Auto aEnviar, Usuario usuario, LocalDate fecha_de_envio) {
        return repositorioAuto.enviarARevision(aEnviar, usuario, fecha_de_envio);
    }

    private void thenObtengoUnaNuevaRevisionConElAutoActualizado(Revision revision) {
        assertThat(revision.getAuto().getSituacion()).isEqualTo(Situacion.EN_REVISION);
        assertThat(revision.getUsuario().getRol()).isEqualTo("mecanico");
    }
}
