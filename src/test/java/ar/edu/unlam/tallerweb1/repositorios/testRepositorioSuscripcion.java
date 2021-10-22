package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class testRepositorioSuscripcion extends SpringTest {

    private static final Long ID_USUARIO =123L;
    private static final Long ID_TIPO=1L;
    private static final Usuario USUARIO =new Usuario(ID_USUARIO);
    private static final Usuario USUARIO_2 =new Usuario(ID_USUARIO +1);
    private static final Usuario USUARIO_3 =new Usuario(ID_USUARIO +2);
    private static final TipoSuscripcion TIPO_SUSCRIPCION=new TipoSuscripcion(ID_TIPO);
    private static final TipoSuscripcion TIPO_SUSCRIPCION2=new TipoSuscripcion(ID_TIPO+1);

    @Autowired
    private RepositorioSuscripcion repositorioSuscripcion;

    @Autowired
    private RepositorioTipoSuscripcion repositorioTipoSuscripcion;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void guardarUnaSuscripcionDeberiaPersistirla(){
        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        Long id=whenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcion(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarSuscripcionPorTipo(){
        givenExisteUnClientePersistido(USUARIO);
        givenExisteUnClientePersistido(USUARIO_2);
        givenExisteUnClientePersistido(USUARIO_3);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION2);

        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        Suscripcion suscripcion2 = givenExisteSuscripcion(USUARIO_2, TIPO_SUSCRIPCION);
        Suscripcion suscripcion3= givenExisteSuscripcion(USUARIO_3, TIPO_SUSCRIPCION2);

        givenGuardoLaSuscripcion(suscripcion);
        givenGuardoLaSuscripcion(suscripcion2);
        givenGuardoLaSuscripcion(suscripcion3);

        Integer cantidad_esperada=2;
        thenEncuentroLaSuscripcionPorTipo(TIPO_SUSCRIPCION, cantidad_esperada);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarPorCliente(){
        givenExisteUnClientePersistido(USUARIO);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);

        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        givenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcionPorCliente(USUARIO);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoActualizarUnaSuscripcion(){
        givenExisteUnClientePersistido(USUARIO);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION2);
        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        givenGuardoLaSuscripcion(suscripcion);
        TipoSuscripcion nuevo_TipoSuscripcion=TIPO_SUSCRIPCION2;
        whenActualizoLaSuscripcion(suscripcion, nuevo_TipoSuscripcion);
        thenEncuentroLaSuscripcionPorTipo(nuevo_TipoSuscripcion, 1);
    }

    private void givenExisteUnTipoSuscripcionPersistido(TipoSuscripcion tipoSuscripcion) {
        repositorioTipoSuscripcion.guardar(tipoSuscripcion);
    }

    private void givenExisteUnClientePersistido(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
    }


    private void whenActualizoLaSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_TipoSuscripcion) {
        suscripcion.setTipoSuscripcion(nuevo_TipoSuscripcion);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }

    private Suscripcion givenExisteSuscripcion(Usuario usuario, TipoSuscripcion tipoSuscripcion) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setUsuario(usuario);
        suscripcion.setTipoSuscripcion(tipoSuscripcion);
        return suscripcion;
    }

    private void givenGuardoLaSuscripcion(Suscripcion suscripcion) {
        repositorioSuscripcion.guardar(suscripcion);
    }

    private Long whenGuardoLaSuscripcion(Suscripcion suscripcion) {
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion.getId();
    }

    private void thenEncuentroLaSuscripcionPorTipo(TipoSuscripcion tipoSuscripcion, Integer cantidad_esperada) {
        List<Suscripcion> suscripcionesBuscadas = repositorioSuscripcion.buscarPorTipo(tipoSuscripcion);
        assertThat(suscripcionesBuscadas).hasSize(cantidad_esperada);
    }

    private void thenEncuentroLaSuscripcion(Long id) {
        Suscripcion buscada = repositorioSuscripcion.buscarPorId(id);
        assertThat(buscada).isNotNull();
    }

    private void thenEncuentroLaSuscripcionPorCliente(Usuario usuario) {
        Suscripcion buscada = repositorioSuscripcion.buscarPorUsuario(usuario);
        assertThat(buscada).isNotNull();

    }

    public static class testRepositorioEnviarAutoAMantenimiento extends SpringTest {

        @Autowired
        private RepositorioEnviarAutoAMantenimiento repositorioEnviarAutoAMantenimiento;

        @Test
        public void queSeGuardeUnAutoEnMantenimientoCorrectamente() {

        }
    }
}
