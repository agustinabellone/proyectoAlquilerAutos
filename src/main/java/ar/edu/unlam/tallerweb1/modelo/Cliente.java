package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String clave;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(){}

    public Cliente(DatosRegistro datosRegistro) {
        this.email = datosRegistro.getEmail();
        this.clave = datosRegistro.getClave();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String contraseña) {
        this.clave = clave;
    }
}
