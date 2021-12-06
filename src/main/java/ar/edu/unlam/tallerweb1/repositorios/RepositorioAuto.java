package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioAuto {

    void guardar(Long id);

    Auto buscarPor(Long id);

    List<Auto> buscarPorModelo(Modelo modelo);

    List<Auto> buscarTodos();

    List<Auto> buscarPorMarca(Marca ford);

    List<Auto> buscarAutosEnMantenimiento(Situacion enMantenimiento);

    Auto enviarAMantenimiento(Long id, Situacion enMantenimiento);

    Auto buscarPorPatente(String patente);

    List<Auto> buscarAutosEnRevision(Situacion enRevision);

    void enviarARevision(Auto buscado, Usuario mecanico);

    List<Revision> buscarRevisionPorMecanico(Usuario mecanico);
}
