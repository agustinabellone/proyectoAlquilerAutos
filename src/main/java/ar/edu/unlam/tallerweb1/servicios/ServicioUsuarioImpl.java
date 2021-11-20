package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayEncargadosException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ServicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;
    private RepositorioSuscripcion repositorioSuscripcion;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioSuscripcion repositorioSuscripcion) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioSuscripcion = repositorioSuscripcion;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return this.repositorioUsuario.buscarPorId(id);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repositorioUsuario.buscarPorEmail(email);
    }

    @Override
    public void eliminarUsuario(Long id) {
        this.repositorioUsuario.eliminarUsuario(id);
    }

    @Override
    public void actualizarUsuario(Long id_usuario, String nombre, String contraseña) {
        if (LaClaveTieneLongitudIncorrecta(contraseña)) {
            throw new ClaveLongitudIncorrectaException();
        }
        this.repositorioUsuario.actualizarUsuario(id_usuario, nombre, contraseña);
    }

    @Override
    public List<Usuario> obtenerListaDeUsuariosPorRol(String rol) throws NoHayEncargadosException {
        throw new NoHayEncargadosException();
    }

    private boolean LaClaveTieneLongitudIncorrecta(String contraseña) {
        return contraseña.length() < 8;
    }
}
