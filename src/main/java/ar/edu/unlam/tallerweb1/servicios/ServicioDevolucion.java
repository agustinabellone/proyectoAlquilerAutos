package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.Exceptions.UsuarioSinSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioDevolucion {

    List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario);

    Alquiler obtenerAlquilerPorID(Long alquilerID);

    void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler);

    void adicionarAumentoPorDevolucionEnMalascondiciones(Alquiler alquiler);

    void finalizarAlquilerCliente(Solicitud solicitud, String enCondiciones, String comentario, int km) throws NoEnviaAutoAMantenimiento, AutoNoExistente;

}

