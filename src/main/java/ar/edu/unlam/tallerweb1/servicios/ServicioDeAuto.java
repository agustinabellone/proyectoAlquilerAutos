package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException;

    Auto enviarAMantenimiento(Long buscado) throws NoEnviaAutoAMantenimiento, AutoNoExistente;


}
