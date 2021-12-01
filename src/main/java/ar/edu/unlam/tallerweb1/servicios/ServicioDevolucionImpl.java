package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioDevolucionImpl implements ServicioDevolucion{

    private RepositorioDevolucion repositorioDevolucion;


    public ServicioDevolucionImpl(){
    }

    @Autowired
    public ServicioDevolucionImpl(RepositorioDevolucion repositorioDevolucion) {
        this.repositorioDevolucion=repositorioDevolucion;
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

    private Suscripcion obtenerSuscripcionDeUsuario(Usuario usuario) {
        return repositorioDevolucion.obtenerSuscripcionDeUnUsuario(usuario);
    }

    @Override
    public void finalizarAlquilerCliente(Alquiler alquiler, Suscripcion suscripcion) {
        //CAMBIAR ESTADO AUTO, CAMBIAR ESTADO ALQUIER
        alquiler.getAuto().setSituacion(Situacion.DISPONIBLE);
        alquiler.setEstado(Estado.FINALIZADO);
        alquiler.setAdicionalCambioLugarFecha(alquiler, suscripcion);
        repositorioDevolucion.finalizarAlquilerCliente(alquiler); //UPDATE
    }

}

