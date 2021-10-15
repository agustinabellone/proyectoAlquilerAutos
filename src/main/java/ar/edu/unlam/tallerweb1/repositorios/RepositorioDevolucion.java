package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface RepositorioDevolucion {
    Auto finalizarAlquiler(Alquiler alquiler);

    void guardar(Auto auto);

    List<Auto> buscarPorSuEstadoDisponible(boolean b);
}

