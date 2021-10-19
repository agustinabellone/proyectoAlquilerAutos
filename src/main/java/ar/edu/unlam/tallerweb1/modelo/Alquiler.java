package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Alquiler {

    @Id
    private Long auto_id;
    private Long cliente_id;
    private String f_ingreso;
    private String f_regreso;
    private Long id;


    @ManyToOne
    private Auto auto;
    @OneToOne
    private Cliente cliente;

    private boolean activo;

    public Alquiler() {
    }

    public Alquiler(Auto auto) {
        this.auto=auto;
    }

    public Alquiler(Auto auto, Cliente cliente) {
        this.auto=auto;
        this.cliente=cliente;
    }

    public Alquiler(DatosAlquiler DA ){
        this.auto_id=DA.getAuto().getId();
        this.cliente_id=DA.getCliente().getId();
        this.f_ingreso=DA.getF_Inicio();
        this.f_regreso=DA.getF_Regreso();
        this.activo=true;

    }

    public Long getAuto_id() {
        return auto_id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public String getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(String f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public String getF_regreso() {
        return f_regreso;
    }

    public void setF_regreso(String f_regreso) {
        this.f_regreso = f_regreso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAuto_id(Long auto_id) {
        this.auto_id = auto_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }


}
