package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAutos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioAdministradorImpl implements ServicioAdministrador {

    private RepositorioDeAutos repositorio;

    @Autowired
    public ServicioAdministradorImpl(RepositorioDeAutos repositorio) {
        this.repositorio = repositorio;
    }

    public ServicioAdministradorImpl() {
    }


    @Override
    public List<Auto> obtenerTodosLoAutos() throws NohayAutosException {
        List<Auto> autosObtenidos = repositorio.obtenerTodosLosAutos();
        if (autosObtenidos.size() == 0) {
            throw new NohayAutosException();
        }
        return autosObtenidos;
    }
}
