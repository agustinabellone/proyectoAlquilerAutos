package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("ServicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion {


    private RepositorioSuscripcion repositorioSuscripcion;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioSuscripcionImpl(RepositorioSuscripcion repositorioSuscripcion, RepositorioUsuario repositorioUsuario) {
        this.repositorioSuscripcion = repositorioSuscripcion;
        this.repositorioUsuario = repositorioUsuario;
    }

    public ServicioSuscripcionImpl() {
    }


    @Override
    public Suscripcion suscribir(Usuario usuario, TipoSuscripcion tipoSuscripcion) {

        if (existeSuscripcionPorUsuario(usuario)) {
            throw new ClienteYaSuscriptoException();
        }
        LocalDate fechaHoy = LocalDate.now();
        Suscripcion suscripcion = new Suscripcion(usuario, tipoSuscripcion);
        suscripcion.setFechaInicio(fechaHoy);
        suscripcion.setFechaFin(fechaHoy.plusDays(tipoSuscripcion.getDuracion()));
        repositorioSuscripcion.guardar(suscripcion);
        return suscripcion;
    }


    @Override
    public Boolean existeSuscripcionPorUsuario(Usuario usuario) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorUsuario(usuario);

        if (Objects.isNull(buscado)) {
            return false;
        }
        return true;
    }

    @Override
    public Suscripcion suscripcionDeUsuario(Usuario usuario) {
        Suscripcion buscado = repositorioSuscripcion.buscarPorUsuario(usuario);
        return buscado;
    }

    @Override
    public void cancelarRenovacionAutomaticaDeSuscripcion(Long id) {

        Suscripcion buscada = buscarPorIdUsuario(id);

        if (buscada.getRenovacion() == false) {
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
    public void revisionDeSuscripcionesFueraDeTermino() {
        List<Suscripcion> listaDeBajas;
        listaDeBajas = repositorioSuscripcion.buscarSuscripcionesFueraDeFecha(LocalDate.now());
        if (!listaDeBajas.isEmpty()) {
            for (Suscripcion suscripcion : listaDeBajas) {

                // SI LA RENOVACION ESTA ACTIVA, SE CREA UNA NUEVA SUSCRIPCION
                if (suscripcion.getRenovacion()) {
                    suscribir(suscripcion.getUsuario(), suscripcion.getTipoSuscripcion());
                    suscripcion.setFechaFinForzada(LocalDate.now());
                    this.repositorioSuscripcion.actualizarSuscripcion(suscripcion);
                    String mensajeNoti="Su suscripcion fue renovada automaticamente";
                    String colorNoti="success";
                    generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                }else{
                    String mensajeNoti="Su suscripcion fue cancelada automaticamente";
                    String colorNoti="danger";
                    generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                }
            }
            System.out.println("Todas las suscripciones fueron dadas de baja correctamente");
        } else {
            System.out.println("Ninguna baja");
        }
    }

    @Override
    public void notificarUsuariosProximosAVencer() {
        List<Suscripcion> listaDeSuscripcionesEn5Dias;
        List<Suscripcion> listaDeSuscripcionesEn10Dias;

        LocalDate fechaInteresadaEn5Dias=LocalDate.now().plusDays(5);
        LocalDate fechaInteresadaEn10Dias=LocalDate.now().plusDays(10);

        listaDeSuscripcionesEn5Dias = repositorioSuscripcion.buscarSuscripcionesFueraDeFecha(fechaInteresadaEn5Dias);
        listaDeSuscripcionesEn10Dias = repositorioSuscripcion.buscarSuscripcionesFueraDeFecha(fechaInteresadaEn10Dias);

        if (!listaDeSuscripcionesEn5Dias.isEmpty()) {
            for (Suscripcion suscripcion : listaDeSuscripcionesEn5Dias) {
                    if(suscripcion.getRenovacion()){
                        String mensajeNoti="Su suscripcion sera renovada dentro de 5 dias";
                        String colorNoti="success";
                        generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                    }else{
                        String mensajeNoti="Su suscripcion sera cancelada dentro de 5 dias";
                        String colorNoti="danger";
                        generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                    }

            }
        } else {
            System.out.println("Ninguna Suscripcion en fecha interesada en 5");
        }

        if (!listaDeSuscripcionesEn10Dias.isEmpty()) {
            for (Suscripcion suscripcion : listaDeSuscripcionesEn10Dias) {
                if(suscripcion.getRenovacion()){
                    String mensajeNoti="Su suscripcion sera renovada dentro de 10 dias";
                    String colorNoti="success";
                    generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                }else{
                    String mensajeNoti="Su suscripcion sera cancelada dentro de 10 dias";
                    String colorNoti="danger";
                    generarNotificacion(suscripcion.getUsuario(),mensajeNoti, colorNoti);
                }

            }
        } else {
            System.out.println("Ninguna Suscripcion en fecha interesada en 10");
        }
    }

    @Override
    public Suscripcion obtenerSuscripcionDeUsuario(Usuario usuario) {

        return null;
    }

    @Override
    public void generarNotificacion(Usuario usuario, String mensajeNotificacion, String colorNotificacion) {
        Notificacion notificacion = new Notificacion(mensajeNotificacion, colorNotificacion, usuario);
        this.repositorioUsuario.guardarNotificacion(notificacion);
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
        Suscripcion buscada = buscarPorIdUsuario(id);

        if (buscada.getRenovacion() == true) {
            throw new SuscripcionYaActivadaException();
        }
        buscada.setRenovacion(true);
        repositorioSuscripcion.actualizarSuscripcion(buscada);
    }

    @Override
    public List<Suscripcion> obtenerClientesSuscriptos() throws NoHayClientesSuscriptos {
        List<Suscripcion> suscripcions = repositorioSuscripcion.buscarSuscripciones();
        if (suscripcions.size() > 0) {
            return suscripcions;
        }
        throw new NoHayClientesSuscriptos();
    }

    @Override
    public List<Usuario> obtenerListaDeUsuariosNoSuscriptos() throws NoHayClientesNoSuscriptos {
        List<Usuario> usuariosSinSuscripcion = new ArrayList<>();
        List<Usuario> usuariosClientes = repositorioUsuario.buscarUsuariosPorRol("cliente");
        if (usuariosClientes.size() > 0) {
            for (Usuario usuario : usuariosClientes) {
                Suscripcion buscado = repositorioSuscripcion.buscarPorUsuario(usuario);
                if (Objects.isNull(buscado)) {
                    usuariosSinSuscripcion.add(usuario);
                }
            }
            if (usuariosSinSuscripcion.size() > 0) {
                return usuariosSinSuscripcion;
            }
            throw new NoHayClientesNoSuscriptos();
        }

        throw new NoHayClientesNoSuscriptos();
    }
}
