package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

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
    private int km;
    private String imagen;
    private String patente;



    private Situacion situacion;

    @Column
    private Gama gama;


    public Auto() {
        this.km = 100;
    }

    public Auto(Long id, Marca marca, Modelo modelo, String imagen, String patente, Tercero tercero, LocalDate añoFabricación, int kilometros, Situacion situacion,Gama gama) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
        this.patente = patente;
        this.tercero = tercero;
        this.añoFabricación = añoFabricación;
        this.km = kilometros;
        this.situacion = situacion;
        this.gama=gama;
    }

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

    public Tercero getTercero() {
        return tercero;

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

    public int getKm() {
        return km;
    }

    public void setKm(int kilometros) {
        this.km = kilometros;
    }

    public Situacion getSituacion() {
        return situacion;
    }


    public void setSituacion(Situacion estado) {
        this.situacion = estado;
    }


    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }
}
