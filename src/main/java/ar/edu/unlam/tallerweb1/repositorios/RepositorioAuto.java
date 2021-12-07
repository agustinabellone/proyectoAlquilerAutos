package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioAuto {

    void guardar(Long id);

    Auto buscarPor(Long id);

    List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento);

    Auto enviarAMantenimiento(Long id, Situacion enMantenimiento, LocalDate localDate);

    Mantenimiento buscarAutoPorIdDeMantenimiento(Long id);
}
