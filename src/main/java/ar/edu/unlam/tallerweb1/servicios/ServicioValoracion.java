package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface ServicioValoracion {


    List<Alquiler> obtenerAlquileresHechos(Long clienteID);

    Auto obtenerAutoPorId(Long autoID);
}

