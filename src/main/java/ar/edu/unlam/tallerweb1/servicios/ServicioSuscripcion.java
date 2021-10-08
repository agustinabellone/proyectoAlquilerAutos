package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;

public interface ServicioSuscripcion {

    Suscripcion suscribir(Long id_cliente, Long id_tipo);

    Boolean existeSuscripcionPorCliente(Long id_cliente);

    void renovarSuscripcion(Suscripcion suscripcion);

    void mejorarNivelSuscripcion(Suscripcion suscripcion, Long nuevo_tipo);
}
