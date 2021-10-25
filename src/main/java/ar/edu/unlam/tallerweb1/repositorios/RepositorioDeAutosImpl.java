package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioDeAutosImpl implements RepositorioDeAutos {
    @Override
    public List<Auto> obtenerTodosLosAutos() {
        return null;
    }
}
