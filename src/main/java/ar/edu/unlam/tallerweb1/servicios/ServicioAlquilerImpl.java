package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ServicioAlquiler")
@Transactional
public class ServicioAlquilerImpl implements ServicioAlquiler {

    private RepositorioAlquiler repositorioAlquiler;

    @Autowired
    public ServicioAlquilerImpl(RepositorioAlquiler repositorioAlquiler) {
        this.repositorioAlquiler = repositorioAlquiler;
    }


    @Override
    public Alquiler AlquilarAuto(DatosAlquiler datosAlquiler) {
        Alquiler alquiler = new Alquiler(datosAlquiler);
        repositorioAlquiler.guardar(alquiler);
        Auto auto = repositorioAlquiler.obtenerAutoPorId(datosAlquiler.getAuto().getId());
        auto.setSituacion(Situacion.OCUPADO);
        return alquiler;
    }

    @Override
    public List<Auto> obtenerAutosDisponibles() {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponibles();
        return autosDisponibles;
    }

    @Override
    public Auto obtenerAutoPorId(Long id_auto) {
        Auto auto = repositorioAlquiler.obtenerAutoPorId(id_auto);
        return auto;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id_usuario) {
        Usuario usuario = repositorioAlquiler.obtenerUsuarioPorId(id_usuario);
        return usuario;
    }

    @Override
    public List<Alquiler> obtenerAlquileresDeUsuario(Usuario id) {
        return repositorioAlquiler.obtenerAlquileresActivosDeUsuario(id);
    }

    @Override
    public List<Auto> obtenerAutosAlquilados() throws NoHayAutosAlquiladosException {
        List<Auto> autosAlquilados = repositorioAlquiler.buscarAutosAlquilados(Situacion.OCUPADO);
        if (autosAlquilados.size() == 0){
            throw new NoHayAutosAlquiladosException();
        }
        return autosAlquilados;
    }

    @Override
    public Garage obtenerGaragePorId(Long lugar) {
        return repositorioAlquiler.obtenerGaragePorId(lugar);
    }

}
