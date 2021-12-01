package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesNoSuscriptos;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

import java.util.List;

public interface ServicioSuscripcion {

    Suscripcion suscribir(Usuario usuario, TipoSuscripcion tipoSuscripcion);

    Boolean existeSuscripcionPorUsuario(Usuario usuario);

    Suscripcion suscripcionDeUsuario(Usuario usuario);

    void cancelarRenovacionAutomaticaDeSuscripcion(Long id);

    Suscripcion buscarPorIdUsuario(Long id);

    void revisionDeSuscripciones();

    TipoSuscripcion getTipoPorid(Long id_tipo);

    void cancelarSuscripcionForzada(Usuario usuario);

    void activarRenovacionAutomaticaDeSuscripcion(Long id);

    List<Suscripcion> obtenerClientesSuscriptos() throws NoHayClientesSuscriptos;

    List<Usuario> obtenerListaDeUsuariosNoSuscriptos()throws NoHayClientesNoSuscriptos;
}