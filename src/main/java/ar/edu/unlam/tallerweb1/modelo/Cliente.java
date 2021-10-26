package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    private Long id;

    public Cliente(Long id) {
        this.id=id;
    }

    public Cliente(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
