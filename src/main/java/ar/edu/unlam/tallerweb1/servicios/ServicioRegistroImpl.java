package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service("ServicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

    private RepositorioCliente repositorioCliente;

    @Autowired
    public ServicioRegistroImpl(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public Cliente registrar(DatosRegistro datosRegistro) {
        if(LasClavesSonDistintas(datosRegistro)){
            throw new ClavesDistintasException();
        }
        if(LaClaveTieneLongitudIncorrecta(datosRegistro)) {
            throw new ClaveLongitudIncorrectaException();
        }
        if(repositorioCliente.buscarPorEmail(datosRegistro.getEmail()) != null){
            throw new ClienteYaExisteException();
        }
        Cliente nuevoCliente = new Cliente(datosRegistro);
        repositorioCliente.guardar(nuevoCliente);
        return nuevoCliente;
    }

    private boolean LasClavesSonDistintas(DatosRegistro datosRegistro) {
        return !datosRegistro.getClave().equals(datosRegistro.getRepiteClave());
    }

    private boolean LaClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        return datosRegistro.getClave().length() < 8;
    }

}
