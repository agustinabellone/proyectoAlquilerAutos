package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import java.util.List;

public interface RepositorioAuto {

    void guardar(Auto auto);

    Auto buscarPor(Long id);

    Auto buscarPorModelo(String modelo);

    List<Auto> buscarTodos();
}
