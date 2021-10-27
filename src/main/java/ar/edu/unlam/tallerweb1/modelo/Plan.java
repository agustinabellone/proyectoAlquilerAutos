package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Plan {
        //CLASE ENUM
    @Id
    private Integer id;
    private String descripcion;

}
