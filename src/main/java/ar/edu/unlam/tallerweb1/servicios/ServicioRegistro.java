package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioRegistro {

    Usuario registrar(DatosRegistro datosRegistro);

}
