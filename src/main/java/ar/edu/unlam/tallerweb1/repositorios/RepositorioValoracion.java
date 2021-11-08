package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;

import java.util.List;

public interface RepositorioValoracion {

    List<Alquiler> obtenerAlquileresHechos(Long clienteID);

    Auto obtenerAutoPorId(Long autoID);

    Usuario obtenerClientePorId(Long autoID);

    void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto, Alquiler alquiler);

    Alquiler obtenerAlquilerPorId(Long id);

    ValoracionAuto obtenerValoracionALquilerAuto(Auto auto, Alquiler alquiler);

    List<ValoracionAuto> obtenerValoracionesAuto(Auto auto);
}

