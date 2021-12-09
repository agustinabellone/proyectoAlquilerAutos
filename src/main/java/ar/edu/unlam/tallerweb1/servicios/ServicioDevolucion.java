package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface ServicioDevolucion {

    List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler, Garage garage);

    void adicionarAumentoPorDevolucionEnMalascondiciones(Alquiler alquiler);

    void finalizarAlquilerCliente(Solicitud solicitud, String enCondiciones, String comentario, Integer km) throws NoEnviaAutoAMantenimiento, AutoNoExistente;

    void adicionarAumentoPorSobrepasoDeKilometros (Alquiler alquiler, Suscripcion suscripcion, Float kilometrosSobrepasados);
}

