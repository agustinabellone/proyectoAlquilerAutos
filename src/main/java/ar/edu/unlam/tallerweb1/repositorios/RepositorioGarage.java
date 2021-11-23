package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Garage;

import java.util.List;

public interface RepositorioGarage {
    List<Garage> obtenerListadoDeGarages();

    Garage obtenerGaragePorId(Long garageID);
}
