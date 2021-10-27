package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;

public interface ServicioDeAuto {
    Auto buscarAutoPorId(Long idDelAuto);

    Boolean enviarAutoMantenimiento(Auto aEnviar);
}
