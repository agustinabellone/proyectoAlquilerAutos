package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Cliente;


public interface ServicioRegistro {

    Cliente registrar(DatosRegistro datosRegistro);


}
