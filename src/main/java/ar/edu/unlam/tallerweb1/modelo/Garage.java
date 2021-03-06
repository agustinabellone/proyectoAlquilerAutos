package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Garage {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private Integer capacidad;
    private Integer cantAutosActual;
    @OneToOne
    private Usuario encargado;


    public Garage(Long id, String direccion, Integer capacidad, Integer cantAutosActual, Usuario encargado) {
        this.id = id;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.cantAutosActual = cantAutosActual;
        this.encargado=encargado;
    }

    public Garage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCantAutosActual() {
        return cantAutosActual;
    }

    public void setCantAutosActual(Integer cantAutosActual) {
        this.cantAutosActual = cantAutosActual;
    }

    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }
}
