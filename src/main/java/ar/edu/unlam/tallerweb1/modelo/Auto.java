package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;


@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String imagen;
    private String patente;
    private Boolean terceros;
    private Integer añoFabricación;
    private int kilometros;
    private String estado;

    public Auto(Long id, String marca, String modelo, String imagen, String patente, Boolean terceros, Integer añoFabricación) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
        this.patente = patente;
        this.terceros = terceros;
        this.añoFabricación = añoFabricación;
    }

    public Auto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public Boolean getTerceros() {
        return terceros;
    }

    public void setTerceros(Boolean terceros) {
        this.terceros = terceros;
    }

    public Integer getAñoFabricación() {
        return añoFabricación;
    }

    public void setAñoFabricación(Integer añoFabricación) {
        this.añoFabricación = añoFabricación;
    }

    public int getKm() {
        return kilometros;
    }

    public void setKm(int kilometros) {
        this.kilometros = kilometros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
