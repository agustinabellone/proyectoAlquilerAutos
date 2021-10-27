package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Encargado {

    @Id
    private Long id;

    private String nombres;

    @ManyToOne
    private Garage garage;


    public Encargado(Long id, String nombres, Garage garage) {
        this.id = id;
        this.nombres = nombres;
        this.garage = garage;
    }

    public Encargado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void enviarConfirmacion(Alquiler alquiler) {

    }
}
