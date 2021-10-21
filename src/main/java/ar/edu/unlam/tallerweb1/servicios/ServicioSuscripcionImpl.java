package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.NivelDeSuscripcionActualMejorOIgualQueElNuevoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
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
    public Suscripcion suscribir(Usuario usuario, TipoSuscripcion tipoSuscripcion) {

        if(existeSuscripcionPorCliente(usuario)){
            throw new ClienteYaSuscriptoException();
        }
        Suscripcion suscripcion = new Suscripcion(usuario, tipoSuscripcion);
        suscripcion.setFechaInicio(LocalDate.now());
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion;
    }

    @Override
    public Boolean existeSuscripcionPorCliente(Usuario usuario) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorCliente(usuario);

        if (Objects.isNull(buscado)){
            return false;
        }
        return true;
    }

    @Override
    public void renovarAutomaticamenteSuscripcion(Long id) {

        Suscripcion buscada= buscarPorIdCliente(id);

        if(buscada.getRenovacion() == true){
            throw new SuscripcionYaRenovadaException();
        }
        buscada.setRenovacion(true);
        repositorioSuscripcion.actualizarSuscripcion(buscada);
    }

    @Override
    public void mejorarNivelSuscripcion(Suscripcion suscripcion, TipoSuscripcion nuevo_tipo) {
        if(suscripcion.getTipoSuscripcion().getId()>=nuevo_tipo.getId()){
            throw new NivelDeSuscripcionActualMejorOIgualQueElNuevoException();
        }
        suscripcion.setTipoSuscripcion(nuevo_tipo);
        repositorioSuscripcion.actualizarSuscripcion(suscripcion);
    }

    @Override
    public Suscripcion buscarPorIdCliente(Long id) {
        Usuario buscado = new Usuario(id);
        return repositorioSuscripcion.buscarPorCliente(buscado);
    }

    @Override
    public void revisionDeSuscripciones() {
        List<Suscripcion> listaDeBajas;
        listaDeBajas = repositorioSuscripcion.buscarSuscripcionesFueraDeFecha(LocalDate.now());
        if(!listaDeBajas.isEmpty()){
            for (Suscripcion suscripcion: listaDeBajas){

                suscripcion.setFechaFin(LocalDate.now());
                repositorioSuscripcion.actualizarSuscripcion(suscripcion);

                // SI LA RENOVACION ESTA ACTIVA, SE CREA UNA NUEVA SUSCRIPCION
                if(suscripcion.getRenovacion()){
                    suscribir(suscripcion.getCliente(),suscripcion.getTipoSuscripcion());
                    System.out.println("Se crea nueva suscripcion");
                }
            }
            System.out.println("Todas las suscripciones fueron dadas de baja correctamente");
        }else{
            System.out.println("No hubo bajas posibles");
        }
    }
}
