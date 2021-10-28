package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
import ar.edu.unlam.tallerweb1.modelo.Auto;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto) throws AutoNoExistente;

    Auto enviarAutoMantenimiento(Auto aEnviar) throws AutoYaExistente;
}
