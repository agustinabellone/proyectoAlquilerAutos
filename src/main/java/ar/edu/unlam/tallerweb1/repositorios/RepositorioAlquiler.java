package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioAlquiler {

    void guardar(Alquiler alquiler);


    List<Auto> obtenerAutosDisponibles();

    List<Auto> obtenerAutosDisponiblesGamaBaja();

    List<Auto> obtenerAutosDisponiblesGamaMedia();

    List<Auto> obtenerAutosDisponiblesGamaAlta();

    Auto obtenerAutoPorId(Long id_auto);

    Usuario obtenerUsuarioPorId(Long id_usuario);

    List<Alquiler> obtenerAlquileresActivosDeUsuario(Usuario id);

    List<Auto> buscarAutosAlquilados(Situacion ocupado);

    Garage obtenerGaragePorId(Long lugar);

    List<Alquiler> obtenerAlquileresDelAuto(Auto id);

    void actualizarAlquiler(Alquiler alquiler);

    Alquiler obtenerAlquileresPendientesDeUsuario(Usuario usuario);

    List<Garage> obtenerGaragesDisponibles();

    Alquiler obtenerAlquilerPorId(Long alquilerID);
}
