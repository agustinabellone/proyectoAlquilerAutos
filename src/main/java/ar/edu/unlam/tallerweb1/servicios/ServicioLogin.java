package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosLogin;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorio.TablaCliente;

public class ServicioLogin {

    private TablaCliente tablaCliente = TablaCliente.getInstance();

    public boolean ingresar(DatosLogin datosLogin) {
        if(!tablaCliente.existeClienteCon(datosLogin.getEmail())){
            throw new ClienteNoExisteException();
        }
        return true;
    }

}
