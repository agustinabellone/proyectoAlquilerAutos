package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        if (buscarSiElAutoYaFueAlquiladoEnEsasFechas(datosAlquiler.getAuto(), datosAlquiler.getF_salida(), datosAlquiler.getF_ingreso())) {
            throw new AutoYaAlquiladoException();
        }
        repositorioAlquiler.guardar(alquiler);
        Auto auto = repositorioAlquiler.obtenerAutoPorId(datosAlquiler.getAuto().getId());
        auto.setSituacion(Situacion.OCUPADO);
        return alquiler;
    }

    @Override
    public boolean buscarSiElAutoYaFueAlquiladoEnEsasFechas(Auto auto, LocalDate f_egreso, LocalDate f_ingreso) {
        List<Alquiler> lista = obtenerAlquileresDelAuto(auto);

        if (lista.size() > 0) {
            for (Alquiler alquiler : lista) {
                if (alquiler.getF_egreso().isBefore(f_egreso) && alquiler.getF_ingreso().isAfter(f_egreso)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Alquiler obtenerAlquilerPendienteDeUsuario(Usuario usuario) {
        return repositorioAlquiler.obtenerAlquileresPendientesDeUsuario(usuario);
    }

    @Override
    public List<Garage> obtenerGaragesDisponibles() {
        return repositorioAlquiler.obtenerGaragesDisponibles();
    }

    @Override
    public List<Auto> obtenerAutosDisponibles() throws NoHayAutosDisponiblesException {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponibles();
        if (autosDisponibles.size() == 0) {
            throw new NoHayAutosDisponiblesException();
        }
        return autosDisponibles;
    }




    @Override
    public List<Auto> obtenerAutosDisponiblesGamaBaja() throws NoHayAutosDisponiblesException {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponiblesGamaBaja();
        if (autosDisponibles.size() == 0) {
            throw new NoHayAutosDisponiblesException();
        }
        return autosDisponibles;
    }

    @Override
    public List<Auto> obtenerAutosDisponiblesGamaMedia() throws NoHayAutosDisponiblesException {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponiblesGamaMedia();
        if (autosDisponibles.size() == 0) {
            throw new NoHayAutosDisponiblesException();
        }
        return autosDisponibles;
    }

    @Override
    public List<Auto> obtenerAutosDisponiblesGamaAlta() throws NoHayAutosDisponiblesException {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponiblesGamaAlta();
        if (autosDisponibles.size() == 0) {
            throw new NoHayAutosDisponiblesException();
        }
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
    public List<Alquiler> obtenerAlquileresDelAuto(Auto auto) {
        return repositorioAlquiler.obtenerAlquileresDelAuto(auto);
    }

    @Override
    public List<Auto> obtenerAutosAlquilados() throws NoHayAutosAlquiladosException {
        List<Auto> autosAlquilados = repositorioAlquiler.buscarAutosAlquilados(Situacion.OCUPADO);
        if (autosAlquilados.size() == 0 || autosAlquilados == null) {
            throw new NoHayAutosAlquiladosException();
        }
        return autosAlquilados;
    }

    @Override
    public Garage obtenerGaragePorId(Long lugar) {
        return repositorioAlquiler.obtenerGaragePorId(lugar);
    }

}
