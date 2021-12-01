package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;

import java.util.List;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    List<Auto> obtenerAutosEnMantenimiento() throws NoHayAutosEnMantenientoException;
}
