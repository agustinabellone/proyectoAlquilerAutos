package ar.edu.unlam.tallerweb1.persistencia;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

public class testPersistenciaSuscripcion extends SpringTest {

    private static final Long ID_USUARIO =123L;
    private static final Long ID_TIPO=1L;
    private static final Usuario USUARIO =new Usuario(ID_USUARIO);
    private static final TipoSuscripcion TIPO_SUSCRIPCION=new TipoSuscripcion(ID_TIPO);
    private static final TipoSuscripcion TIPO_SUSCRIPCION2=new TipoSuscripcion(ID_TIPO+1);

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        Long id=whenGuardoLaSuscripcion(suscripcion);
        thenLaPuedoBuscarPorId(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnaSuscripcion(){
        Suscripcion suscripcion = givenExisteSuscripcion(USUARIO, TIPO_SUSCRIPCION);
        Long id = givenPersistoLaSuscripcion(suscripcion);
        whenModificoLaSuscripcion(suscripcion, TIPO_SUSCRIPCION2);
        thenLaElTipoDeSuscripcionCambio(id, TIPO_SUSCRIPCION2);
    }

    private Long givenPersistoLaSuscripcion(Suscripcion suscripcion) {
        session().save(suscripcion);
        return suscripcion.getId();
    }

    private void whenModificoLaSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_TipoSuscripcion) {
        suscripcion.setTipoSuscripcion(nuevo_TipoSuscripcion);
        session().update(suscripcion);
    }

    private void thenLaElTipoDeSuscripcionCambio(Long id, TipoSuscripcion nuevo_TipoSuscripcion) {
        Suscripcion buscada = session().get(Suscripcion.class, id);
        assertThat(buscada.getTipoSuscripcion()).isEqualTo(nuevo_TipoSuscripcion);
    }

    private Suscripcion givenExisteSuscripcion(Usuario usuario, TipoSuscripcion tipoSuscripcion) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setUsuario(usuario);
        suscripcion.setTipoSuscripcion(tipoSuscripcion);
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
