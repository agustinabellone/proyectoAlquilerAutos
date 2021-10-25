package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioAdministradorImpl implements ServicioAdministrador{
    @Override
    public List<Auto> obtenerTodosLoAutos() throws NohayAutosException {
        throw new NohayAutosException();
    }
}
