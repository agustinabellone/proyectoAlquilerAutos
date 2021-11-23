package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;

@Entity
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    @ManyToOne
    private Marca marca;


    public Modelo() {
    }

    public Modelo(Integer id, String descripcion, Marca marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
