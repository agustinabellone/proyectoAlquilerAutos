package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer km;
    private String imagen;
    private String patente;
    @Column(nullable = true)
    private Integer limiteKm = 0;
    private Situacion situacion;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha_inicio_mantenimiento;
    @Column
    private Gama gama;


    public Auto() {
        this.km = 100;
        this.limiteKm = 1000;
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
        this.limiteKm = 1000;
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

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer kilometrosAdicionales) {
        this.km = this.km + kilometrosAdicionales;
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

    public Integer getLimiteKm() {
        return limiteKm;
    }

    public void setLimiteKm(Integer limiteKm) {
        this.limiteKm = limiteKm;
    }

    public LocalDate getFecha_inicio_mantenimiento() {
        return fecha_inicio_mantenimiento;
    }

    public void setFecha_inicio_mantenimiento(LocalDate fecha_inicio_mantenimiento) {
        this.fecha_inicio_mantenimiento = fecha_inicio_mantenimiento;
    }
}
