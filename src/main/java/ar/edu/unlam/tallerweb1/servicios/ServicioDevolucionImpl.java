package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;


import ar.edu.unlam.tallerweb1.modelo.Situacion;
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
    public void finalizarAlquiler(Alquiler alquiler) {
        alquiler.getAuto().setSituacion(Situacion.DISPONIBLE);
    }
}

