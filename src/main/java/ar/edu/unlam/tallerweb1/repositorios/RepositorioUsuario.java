package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
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

    List<Usuario> buscarUsuariosPorSuscripcion(Suscripcion suscripcion);


    List<Notificacion> getNotificacionesPorId(Usuario buscado);

    List<Usuario> buscarUsuariosPorRol(Rol rol);

    List<Usuario> buscarUsuariosPendientesDeRol();

    Usuario actualizarRol(Rol rol, Long id_usuario);
}
