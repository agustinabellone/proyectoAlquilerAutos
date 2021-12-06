package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioDevolucionImpl implements ServicioDevolucion {

    private RepositorioDevolucion repositorioDevolucion;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioDeAuto servicioDeAuto;


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
        repositorioDevolucion.updateAlquiler(alquiler);
    }

    private void adicionarAumentoPorSobrepasoDeKilometros(Alquiler alquiler, Suscripcion suscripcion, int kilometrosSobrepasados) {
        alquiler.setAdicionalKilometraje(suscripcion, kilometrosSobrepasados);
        repositorioDevolucion.updateAlquiler(alquiler);
    }



    @Override
    public void finalizarAlquilerCliente(Solicitud solicitud, String enCondiciones, String comentario, int kmPorEncargado) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        Solicitud solAlquilerModificado = modificarEstadosParaFinalizar(solicitud, enCondiciones, comentario);
        evaluarSobrepasoDeKilometrajes(kmPorEncargado, solAlquilerModificado);
        evaluarEnviarAMantenimiento(solicitud.getAlquiler().getAuto());
        repositorioDevolucion.finalizarAlquilerCliente(solAlquilerModificado.getAlquiler(), solAlquilerModificado); //UPDATE
    }

    private void evaluarEnviarAMantenimiento(Auto auto) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        if(auto.getKm()>=auto.getLimiteKm()) {
            servicioDeAuto.enviarAMantenimiento(auto.getId());
        }
    }

    private void evaluarSobrepasoDeKilometrajes(int kmPorEncargado, Solicitud solAlquilerModificado) {
        Suscripcion suscripcion = servicioSuscripcion.suscripcionDeUsuario(solAlquilerModificado.getUsuario());
        int kmRealizados = kmPorEncargado-solAlquilerModificado.getAlquiler().getAuto().getKm();
        if(kmRealizados > suscripcion.getTipoSuscripcion().getLimiteKilometraje()) {
            int kmSobrepasados = (int) (kmRealizados - suscripcion.getTipoSuscripcion().getLimiteKilometraje());
            adicionarAumentoPorSobrepasoDeKilometros(solAlquilerModificado.getAlquiler(), suscripcion, kmSobrepasados);
        }
        solAlquilerModificado.getAlquiler().getAuto().setKm(kmPorEncargado);
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

