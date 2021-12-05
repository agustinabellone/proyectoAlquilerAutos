package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario buscarPorEmail(String email);

    List<Usuario> buscarTodos();

    Usuario buscarPorEmailYHash(String email, String hash);

    void eliminarUsuario(Long id);

    void actualizarUsuario(Long id_usuario, String nombre, String contraseña);

    List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Usuario usuario);

    List<Usuario> buscarUsuariosPorSuscripcion(Suscripcion suscripcion);

    List<Notificacion> getNotificacionesPorId(Usuario buscado);

    List<Usuario> buscarUsuariosPorRol(String rol);

    List<Usuario> buscarUsuariosPendientesDeRol();

    void actualizarPuntaje(int puntaje, Usuario usuario);

    void actualizarRol(String rol, Long id_usuario);

    void restarPuntaje(int numero, Usuario usuario);

    void actualizarNotificacion(Long id_noti);

    void reactivarUsuario(Usuario usuario);

}
