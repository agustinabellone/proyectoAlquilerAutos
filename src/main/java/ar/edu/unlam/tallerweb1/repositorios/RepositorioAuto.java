package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import ar.edu.unlam.tallerweb1.modelo.Situacion;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioAuto {

    void guardar(Long id);

    Auto buscarPor(Long id);

    List<Auto> buscarPorModelo(Modelo modelo);

    List<Auto> buscarTodos();

    List<Auto> buscarPorMarca(Marca ford);

    List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento);

    void enviarAMantenimiento(Long id, Situacion enMantenimiento);
}
