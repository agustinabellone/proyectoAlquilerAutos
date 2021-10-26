package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;

public class ServicioAutoImpl implements ServicioAuto {

    private RepositorioAuto repositorioAuto;

    @Override
    public Auto obtenerAutoPorId(Long id) {
        Auto auto = repositorioAuto.buscarPor(id);
        return auto;
    }
}
