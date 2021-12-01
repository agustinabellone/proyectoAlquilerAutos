package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioGarageImpl implements ServicioGarage{

    private RepositorioGarage repositorioGarage;

    @Autowired
    public ServicioGarageImpl(RepositorioGarage repositorioGarage) {
        this.repositorioGarage = repositorioGarage;
    }

    @Override
    public List<Garage> obtenerListadoDeGarages() {
        return repositorioGarage.obtenerListadoDeGarages();
    }

    @Override
    public Garage obtenerGaragePorID(Long garageID) {
        return repositorioGarage.obtenerGaragePorId(garageID);
    }
}
