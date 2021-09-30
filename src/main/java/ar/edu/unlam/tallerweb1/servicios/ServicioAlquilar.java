package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;

public interface ServicioAlquilar {

    Alquiler AlquilarAuto(DatosAlquiler da);
}
