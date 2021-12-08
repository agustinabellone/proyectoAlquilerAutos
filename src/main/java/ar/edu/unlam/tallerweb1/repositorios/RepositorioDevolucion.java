package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioDevolucion {

    List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario);

    void updateAlquiler(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler, Solicitud solAlquilerModificado);

    Suscripcion obtenerSuscripcionDeUnUsuario(Usuario usuario);
}

