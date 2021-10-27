package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioDevolucion {

    Alquiler obtenerAlquilerActivoDeCliente(Usuario usuario);

    Alquiler obtenerAlquilerPorID(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void finalizarAlquilerCliente(Alquiler alquiler);

}

