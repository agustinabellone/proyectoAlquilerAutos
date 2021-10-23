package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface RepositorioValoracion {

    List<Alquiler> obtenerAlquileresHechos(Long clienteID);

    Auto obtenerAutoPorId(Long autoID);
}

