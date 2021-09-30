package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Alquiler {

    private Long auto_id;
    private Long cliente_id;
    private String f_ingreso;
    private String f_regreso;
    private Long id;

    public Alquiler() {
    }

    public Alquiler(DatosAlquiler DA ){

        this.auto_id=DA.getAuto().getId();
        this.cliente_id=DA.getCliente().getId();
        this.f_ingreso=DA.getF_Inicio();
        this.f_regreso=DA.getF_Regreso();

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

    @Id
    public Long getId() {
        return id;
    }
}
