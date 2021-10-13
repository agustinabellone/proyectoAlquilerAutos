package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente Cliente;

    @OneToOne
    private TipoSuscripcion TipoSuscripcion;

    @Column(nullable = true)
    private Boolean Renovacion;

    @Column
    private Short DiasRestantes;

    public Suscripcion(){

    }

    public Suscripcion(Cliente cliente, TipoSuscripcion tipoSuscripcion) {
        this.Cliente = cliente;
        this.TipoSuscripcion = tipoSuscripcion;
        this.Renovacion=false;
        this.DiasRestantes=30;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente cliente) {
        Cliente = cliente;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return TipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcion tipoSuscripcion) {
        TipoSuscripcion = tipoSuscripcion;
    }

    public Boolean getRenovacion() {
        return Renovacion;
    }

    public void setRenovacion(Boolean renovacion) {
        Renovacion = renovacion;
    }

    public Short getDiasRestantes() {
        return DiasRestantes;
    }

    public void setDiasRestantes(Short diasRestantes) {
        DiasRestantes = diasRestantes;
    }
}
