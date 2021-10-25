package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Garage;

import java.util.List;

public interface ServicioGarage {
    List<Garage> obtenerListadoDeGarages();

    Garage obtenerGaragePorID(Long garageID);
}
