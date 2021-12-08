package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException;

    Auto enviarAMantenimiento(Long buscado) throws NoEnviaAutoAMantenimiento, AutoNoExistente;


    Auto enviarARevision(Auto enMantenimiento, Usuario mecanico, LocalDate fecha_de_envio);

    List<Auto> obtenerAutosEnRevision() throws NoHayAutosParaRevision;
}
