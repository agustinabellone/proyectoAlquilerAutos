package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;

public interface ServicioDevolucion {

    Alquiler obtenerAlquilerActivoDeCliente(Long clienteID);

    Alquiler obtenerAlquilerPorID(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler);
}

