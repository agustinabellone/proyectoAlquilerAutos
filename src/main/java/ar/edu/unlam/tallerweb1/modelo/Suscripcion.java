package ar.edu.unlam.tallerweb1.modelo;

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

    public Suscripcion(Long id_cliente, Long id_tipo) {
        this.Cliente_id = id_cliente;
        this.Tipo_id = id_tipo;
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
