package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface ServicioAlquiler {

    Alquiler AlquilarAuto(DatosAlquiler datosAlquiler);

    List<Auto> obtenerAutosDisponibles() throws NoHayAutosDisponiblesException;

    List<Auto> obtenerAutosDisponiblesGamaBaja() throws NoHayAutosDisponiblesException;


    List<Auto> obtenerAutosDisponiblesGamaMedia() throws NoHayAutosDisponiblesException;

    List<Auto> obtenerAutosDisponiblesGamaAlta() throws NoHayAutosDisponiblesException;

    Auto obtenerAutoPorId(Long id_auto);

    Usuario obtenerUsuarioPorId(Long id_usuario);

    List<Alquiler> obtenerAlquileresDeUsuario(Usuario id);

    List<Alquiler> obtenerAlquileresDelAuto(Auto id);

    List<Auto> obtenerAutosAlquilados() throws NoHayAutosAlquiladosException;

    Garage obtenerGaragePorId(Long lugar);

    boolean buscarSiElAutoYaFueAlquiladoEnEsasFechas(Auto auto, LocalDate f_egreso, LocalDate f_ingreso);

    Alquiler obtenerAlquilerPendienteDeUsuario(Usuario usuario);

    List<Garage> obtenerGaragesDisponibles();

    Alquiler obtenerAlquilerPorID(Long alquilerID);
}
