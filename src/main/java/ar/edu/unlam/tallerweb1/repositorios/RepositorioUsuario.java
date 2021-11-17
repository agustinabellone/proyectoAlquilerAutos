package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario buscarPorEmail(String email);

    List<Usuario> buscarTodos();

    void eliminarUsuario(Long id);

    void actualizarUsuario(Long id_usuario, String nombre, String contraseña);

    List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Usuario usuario);

    List<Usuario> buscarUsuariosPorSuscripcion(Suscripcion suscripcion);
}
