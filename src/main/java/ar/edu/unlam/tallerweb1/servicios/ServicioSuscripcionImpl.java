package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.NivelDeSuscripcionActualMejorOIgualQueElNuevoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
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
    public Suscripcion suscribir(Long id_cliente, Long id_tipo) {

        if(existeSuscripcionPorCliente(id_cliente)){
            throw new ClienteYaSuscriptoException();
        }
        Suscripcion suscripcion = new Suscripcion(id_cliente, id_tipo);
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion;
    }

    @Override
    public Boolean existeSuscripcionPorCliente(Long id_cliente) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorCliente(id_cliente);

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
