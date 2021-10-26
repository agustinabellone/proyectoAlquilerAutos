package ar.edu.unlam.tallerweb1.modelo;

<<<<<<< HEAD

import javax.persistence.*;
import java.time.LocalDate;
=======
import javax.persistence.*;
>>>>>>> master


@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Marca marca;
    @ManyToOne
    private Modelo modelo;
    @JoinColumn(nullable = true)
    @ManyToOne
    private Tercero tercero; //CAMBIAMOS BOOLEAN POR TERCERO QUE PUEDE SER NULO
    @ManyToOne
    private Garage garage;
    private LocalDate añoFabricación;
    private int kilometros;
    private String imagen;
    private String patente;

<<<<<<< HEAD
    private Situacion situacion;

    public Auto () {
        this.kilometros = 100;
    }

    public Auto(Long id, Marca marca, Modelo modelo, String imagen, String patente, Tercero tercero, LocalDate añoFabricación, int kilometros, Situacion situacion) {
=======
    public Auto(Long id, String marca, String modelo, Integer kilometros, String imagen, String patente, Boolean terceros, Integer añoFabricación) {
>>>>>>> master
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
        this.patente = patente;
<<<<<<< HEAD
        this.tercero = tercero;
=======
        this.kilometros = kilometros;
        this.terceros = terceros;
>>>>>>> master
        this.añoFabricación = añoFabricación;
        this.kilometros = kilometros;
        this.situacion = situacion;
    }

<<<<<<< HEAD
=======
    public Auto() {}

>>>>>>> master
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

<<<<<<< HEAD
    public Tercero getTercero() {
        return tercero;
=======
    public Boolean getTerceros() {
        return terceros;
>>>>>>> master
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public LocalDate getAñoFabricación() {
        return añoFabricación;
    }

    public void setAñoFabricación(LocalDate añoFabricación) {
        this.añoFabricación = añoFabricación;
    }

<<<<<<< HEAD
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
=======
    public int getKm() {
        return kilometros;
    }

    public void setKm(int kilometros) {
        this.kilometros = kilometros;
    }

    public String getEstado() {
        return estado;
>>>>>>> master
    }

    public Situacion getSituacion() {
        return situacion;
    }

<<<<<<< HEAD
    public void setSituacion(Situacion estado) {
        this.situacion = estado;
    }



=======
>>>>>>> master
}
