package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorios.TablaAlquiler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioAlquilarImpls implements ServicioAlquilar {

    private TablaAlquiler tablaAlquiler= TablaAlquiler.getInstance();

    @Override
    public Alquiler AlquilarAuto(DatosAlquiler da) {
        if(tablaAlquiler.existeAlquilerCon(da.getAuto())){
            throw new AutoYaAlquiladoException();
        }
        Alquiler alquiler = new Alquiler(da);
        tablaAlquiler.agregar(alquiler);
        return alquiler;
    }
}
