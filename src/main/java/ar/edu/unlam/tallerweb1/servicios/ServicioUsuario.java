package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioUsuario {

    Usuario buscarPorId(Long id);

    Usuario buscarPorEmail(String email);

    void eliminarUsuario(Long id);

    void actualizarUsuario(Long id_usuario, String nombre, String contraseña);

    List<Notificacion> getNotificacionesPorId(Usuario buscado);

    List<Usuario> obtenerListaDeUsuariosPorRol(Rol rol) throws NoHayEmpladosException;

    List<Usuario> obtenerListaDeUsuariosPendienteDeRol() throws NoHayUsuariosPendientesDeRol;

    List<Usuario> obtenerListaDeUsuariosPorRol(String rol);

    Suscripcion obtenerSuscripcionDeUsuario(Usuario clienteID);

}
