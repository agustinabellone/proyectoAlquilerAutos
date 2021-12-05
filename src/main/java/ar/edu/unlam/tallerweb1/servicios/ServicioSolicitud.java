package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;

public interface ServicioSolicitud {

    void generarSolicitud(Alquiler alquiler);

    void realizarPeticionDeDevolucion(Alquiler alquiler);

    Solicitud obtenerSolicitudPorId(Long solicitudID);
}
