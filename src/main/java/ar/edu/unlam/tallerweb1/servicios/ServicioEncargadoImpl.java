package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEncargadoImpl implements ServicioEncargado{

    private RepositorioDevolucion repositorioDevolucion;

    @Autowired
    public ServicioEncargadoImpl(RepositorioDevolucion repositorioDevolucion) {
        this.repositorioDevolucion = repositorioDevolucion;
    }

    @Override
    public void enviarConfirmacionDeDevolucion(Long idAlquiler) {
      //  Alquiler alquiler = repositorioDevolucion.getAlquiler(idAlquiler);
        //alquiler.set
        //return alquiler;
    }
}
