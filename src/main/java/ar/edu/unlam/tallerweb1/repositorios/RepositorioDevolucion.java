package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioDevolucion {

    List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario);

    Alquiler obtenerAlquilerPorId(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler);

    Suscripcion obtenerSuscripcionDeUnUsuario(Usuario usuario);
}

