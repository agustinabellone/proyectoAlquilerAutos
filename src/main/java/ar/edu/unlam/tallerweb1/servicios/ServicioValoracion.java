package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface ServicioValoracion {


    List<Alquiler> obtenerAlquileresHechos(Long clienteID);

    Auto obtenerAutoPorId(Long autoID);

    void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto,Long alquiler);

    Long obtenerValoracionAuto(Auto auto);

    Alquiler obtenerAlquilerPorId(Long alquilerID);
}

