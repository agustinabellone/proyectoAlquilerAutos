package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;

import java.util.List;

public interface ServicioHome {
    List<String> obtenerFechasEnString(List<Alquiler> alquileresUsuario);

    List<Modelo> obtenerModeloAutoAlquiler(List<Alquiler> alquileresUsuario);

    List<Marca> obtenerMarcaAutoAlquiler(List<Alquiler> alquileresUsuario);
}
