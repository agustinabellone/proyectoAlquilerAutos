package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosSuscripcion;

import javax.persistence.*;

@Entity
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long Cliente_id;

    @Column(nullable = true)
    private Long Tipo_id;

    @Column(nullable = true)
    private Boolean Renovacion;

    public Suscripcion(){

    }

    public Suscripcion(DatosSuscripcion datosSuscripcion) {
        this.Cliente_id = datosSuscripcion.getCliente().getId();
        this.Tipo_id = datosSuscripcion.getTipoSuscripcion().getId();
        this.Renovacion=false;
    }

    public Long getCliente_id() {
        return Cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        Cliente_id = cliente_id;
    }

    public Long getTipo_id() {
        return Tipo_id;
    }

    public void setTipo_id(Long tipo_id) {
        this.Tipo_id = tipo_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRenovacion(Boolean estado) {
        this.Renovacion=estado;
    }

    public Boolean getRenovacion() {
        return this.Renovacion;
    }
}
