package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Solicitud;

public interface RepositorioSolicitud {
    void crearSolicitud(Solicitud alquiler);

    Solicitud obtenerSolicitud(Solicitud solicitud);

    Solicitud obtenerSolicitudPorId(Long solicitudID);
}
