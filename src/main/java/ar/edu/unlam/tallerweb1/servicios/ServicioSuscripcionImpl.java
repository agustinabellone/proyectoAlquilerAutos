package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.NivelDeSuscripcionActualMejorOIgualQueElNuevoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.controladores.DatosSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service("ServicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion{


    private RepositorioSuscripcion repositorioSuscripcion;

    @Autowired
    public ServicioSuscripcionImpl(RepositorioSuscripcion repositorioSuscripcion){
        this.repositorioSuscripcion=repositorioSuscripcion;
    }


    @Override
    public Suscripcion suscribir(DatosSuscripcion datosSuscripcion) {

        if(existeSuscripcionPorCliente(datosSuscripcion.getCliente())){
            throw new ClienteYaSuscriptoException();
        }
        Suscripcion suscripcion = new Suscripcion(datosSuscripcion);
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion;
    }

    @Override
    public Boolean existeSuscripcionPorCliente(Cliente cliente) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorCliente(cliente.getId());

        if (Objects.isNull(buscado)){
            return false;
        }
        return true;
    }

    @Override
    public void renovarSuscripcion(Suscripcion suscripcion) {
        if(suscripcion.getRenovacion() == true){
            throw new SuscripcionYaRenovadaException();
        }
        suscripcion.setRenovacion(true);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }

    @Override
    public void mejorarNivelSuscripcion(Suscripcion suscripcion, Long nuevo_tipo) {
        if(suscripcion.getTipo_id()>=nuevo_tipo){
            throw new NivelDeSuscripcionActualMejorOIgualQueElNuevoException();
        }
        suscripcion.setTipo_id(nuevo_tipo);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }
}
