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
    private int kilometros;
    private String imagen;
    private String patente;

    private Situacion situacion;

    public Auto () {
        this.kilometros = 100;
    }

    public Auto(Long id, Marca marca, Modelo modelo, String imagen, String patente, Tercero tercero, LocalDate añoFabricación, int kilometros, Situacion situacion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
        this.patente = patente;
        this.tercero = tercero;
        this.añoFabricación = añoFabricación;
        this.kilometros = kilometros;
        this.situacion = situacion;
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

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public Situacion getSituacion() {
        return situacion;
    }

    public void setSituacion(Situacion estado) {
        this.situacion = estado;
    }



}
