package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioAlquiler {

    Alquiler AlquilarAuto(DatosAlquiler datosAlquiler);

    List<Auto> obtenerAutosDisponibles();

    Auto obtenerAutoPorId(Long id_auto);

    Usuario obtenerUsuarioPorId(Long id_usuario);
}
