package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoValorado;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;

import java.util.List;

public interface ServicioValoracion {


    List<Alquiler> obtenerAlquileresHechos(Long clienteID);

    Auto obtenerAutoPorId(Long autoID);

    void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto,Long alquilerID);

    Long obtenerValoracionPromedioAuto(Long autoID);

    List<ValoracionAuto> obtenerValoracionesAuto(Long autoID) throws AutoNoValorado;

}

