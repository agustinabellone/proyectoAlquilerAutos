package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface RepositorioDeAutos {
    List<Auto> obtenerTodosLosAutos();

    List<Auto> obtenerListaDeAutosPorMarca(String marca);
}
