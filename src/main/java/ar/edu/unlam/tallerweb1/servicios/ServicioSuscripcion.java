package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public interface ServicioSuscripcion {

    Suscripcion suscribir(Cliente cliente, TipoSuscripcion tipoSuscripcion);

    Boolean existeSuscripcionPorCliente(Cliente cliente);

    void renovarSuscripcion(Suscripcion suscripcion);

    void mejorarNivelSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_tipo);

    Suscripcion buscarPorIdCliente(Long id);
}
