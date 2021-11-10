package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public interface ServicioSuscripcion {

    Suscripcion suscribir(Usuario usuario, TipoSuscripcion tipoSuscripcion);

    Boolean existeSuscripcionPorUsuario(Usuario usuario);

    void cancelarRenovacionAutomaticaDeSuscripcion(Long id);

    void mejorarNivelSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_tipo);

    Suscripcion buscarPorIdUsuario(Long id);

    void revisionDeSuscripciones();
    
}