package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException;

    Auto enviarAMantenimiento(Long buscado) throws NoEnviaAutoAMantenimiento;
}
