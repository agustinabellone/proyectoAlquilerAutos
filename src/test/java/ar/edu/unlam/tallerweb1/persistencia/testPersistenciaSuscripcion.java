package ar.edu.unlam.tallerweb1.persistencia;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

public class testPersistenciaSuscripcion extends SpringTest {

    private static final Long ID_CLIENTE=123L;
    private static final Long ID_TIPO=1L;

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        Long id=whenGuardoLaSuscripcion(suscripcion);
        thenLaPuedoBuscarPorId(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteSuscripcion(ID_CLIENTE, ID_TIPO);
        Long id = givenPersistoLaSuscripcion(suscripcion);
        Long nuevo_tipo=ID_TIPO+1;
        whenModificoLaSuscripcion(suscripcion, nuevo_tipo);
        thenLaElTipoDeSuscripcionCambio(id, nuevo_tipo);
    }

    private Long givenPersistoLaSuscripcion(Suscripcion suscripcion) {
        session().save(suscripcion);
        return suscripcion.getId();
    }

    private void whenModificoLaSuscripcion(Suscripcion suscripcion, Long nuevo_tipo) {
        suscripcion.setTipo_id(nuevo_tipo);
        session().update(suscripcion);
    }

    private void thenLaElTipoDeSuscripcionCambio(Long id, Long nuevo_tipo) {
        Suscripcion buscada = session().get(Suscripcion.class, id);
        assertThat(buscada.getTipo_id()).isEqualTo(nuevo_tipo);
    }

    private Suscripcion givenExisteSuscripcion(Long id_cliente, Long id_tipo) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setCliente_id(id_cliente);
        suscripcion.setTipo_id(id_tipo);
        return suscripcion;
    }

    private Long whenGuardoLaSuscripcion(Suscripcion suscripcion) {
        session().save(suscripcion);
        return suscripcion.getId();
    }

    private void thenLaPuedoBuscarPorId(Long id) {
        Suscripcion buscada = session().get(Suscripcion.class, id);
        assertThat(buscada).isNotNull();
    }

}
