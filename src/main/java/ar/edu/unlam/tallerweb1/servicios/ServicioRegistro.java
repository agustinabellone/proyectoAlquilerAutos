package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorio.TablaCliente;

public class ServicioRegistro {

    private TablaCliente tablaCliente = TablaCliente.getInstance();

    public Cliente registrar(DatosRegistro datosRegistro) {
        if(LasClavesSonDistintas(datosRegistro)){
            throw new ClavesDistintasException();
        }
        if(LaClaveTieneLongitudIncorrecta(datosRegistro)) {
            throw new ClaveLongitudIncorrectaException();
        }
        if(tablaCliente.existeClienteCon(datosRegistro.getEmail())){
            throw new ClienteYaExisteException();
        }
        Cliente nuevoCliente = new Cliente(datosRegistro);
        tablaCliente.agregar(nuevoCliente);
        return nuevoCliente;
    }

    private boolean LasClavesSonDistintas(DatosRegistro datosRegistro) {
        return !datosRegistro.getClave().equals(datosRegistro.getRepiteClave());
    }

    private boolean LaClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        return datosRegistro.getClave().length() < 8;
    }

}
