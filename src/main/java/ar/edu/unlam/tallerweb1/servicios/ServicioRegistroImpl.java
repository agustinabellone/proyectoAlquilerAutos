package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("ServicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario registrar(DatosRegistro datosRegistro) {
        if (LasClavesSonDistintas(datosRegistro)) {
            throw new ClavesDistintasException();
        }
        if (LaClaveTieneLongitudIncorrecta(datosRegistro)) {
            throw new ClaveLongitudIncorrectaException();
        }
        if (repositorioUsuario.buscarPorEmail(datosRegistro.getEmail()) != null) {
            throw new ClienteYaExisteException();
        }
        if (datosRegistro.getEmail().contains("@tallerweb")) {
            Usuario nuevoUsuario = new Usuario(datosRegistro);
            nuevoUsuario.setRol(Rol.EMPLEADO);
            repositorioUsuario.guardar(nuevoUsuario);
        }
        Usuario nuevoUsuario = new Usuario(datosRegistro);

        nuevoUsuario.setRol(Rol.CLIENTE);

        repositorioUsuario.guardar(nuevoUsuario);
        return nuevoUsuario;
    }

    private boolean LasClavesSonDistintas(DatosRegistro datosRegistro) {
        return !datosRegistro.getClave().equals(datosRegistro.getRepiteClave());
    }

    private boolean LaClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        return datosRegistro.getClave().length() < 8;
    }

}
