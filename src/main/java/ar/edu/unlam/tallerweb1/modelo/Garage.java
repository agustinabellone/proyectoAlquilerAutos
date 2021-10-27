package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Garage {

    @Id
    private Integer id;
    private String direccion;
    private Integer capacidad;
    private Integer cantAutosActual;


    public Garage(Integer id, String direccion, Integer capacidad, Integer cantAutosActual) {
        this.id = id;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.cantAutosActual = cantAutosActual;
    }

    public Garage() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
