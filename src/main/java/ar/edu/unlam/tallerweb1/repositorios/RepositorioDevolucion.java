package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioDevolucion {

    Alquiler obtenerAlquilerActivoDeCliente(Usuario usuario);

    Alquiler obtenerAlquilerPorId(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler);
}

