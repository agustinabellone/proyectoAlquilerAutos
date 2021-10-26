package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

public interface RepositorioAlquiler {

    void guardar(Alquiler alquiler);

    Alquiler buscarAlquilerPorId(Long id);

}
