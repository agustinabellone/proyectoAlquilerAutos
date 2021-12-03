package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAdministradorSeccionClientes extends SpringTest {

    @Autowired
    private RepositorioSuscripcion repositorioSuscripcion;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaDeSuscripciones() {
        givenExistenSuscripciones(5);
        List<Suscripcion> suscripcions = whenLasObtengoComoUnaLista();
        thenObtengoLalista(suscripcions, 5);
    }

    private void givenExistenSuscripciones(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Suscripcion suscripcion = new Suscripcion();
            session().save(suscripcion);
        }
    }

    private List<Suscripcion> whenLasObtengoComoUnaLista() {
        return repositorioSuscripcion.buscarSuscripciones();
    }

    private void thenObtengoLalista(List<Suscripcion> suscripcions, int cantidad_esperada) {
        assertThat(suscripcions).hasSize(cantidad_esperada);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnaListaConClientes() {
        givenExistenClientes(5);
        List<Usuario> usuarios = whenObtengoLosClientesEnUnaLista();
        thenObtengoLaListaDeClientes(usuarios, 5);
    }

    private void givenExistenClientes(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Usuario usuario = new Usuario();
            usuario.setRol("cliente");
            session().save(usuario);
        }
    }

    private List<Usuario> whenObtengoLosClientesEnUnaLista() {
        return repositorioUsuario.buscarUsuariosPorRol("cliente");
    }

    private void thenObtengoLaListaDeClientes(List<Usuario> usuarios, int cantidad_esperada) {
        assertThat(usuarios).hasSize(cantidad_esperada);
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnClienteSinSuscripcion() {
        Usuario usuario = givenExisteUnClienteNoSuscripto();
        Suscripcion sinSuscripcion = whenObtengoUnClienteNoSuscripto(usuario);
        thenObtengoUnaSuscripcionNulaPorqueNoEstaSuscripto(sinSuscripcion, usuario);
    }

    private Usuario givenExisteUnClienteNoSuscripto() {
        Usuario usuario = new Usuario();
        session().save(usuario);
        return usuario;
    }

    private Suscripcion whenObtengoUnClienteNoSuscripto(Usuario usuario) {
        return repositorioSuscripcion.buscarPorUsuario(usuario);
    }

    private void thenObtengoUnaSuscripcionNulaPorqueNoEstaSuscripto(Suscripcion sinSuscripcion, Usuario usuario) {
        assertThat(sinSuscripcion).isNull();
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnClienteConSuscripcion() {
        Usuario usuario = givenExisteUnClienteSuscripto();
        Suscripcion suscripcion = whenObtengoUnClienteSuscripto(usuario);
        thenObtengoUnaSuscripcion(suscripcion, usuario);
    }

    private Usuario givenExisteUnClienteSuscripto() {
        Usuario usuario = new Usuario();
        Suscripcion suscripcion = new Suscripcion();
        usuario.setRol("cliente");
        suscripcion.setUsuario(usuario);
        suscripcion.setFechaInicio(LocalDate.now());
        suscripcion.setFechaFin(LocalDate.now().plusDays(3));
        session().save(usuario);
        session().save(suscripcion);
        return usuario;
    }

    private Suscripcion whenObtengoUnClienteSuscripto(Usuario usuario) {
        return repositorioSuscripcion.buscarPorUsuario(usuario);
    }

    private void thenObtengoUnaSuscripcion(Suscripcion suscripcion, Usuario usuario) {
        assertThat(suscripcion).isNotNull();
        assertThat(suscripcion.getUsuario()).isEqualTo(usuario);
    }

}
