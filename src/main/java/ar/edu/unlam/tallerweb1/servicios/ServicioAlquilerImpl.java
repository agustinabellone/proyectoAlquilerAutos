package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
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

        /*if(repositorioAlquiler.buscarAutoPorId(datosAlquiler.getAuto().getId()) != null){
            throw new AutoYaAlquiladoException();
        }*/
        Alquiler alquiler = new Alquiler(datosAlquiler);
        repositorioAlquiler.guardar(alquiler);

        return alquiler;
    }

    @Override
    public List<Auto> obtenerAutosDisponibles() {
        List<Auto> autosDisponibles = repositorioAlquiler.obtenerAutosDisponibles();
        return autosDisponibles;
    }

}
