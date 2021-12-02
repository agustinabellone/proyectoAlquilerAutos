package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoSuscripcion {

    @Id
    private Long id;
    @Column
    private String descripcion;
    @Column
    private String nombre;
    @Column
    private Long duracion;
    @Column
    private Float precio;
    @Column
    private Float limiteKilometraje;
    @Column
    private Float valorPorKmAdicional;
    @Column
    private Float valorPorMalasCondiciones;
    @Column
    private Boolean eleccionVehiculo;
    @Column
    private Boolean permiteReserva;
    @Column
    private Float valorIncumplimientoHoraLugar;

    public TipoSuscripcion(Long id, String descripcion, String nombre) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public TipoSuscripcion() {
    }

    public TipoSuscripcion(Long id_tipo) {
        this.id = id_tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getLimiteKilometraje() {
        return limiteKilometraje;
    }

    public void setLimiteKilometraje(Float limiteKilometraje) {
        this.limiteKilometraje = limiteKilometraje;
    }

    public Float getValorPorKmAdicional() {
        return valorPorKmAdicional;
    }

    public void setValorPorKmAdicional(Float valorPorKmAdicional) {
        this.valorPorKmAdicional = valorPorKmAdicional;
    }

    public Boolean getEleccionVehiculo() {
        return eleccionVehiculo;
    }

    public void setEleccionVehiculo(Boolean eleccionVehiculo) {
        this.eleccionVehiculo = eleccionVehiculo;
    }

    public Boolean getPermiteReserva() {
        return permiteReserva;
    }

    public void setPermiteReserva(Boolean permiteReserva) {
        this.permiteReserva = permiteReserva;
    }

    public Float getValorIncumplimientoHoraLugar() {
        return valorIncumplimientoHoraLugar;
    }

    public void setValorIncumplimientoHoraLugar(Float valorIncumplimientoHoraLugar) {
        this.valorIncumplimientoHoraLugar = valorIncumplimientoHoraLugar;
    }

    public Float getValorPorMalasCondiciones() {
        return valorPorMalasCondiciones;
    }

    public void setValorPorMalasCondiciones(Float valorPorMalasCondiciones) {
        this.valorPorMalasCondiciones = valorPorMalasCondiciones;
    }
}
