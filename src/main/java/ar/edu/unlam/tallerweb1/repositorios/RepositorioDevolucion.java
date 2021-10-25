package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;

public interface RepositorioDevolucion {

    Alquiler obtenerAlquilerActivoDeCliente(Long clienteID);

    Alquiler obtenerAlquilerPorId(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler);
}

