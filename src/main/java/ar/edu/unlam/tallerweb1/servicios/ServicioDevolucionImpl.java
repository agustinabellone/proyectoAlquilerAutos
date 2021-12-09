package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ServicioDevolucionImpl implements ServicioDevolucion {

    private RepositorioDevolucion repositorioDevolucion;
    private RepositorioSuscripcion repositorioSuscripcion;
    private RepositorioAuto repositorioAuto;


    public ServicioDevolucionImpl() {
    }

    @Autowired
    public ServicioDevolucionImpl(RepositorioDevolucion repositorioDevolucion, RepositorioSuscripcion repositorioSuscripcion, RepositorioAuto repositorioAuto) {
        this.repositorioDevolucion = repositorioDevolucion;
        this.repositorioSuscripcion = repositorioSuscripcion;
        this.repositorioAuto = repositorioAuto;
    }


    @Override
    public List<Alquiler> obtenerAlquilerActivoDeCliente(Usuario usuario) {
        List<Alquiler> alquiler = repositorioDevolucion.obtenerAlquilerActivoDeCliente(usuario);
        return alquiler;
    }

    @Override
    public void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler, Garage garage) {
        Suscripcion suscripcion = obtenerSuscripcionDeUsuario(alquiler.getUsuario());
        alquiler.setGarageLlegada(garage);
        setearAdicionalCambioLugarFecha(alquiler, suscripcion);
        repositorioDevolucion.updateAlquiler(alquiler);
    }

    @Override
    public void adicionarAumentoPorSobrepasoDeKilometros(Alquiler alquiler, Suscripcion suscripcion, Float kilometrosSobrepasados) {
        alquiler.setAdicionalKilometraje(suscripcion, kilometrosSobrepasados);
        repositorioDevolucion.updateAlquiler(alquiler);
    }

    @Override
    public void adicionarAumentoPorDevolucionEnMalascondiciones(Alquiler alquiler) {
        Suscripcion suscripcion = obtenerSuscripcionDeUsuario(alquiler.getUsuario());
        Float adicional = suscripcion.getTipoSuscripcion().getValorPorMalasCondiciones();
        alquiler.setAdicionalCondiciones(adicional);
    }

    @Override
    public void finalizarAlquilerCliente(Solicitud solicitud, String enCondiciones, String comentario, Integer kmPorEncargado) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        Solicitud solAlquilerModificado = modificarEstadosParaFinalizar(solicitud, enCondiciones, comentario);
        evaluarSobrepasoDeKilometrajes(kmPorEncargado, solAlquilerModificado);
        evaluarEnviarAMantenimiento(solicitud.getAlquiler().getAuto());
        repositorioDevolucion.finalizarAlquilerCliente(solAlquilerModificado.getAlquiler(), solAlquilerModificado, solAlquilerModificado.getAlquiler().getAuto()); //UPDATE
    }

    private void setearAdicionalCambioLugarFecha(Alquiler alquiler, Suscripcion suscripcion) {
        Usuario usuario = alquiler.getUsuario();
        if (usuario.getRol().equals("cliente")) {
            if (suscripcion.getUsuario().getId().equals(usuario.getId())) {
                if (alquiler.getGarageLlegada() != null) //QUIERE DECIR QUE HUBO CAMBIO DE GARAGE
                    alquiler.setAdicionalCambioLugarFecha(suscripcion.getTipoSuscripcion().getValorIncumplimientoHoraLugar());
            }
        }
    }

    private void evaluarEnviarAMantenimiento(Auto auto) {
        if (auto.getKm() >= auto.getLimiteKm()) {
            Auto autoObtenido = repositorioAuto.enviarAMantenimiento(auto.getId(), Situacion.EN_MANTENIMIENTO, LocalDate.now());
        }
    }

    private void evaluarSobrepasoDeKilometrajes(Integer kmPorEncargado, Solicitud solAlquilerModificado) {
        Suscripcion suscripcion = repositorioSuscripcion.obtenerSuscripcionDeUsuario(solAlquilerModificado.getUsuario());
        Integer kmRealizados = kmPorEncargado - solAlquilerModificado.getAlquiler().getAuto().getKm();
        if (kmRealizados > suscripcion.getTipoSuscripcion().getLimiteKilometraje()) {
            Float kmSobrepasados = (kmRealizados - suscripcion.getTipoSuscripcion().getLimiteKilometraje());
            adicionarAumentoPorSobrepasoDeKilometros(solAlquilerModificado.getAlquiler(), suscripcion, kmSobrepasados);
        }
        solAlquilerModificado.getAlquiler().getAuto().setKm(kmRealizados);
    }


    private Solicitud modificarEstadosParaFinalizar(Solicitud solicitud, String enCondiciones, String comentario) {
        Alquiler alquiler = solicitud.getAlquiler();
        if (enCondiciones == null) {
            adicionarAumentoPorDevolucionEnMalascondiciones(alquiler);
        }
        solicitud.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        solicitud.getAlquiler().getAuto().setSituacion(Situacion.DISPONIBLE);
        solicitud.getAlquiler().setComentario(comentario);
        solicitud.getAlquiler().setEstado(Estado.FINALIZADO);
        return solicitud;
    }


    private Suscripcion obtenerSuscripcionDeUsuario(Usuario usuario) {
        return repositorioDevolucion.obtenerSuscripcionDeUnUsuario(usuario);
    }


}

