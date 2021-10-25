package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Solicitud {

    @Id
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Encargado encargado;


    public Solicitud(Long id, Cliente cliente, Encargado encargado) {
        this.id = id;
        this.cliente = cliente;
        this.encargado = encargado;
    }

    public Solicitud() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }
}
