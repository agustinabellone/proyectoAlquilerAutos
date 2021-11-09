package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioAlquiler {

    void guardar(Alquiler alquiler);

    List<Auto> obtenerAutosDisponibles();

    Auto obtenerAutoPorId(Long id_auto);

    Usuario obtenerUsuarioPorId(Long id_usuario);

    List<Alquiler> obtenerAlquileresActivosDeUsuario(Usuario id);

    List<Auto> buscarAutosAlquilados(Situacion ocupado);

    Garage obtenerGaragePorId(Long lugar);

}
