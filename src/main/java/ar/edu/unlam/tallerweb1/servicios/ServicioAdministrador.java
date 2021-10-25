package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;


public interface ServicioAdministrador {
    List<Auto> obtenerTodosLoAutos() throws NohayAutosException;
}
