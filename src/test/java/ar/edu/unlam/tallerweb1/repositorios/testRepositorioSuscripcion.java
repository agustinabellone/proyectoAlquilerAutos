package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class testRepositorioSuscripcion extends SpringTest {

    private static final Long ID_CLIENTE=123L;
    private static final Long ID_TIPO=1L;
    private static final Cliente CLIENTE=new Cliente(ID_CLIENTE);
    private static final Cliente CLIENTE2=new Cliente(ID_CLIENTE+1);
    private static final Cliente CLIENTE3=new Cliente(ID_CLIENTE+2);
    private static final TipoSuscripcion TIPO_SUSCRIPCION=new TipoSuscripcion(ID_TIPO);
    private static final TipoSuscripcion TIPO_SUSCRIPCION2=new TipoSuscripcion(ID_TIPO+1);

    @Autowired
    private RepositorioSuscripcion repositorioSuscripcion;

    @Autowired
    private RepositorioTipoSuscripcion repositorioTipoSuscripcion;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Test
    @Transactional
    @Rollback
    public void guardarUnaSuscripcionDeberiaPersistirla(){
        Suscripcion suscripcion = givenExisteSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        Long id=whenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcion(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarSuscripcionPorTipo(){
        givenExisteUnClientePersistido(CLIENTE);
        givenExisteUnClientePersistido(CLIENTE2);
        givenExisteUnClientePersistido(CLIENTE3);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION2);

        Suscripcion suscripcion = givenExisteSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        Suscripcion suscripcion2 = givenExisteSuscripcion(CLIENTE2, TIPO_SUSCRIPCION);
        Suscripcion suscripcion3= givenExisteSuscripcion(CLIENTE3, TIPO_SUSCRIPCION2);

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
        givenExisteUnClientePersistido(CLIENTE);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);

        Suscripcion suscripcion = givenExisteSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        givenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcionPorCliente(CLIENTE);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoActualizarUnaSuscripcion(){
        givenExisteUnClientePersistido(CLIENTE);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION);
        givenExisteUnTipoSuscripcionPersistido(TIPO_SUSCRIPCION2);
        Suscripcion suscripcion = givenExisteSuscripcion(CLIENTE, TIPO_SUSCRIPCION);
        givenGuardoLaSuscripcion(suscripcion);
        TipoSuscripcion nuevo_TipoSuscripcion=TIPO_SUSCRIPCION2;
        whenActualizoLaSuscripcion(suscripcion, nuevo_TipoSuscripcion);
        thenEncuentroLaSuscripcionPorTipo(nuevo_TipoSuscripcion, 1);
    }

    private void givenExisteUnTipoSuscripcionPersistido(TipoSuscripcion tipoSuscripcion) {
        repositorioTipoSuscripcion.guardar(tipoSuscripcion);
    }

    private void givenExisteUnClientePersistido(Cliente cliente) {
        repositorioCliente.guardar(cliente);
    }


    private void whenActualizoLaSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_TipoSuscripcion) {
        suscripcion.setTipoSuscripcion(nuevo_TipoSuscripcion);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }

    private Suscripcion givenExisteSuscripcion(Cliente cliente, TipoSuscripcion tipoSuscripcion) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setCliente(cliente);
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

    private void thenEncuentroLaSuscripcionPorCliente(Cliente cliente) {
        Suscripcion buscada = repositorioSuscripcion.buscarPorCliente(cliente);
        assertThat(buscada).isNotNull();

    }
}
