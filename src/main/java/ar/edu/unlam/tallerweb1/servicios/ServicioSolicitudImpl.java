package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioSolicitudImpl implements ServicioSolicitud{

    private RepositorioSolicitud repositorioSolicitud;
    private RepositorioAlquiler repositorioAlquiler;

    @Autowired
    public ServicioSolicitudImpl(RepositorioSolicitud repositorioSolicitud, RepositorioAlquiler repositorioAlquiler) {
        this.repositorioSolicitud = repositorioSolicitud;
        this.repositorioAlquiler = repositorioAlquiler;
    }


    @Override
    public void realizarPeticionDeDevolucion(Alquiler alquiler) {
        generarSolicitud(alquiler);
        alquiler.setEstado(Estado.PENDIENTE);
        repositorioAlquiler.actualizarAlquiler(alquiler);
    }

    @Override
    public Solicitud obtenerSolicitudPorId(Long solicitudID) {
        return repositorioSolicitud.obtenerSolicitudPorId(solicitudID);
    }

    @Override
    public void generarSolicitud(Alquiler alquiler) {
        Solicitud solicitud = new Solicitud(alquiler);
        Solicitud soli = obtenerSolicitud(solicitud);
        if(soli == null){
            repositorioSolicitud.crearSolicitud(solicitud);
        }
    }

    private Solicitud obtenerSolicitud(Solicitud solicitud) {
        return repositorioSolicitud.obtenerSolicitud(solicitud);
    }
}
