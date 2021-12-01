package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Solicitud {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Usuario encargado;
    @OneToOne
    private Alquiler alquiler;
    private EstadoSolicitud estadoSolicitud;


    public Solicitud(Alquiler alquiler) {
        this.usuario = alquiler.getUsuario();
        this.encargado = alquiler.getEncargado();
        this.alquiler = alquiler;
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
    }


    public Solicitud(Long id, Usuario usuario, Usuario encargado) {
        this.id = id;
        this.usuario = usuario;
        this.encargado = encargado;
    }

    public Solicitud() {
        this.estadoSolicitud=EstadoSolicitud.PENDIENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario cliente) {
        this.usuario = usuario;
    }

    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
}
