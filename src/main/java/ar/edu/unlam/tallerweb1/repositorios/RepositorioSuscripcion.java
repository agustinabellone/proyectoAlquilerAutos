package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioSuscripcion {
    Suscripcion guardar(Suscripcion suscripcion);

    Suscripcion buscarPorId(Long id);

    List<Suscripcion> buscarPorTipo(TipoSuscripcion tipoSuscripcion);

    Suscripcion buscarPorUsuario(Usuario usuario);

    void actualizarSuscripcion(Suscripcion suscripcion);

    List<Suscripcion> buscarSuscripcionesFueraDeFecha(LocalDate fechaActual);

}
