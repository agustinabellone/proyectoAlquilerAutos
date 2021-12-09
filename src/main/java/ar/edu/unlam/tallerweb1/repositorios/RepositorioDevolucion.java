package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioDevolucion {

    List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario);

    void updateAlquiler(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler, Solicitud solAlquilerModificado, Auto auto);

    Suscripcion obtenerSuscripcionDeUnUsuario(Usuario usuario);
}

