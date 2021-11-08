package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Alquiler;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.EstadoValoracionAuto;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ServicioValoracion")
@Transactional
public class ServicioValoracionImpls implements ServicioValoracion {

    private RepositorioValoracion repositorioValoracion;
    private RepositorioAuto repositorioAuto;
    private RepositorioAlquiler repositorioAlquiler;


    @Autowired
    public ServicioValoracionImpls(RepositorioValoracion repositorioValoracion, RepositorioAuto repositorioAuto,RepositorioAlquiler repositorioAlquiler) {
        this.repositorioValoracion = repositorioValoracion;
        this.repositorioAuto=repositorioAuto;
        this.repositorioAlquiler=repositorioAlquiler;
    }

    @Override
    public List<Alquiler> obtenerAlquileresHechos(Long clienteID) {
        List<Alquiler> alquileresHechos = repositorioValoracion.obtenerAlquileresHechos(clienteID);
        return alquileresHechos;
    }


    @Override
    public Auto obtenerAutoPorId(Long autoID) {
        Auto auto = repositorioAuto.buscarPor(autoID);
        return auto;
    }

    @Override
    public void guardarValoracionAuto(int cantidadEstrellas, String comentarioAuto, Auto auto,Long alquilerID) {
        repositorioValoracion.guardarValoracionAuto(cantidadEstrellas,comentarioAuto, auto);
        Alquiler alquiler=repositorioAlquiler.obtenerAlquilerPorId(alquilerID);
        alquiler.setEstadoValoracionAuto(Boolean.TRUE);
    }

    @Override
    public Long obtenerValoracionPromedioAuto(Long autoID){
        Long suma=0L;
        Long promedioValoracion=0L;
        Auto auto = obtenerAutoPorId(autoID);
        List<ValoracionAuto> valoraciones=repositorioValoracion.obtenerValoracionesAuto(auto);
        int cantidadValoracion=valoraciones.size();
        if(cantidadValoracion!=0){
            for(ValoracionAuto val:valoraciones){
                suma= suma + val.getValoracion();

            }
            promedioValoracion=suma/valoraciones.size();
        }
        return promedioValoracion;
    }


    @Override
    public List<ValoracionAuto> obtenerValoracionesAuto(Long autoID) {
        Auto auto = repositorioAuto.buscarPor(autoID);
        List<ValoracionAuto> valoracionesAuto = repositorioValoracion.obtenerValoracionesAuto(auto);
        return valoracionesAuto;
    }




}

