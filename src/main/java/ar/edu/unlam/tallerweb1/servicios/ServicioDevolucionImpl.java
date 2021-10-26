package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Alquiler obtenerAlquilerActivoDeCliente(Long clienteID) {
        Alquiler alquiler = repositorioDevolucion.obtenerAlquilerActivoDeCliente(clienteID);
        return alquiler;
    }

    @Override
    public Alquiler obtenerAlquilerPorID(Long alquilerID) {
        return repositorioDevolucion.obtenerAlquilerPorId(alquilerID);
    }

    @Override
    public void adicionarAumentoPorCambioDeLugarFecha(Alquiler alquiler) {
        alquiler.setAdicionalCambioLugarFecha(alquiler.getUsuario());
        repositorioDevolucion.adicionarAumentoPorCambioDeLugarFecha(alquiler);
    }

    @Override
    public void finalizarAlquilerCliente(Alquiler alquiler) {
        Solicitud solicitud = new Solicitud();
        solicitud.setEncargado(alquiler.getEncargado()); //METODO DUDOSO
        alquiler.getEncargado().enviarConfirmacion(alquiler);
    }

}

