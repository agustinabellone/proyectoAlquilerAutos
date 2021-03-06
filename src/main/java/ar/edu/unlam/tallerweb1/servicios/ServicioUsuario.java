package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayUsuariosPendientesDeRol;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.Exceptions.NoSeAsignoElRol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioUsuario {

    Usuario buscarPorId(Long id) throws NoHayEmpladosException;

    Usuario buscarPorEmail(String email);

    void eliminarUsuario(Long id);

    void actualizarUsuario(Long id_usuario, String nombre, String contraseña);

    List<Usuario> obtenerUsuariosSuscriptosAlPlanBasico() throws NoHayClientesSuscriptosAlPlanBasico;

    List<Solicitud> obtenerSolicitudesPendientesDeUnEncargado(Long IDusuario);

    List<Notificacion> getNotificacionesPorId(Usuario buscado);

    List<Usuario> obtenerListaDeUsuariosPorRol(String rol) throws NoHayEmpladosException;

    List<Usuario> obtenerListaDeUsuariosPendienteDeRol() throws NoHayUsuariosPendientesDeRol;

    void actualizarPuntaje(int puntaje, Usuario usuario);

    void restarPuntaje(int i, Usuario usuario);

    Suscripcion obtenerSuscripcionDeUsuario(Usuario clienteID);

    Usuario asignarRol(String mecanico, Long id_usuario) throws NoSeAsignoElRol;

    void actualizarNotificacion(Long id_noti);

    void reactivarCuenta(Usuario usuario);

    void generarNotificacion(Usuario usuario, String mensajeNotificacion, String colorNotificacion);

}
