package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.Exceptions.UsuarioSinSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioDevolucionImpl implements ServicioDevolucion {

    private RepositorioDevolucion repositorioDevolucion;


    public ServicioDevolucionImpl() {
    }

    @Autowired
    public ServicioDevolucionImpl(RepositorioDevolucion repositorioDevolucion) {
        this.repositorioDevolucion = repositorioDevolucion;
    }


    @Override
    public List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario) {
        List<Alquiler> alquiler = repositorioDevolucion.obtenerAlquilerActivoDeCliente(usuario);
        return alquiler;
    }

    @Override
    public Alquiler obtenerAlquilerPorID(Long alquilerID) {
        return repositorioDevolucion.obtenerAlquilerPorId(alquilerID);
    }

    @Override
    public void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler) {
        Suscripcion suscripcion = obtenerSuscripcionDeUsuario(alquiler.getUsuario());
        alquiler.setAdicionalCambioLugarFecha(alquiler, suscripcion);
        repositorioDevolucion.adicionarAumentoPorCambioDeLugarFecha(alquiler);
    }



    @Override
    public void finalizarAlquilerCliente(Solicitud solicitud, String enCondiciones, String comentario) {
        Solicitud solAlquilerModificado = modificarEstadosParaFinalizar(solicitud, enCondiciones, comentario);
        repositorioDevolucion.finalizarAlquilerCliente(solAlquilerModificado.getAlquiler(), solAlquilerModificado); //UPDATE
    }

    private Solicitud modificarEstadosParaFinalizar(Solicitud solicitud, String enCondiciones, String comentario) {
        Alquiler alquiler = solicitud.getAlquiler();
        if(enCondiciones==null) {
            adicionarAumentoPorDevolucionEnMalascondiciones(alquiler);
        }
        solicitud.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        solicitud.getAlquiler().getAuto().setSituacion(Situacion.DISPONIBLE);
        solicitud.getAlquiler().setComentario(comentario);
        solicitud.getAlquiler().setEstado(Estado.FINALIZADO);
        //solicitud.getAlquiler().setAdicionalKilometraje();
        return solicitud;
    }

    @Override
    public void adicionarAumentoPorDevolucionEnMalascondiciones(Alquiler alquiler) {
        Suscripcion suscripcion = obtenerSuscripcionDeUsuario(alquiler.getUsuario());
        alquiler.setAdicionalCondiciones(alquiler, suscripcion);
    }



    private Suscripcion obtenerSuscripcionDeUsuario(Usuario usuario) {
        return repositorioDevolucion.obtenerSuscripcionDeUnUsuario(usuario);
    }



}

