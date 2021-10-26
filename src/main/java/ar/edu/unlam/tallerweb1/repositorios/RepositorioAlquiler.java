package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface RepositorioAlquiler {

    void guardar(Alquiler alquiler);

    Alquiler buscarAlquilerPorId(Long id);

    List<Auto> obtenerAutosDisponibles();
}
