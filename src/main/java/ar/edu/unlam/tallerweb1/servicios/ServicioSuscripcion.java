package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;

public interface ServicioSuscripcion {

    Suscripcion suscribir(DatosSuscripcion datosSuscripcion);

    Boolean existeSuscripcionPorCliente(Cliente cliente);

    void renovarSuscripcion(Suscripcion suscripcion);

    void mejorarNivelSuscripcion(Suscripcion suscripcion, Long nuevo_tipo);
}
