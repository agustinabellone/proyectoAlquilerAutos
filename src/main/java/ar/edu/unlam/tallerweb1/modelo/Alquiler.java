package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String f_ingreso;
    private String f_regreso;

    public Alquiler() {
    }

 //   public Alquiler(DatosAlquiler DA ){
   //     this.f_ingreso=DA.getF_Inicio();
     //   this.f_regreso=DA.getF_Regreso();
    //}

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
}
