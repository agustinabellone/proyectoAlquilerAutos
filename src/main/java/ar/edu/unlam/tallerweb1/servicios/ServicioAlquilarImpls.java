package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorio.TablaAlquiler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

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
