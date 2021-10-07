package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class testRepositorioSuscripcion extends SpringTest {

    private static final Long ID_CLIENTE=123L;
    private static final Long ID_TIPO=1L;

    @Autowired
    private RepositorioSuscripcion repositorioSuscripcion;

    @Test
    @Transactional
    @Rollback
    public void guardarUnaSuscripcionDeberiaPersistirla(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        Long id=whenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcion(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarSuscripcionPorTipo(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        Suscripcion suscripcion2 = givenExisteSuscripcion(ID_CLIENTE+1, ID_TIPO);
        Suscripcion suscripcion3 = givenExisteSuscripcion(ID_CLIENTE+2, ID_TIPO+1);

        givenGuardoLaSuscripcion(suscripcion);
        givenGuardoLaSuscripcion(suscripcion2);
        givenGuardoLaSuscripcion(suscripcion3);

        Integer cantidad_esperada=2;
        thenEncuentroLaSuscripcionPorTipo(ID_TIPO, cantidad_esperada);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarPorCliente(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        givenGuardoLaSuscripcion(suscripcion);
        thenEncuentroLaSuscripcionPorCliente(ID_CLIENTE);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoActualizarUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        givenGuardoLaSuscripcion(suscripcion);
        Long nuevo_tipo=ID_TIPO+1;
        whenActualizoLaSuscripcion(suscripcion, nuevo_tipo);
        thenEncuentroLaSuscripcionPorTipo(nuevo_tipo, 1);
    }


    private void whenActualizoLaSuscripcion(Suscripcion suscripcion, Long nuevo_tipo) {
        suscripcion.setTipo_id(nuevo_tipo);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }

    private Suscripcion givenExisteSuscripcion(Long id_cliente, Long id_tipo) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setCliente_id(id_cliente);
        suscripcion.setTipo_id(id_tipo);
        return suscripcion;
    }

    private void givenGuardoLaSuscripcion(Suscripcion suscripcion) {
        repositorioSuscripcion.guardar(suscripcion);
    }

    private Long whenGuardoLaSuscripcion(Suscripcion suscripcion) {
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion.getId();
    }

    private void thenEncuentroLaSuscripcionPorTipo(Long id_Tipo, Integer cantidad_esperada) {
        List<Suscripcion> suscripcionesBuscadas = repositorioSuscripcion.buscarPorTipo(id_Tipo);
        assertThat(suscripcionesBuscadas).hasSize(cantidad_esperada);
    }

    private void thenEncuentroLaSuscripcion(Long id) {
        Suscripcion buscada = repositorioSuscripcion.buscarPorId(id);
        assertThat(buscada).isNotNull();
    }

    private void thenEncuentroLaSuscripcionPorCliente(Long id_Cliente) {
        Suscripcion buscada = repositorioSuscripcion.buscarPorCliente(id_Cliente);
        assertThat(buscada).isNotNull();

    }
}
