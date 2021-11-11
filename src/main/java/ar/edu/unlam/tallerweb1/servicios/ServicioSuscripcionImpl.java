package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
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

        if(existeSuscripcionPorUsuario(usuario)){
            throw new ClienteYaSuscriptoException();
        }
        LocalDate fechaHoy=LocalDate.now();
        Suscripcion suscripcion = new Suscripcion(usuario, tipoSuscripcion);
        suscripcion.setFechaInicio(fechaHoy);
        suscripcion.setFechaFin(fechaHoy.plusDays(tipoSuscripcion.getDuracion()));
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion;
    }


    @Override
    public Boolean existeSuscripcionPorUsuario(Usuario usuario) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorUsuario(usuario);

        if (Objects.isNull(buscado)){
            return false;
        }
        return true;
    }

    @Override
    public void cancelarRenovacionAutomaticaDeSuscripcion(Long id) {

        Suscripcion buscada= buscarPorIdUsuario(id);

        if(buscada.getRenovacion() == false){
            throw new SuscripcionYaCanceladaException();
        }
        buscada.setRenovacion(false);
        repositorioSuscripcion.actualizarSuscripcion(buscada);
    }

    @Override
    public Suscripcion buscarPorIdUsuario(Long id) {
        Usuario buscado = new Usuario(id);
        return repositorioSuscripcion.buscarPorUsuario(buscado);
    }

    @Override
    public void revisionDeSuscripciones() {
        List<Suscripcion> listaDeBajas;
        listaDeBajas = repositorioSuscripcion.buscarSuscripcionesFueraDeFecha(LocalDate.now());
        if(!listaDeBajas.isEmpty()){
            for (Suscripcion suscripcion: listaDeBajas){

                // SI LA RENOVACION ESTA ACTIVA, SE CREA UNA NUEVA SUSCRIPCION
                if(suscripcion.getRenovacion()){
                    suscribir(suscripcion.getUsuario(),suscripcion.getTipoSuscripcion());
                    System.out.println("Se crea nueva suscripcion");
                }
            }
            System.out.println("Todas las suscripciones fueron dadas de baja correctamente");
        }else{
            System.out.println("Ninguna baja");
        }
    }

    @Override
    public TipoSuscripcion getTipoPorid(Long id_tipo) {
        TipoSuscripcion tipo = repositorioSuscripcion.getTipoPorId(id_tipo);
        return tipo;
    }

    @Override
    public void cancelarSuscripcionForzada(Usuario usuario) {

        Suscripcion suscripcionBuscada = buscarPorIdUsuario(usuario.getId());

        suscripcionBuscada.setFechaFinForzada(LocalDate.now());

        repositorioSuscripcion.actualizarSuscripcion(suscripcionBuscada);
    }

    @Override
    public void activarRenovacionAutomaticaDeSuscripcion(Long id) {
        Suscripcion buscada= buscarPorIdUsuario(id);

        if(buscada.getRenovacion() == true){
            throw new SuscripcionYaActivadaException();
        }
        buscada.setRenovacion(true);
        repositorioSuscripcion.actualizarSuscripcion(buscada);
    }

    @Override
    public List<Suscripcion> obtenerUsuariosSuscriptos() throws NoHayClientesSuscriptosAlPlanBasico {
        List<Suscripcion> suscripcions = repositorioSuscripcion.buscarSuscripciones();
        if (suscripcions.size() == 0){
            throw new NoHayClientesSuscriptosAlPlanBasico();
        }
        return suscripcions;
    }
}
