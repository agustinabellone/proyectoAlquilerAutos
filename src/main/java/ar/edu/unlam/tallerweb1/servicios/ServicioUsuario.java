package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptosAlPlanBasico;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioUsuario {

    Usuario buscarPorId(Long id);

    Usuario buscarPorEmail(String email);

    void eliminarUsuario(Long id);

    void actualizarUsuario(Long id_usuario, String nombre, String contraseña);

    List<Usuario> obtenerUsuariosSuscriptosAlPlanBasico() throws NoHayClientesSuscriptosAlPlanBasico;

    List<Usuario> obtenerUsuariosSuscriptos(Suscripcion suscripcion);
}
