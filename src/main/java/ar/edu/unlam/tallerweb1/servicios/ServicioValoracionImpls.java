package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ServicioValoracion")
@Transactional
public class ServicioValoracionImpls implements ServicioValoracion {

    private RepositorioValoracion repositorioValoracion;


    @Autowired
    public ServicioValoracionImpls(RepositorioValoracion repositorioValoracion) {
        this.repositorioValoracion = repositorioValoracion;
    }

    @Override
    public List<Alquiler> obtenerAlquileresHechos(Long clienteID) {
        List<Alquiler> alquileresHechos = repositorioValoracion.obtenerAlquileresHechos(clienteID);
        return alquileresHechos;
    }

    @Override
    public Auto obtenerAutoPorId(Long autoID) {
        Auto auto = repositorioValoracion.obtenerAutoPorId(autoID);
        return auto;
    }
}

