package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.Cliente;


public interface ServicioLogin {

    Cliente ingresar(DatosLogin datosLogin);

}
