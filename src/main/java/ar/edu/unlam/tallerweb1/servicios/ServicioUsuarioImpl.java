package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.Exceptions.NoSeAsignoElRol;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
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

    public ServicioUsuarioImpl() {
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
    public List<Usuario> obtenerUsuariosSuscriptosAlPlanBasico() throws NoHayClientesSuscriptosAlPlanBasico {
        return null;
    }

    public List<Usuario> obtenerListaDeUsuariosPorRol(String rol) throws NoHayEmpladosException {
        List<Usuario> buscados = repositorioUsuario.buscarUsuariosPorRol(rol);
        if (buscados.size() > 0) {
            return buscados;
        }
        throw new NoHayEmpladosException();
    }

    @Override
    public List<Usuario> obtenerListaDeUsuariosPendienteDeRol() throws NoHayUsuariosPendientesDeRol {
        List<Usuario> pendientesDeRol = repositorioUsuario.buscarUsuariosPendientesDeRol();
        if (pendientesDeRol.size() > 0) {
            return pendientesDeRol;
        }
        throw new NoHayUsuariosPendientesDeRol();
    }

    @Override
    public List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Long idUsuario) {
        Usuario usuario = repositorioUsuario.buscarPorId(idUsuario);
        return repositorioUsuario.obtenerSolicitudesPendientesDeUnEncargado(usuario);
    }


    @Override
    public List<Notificacion> getNotificacionesPorId(Usuario buscado) {
        return repositorioUsuario.getNotificacionesPorId(buscado);
    }

    @Override
    public void actualizarPuntaje(int puntaje, Usuario usuario) {
        repositorioUsuario.actualizarPuntaje(puntaje, usuario);
    }

    @Override
    public void restarPuntaje(int numero, Usuario usuario) {
        repositorioUsuario.restarPuntaje(numero, usuario);
    }

    public Suscripcion obtenerSuscripcionDeUsuario(Usuario cliente) {
        return repositorioSuscripcion.buscarPorUsuario(cliente);
    }

    @Override
    public Usuario asignarRol(String rol, Long id_usuario) throws NoSeAsignoElRol {
        Usuario buscado = repositorioUsuario.buscarPorId(id_usuario);
        if (buscado != null) {
            repositorioUsuario.actualizarRol(rol, buscado.getId());
            Usuario actualizado = buscarPorId(buscado.getId());
            return actualizado;
        }
        throw new NoSeAsignoElRol();
    }

    @Override
    public void actualizarNotificacion(Long id_noti) {
        this.repositorioUsuario.actualizarNotificacion(id_noti);
    }


    public void reactivarCuenta(Usuario usuario) {
         repositorioUsuario.reactivarUsuario(usuario);
    }

    @Override
    public void generarNotificacion(Usuario usuario, String mensajeNotificacion, String colorNotificacion) {
        Notificacion notificacion = new Notificacion(mensajeNotificacion, colorNotificacion, usuario);
        this.repositorioUsuario.guardarNotificacion(notificacion);
    }


    private boolean LaClaveTieneLongitudIncorrecta(String contraseña) {
        return contraseña.length() < 8;
    }
}
