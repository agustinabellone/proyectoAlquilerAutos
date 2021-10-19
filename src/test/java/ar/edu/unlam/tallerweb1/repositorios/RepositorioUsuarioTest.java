package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioUsuarioTest extends SpringTest {

    private static final String ADMIN = "ADMIN";
    private static final String INVITADO = "INVITADO";

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Rollback
    @Transactional
    public void alBuscarPorRolDebeDevolverUsuariosConEseRol() {
        givenQueExisteUsuariosConRol(ADMIN, 2);
        givenQueExisteUsuariosConRol(INVITADO, 3);

        List<Usuario> usuarios = whenBuscoUsuarioConRol(ADMIN);

        thenEncuentro(usuarios, 2);
    }

    @Test
    @Rollback
    @Transactional
    public void alBuscarPorRolNoDeberiaDevolverResultadosSiNoExistenUsuariosConEseRol() {
        givenQueExisteUsuariosConRol(INVITADO, 3);
        List<Usuario> usuarios = whenBuscoUsuarioConRol(ADMIN);
        thenEncuentro(usuarios, 0);
    }

    @Test
    @Rollback
    @Transactional
    public void buscarUsuariosConMailDeAdmin() {
        givenQueExisteUsuariosConRol(ADMIN, 3);
        List<Usuario> usuarios = whenBuscoUsuarioConMail(ADMIN);
        thenEncuentro(usuarios, 3);
    }

    private List<Usuario> whenBuscoUsuarioConMail(String mail) {
        return repositorioUsuario.buscarUsuarioConMailLike(mail);
    }

    private void givenQueExisteUsuariosConRol(String rol, int cantidadDeUsuarios) {
        for (int i = 0; i < cantidadDeUsuarios; i++) {
            Usuario usuario = new Usuario();
            usuario.setEmail("eze" + i + "@eze.com" + rol);
            usuario.setPassword("123" + i);
            usuario.setRol(rol);

            Cuenta cuenta = new Cuenta();
            cuenta.setCreada(new Date());
            usuario.setCuenta(cuenta);

            session().save(usuario);
        }
    }

    private List<Usuario> whenBuscoUsuarioConRol(String rol) {
        return repositorioUsuario.buscarPorRol(rol);
    }

    private void thenEncuentro(List<Usuario> usuarios, int usuariosEncontrados) {
        assertThat(usuarios).hasSize(usuariosEncontrados);
    }
}
