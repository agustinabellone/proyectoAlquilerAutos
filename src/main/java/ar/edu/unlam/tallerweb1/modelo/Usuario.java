package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rol;


    @Column
    private String nombre;

    @Column
    private String dni;

    @Column
    private String telefono;

    @Column
    private String email;

    @Column
    private String clave;


    public Usuario(String rol) {
        this.rol = rol;
    }

    public Usuario() {

    }

    public Usuario(DatosRegistro datosRegistro) {
        this.email = datosRegistro.getEmail();
        this.clave = datosRegistro.getClave();
        this.nombre = datosRegistro.getNombre();
    }

    public Usuario(Long id){
        this.id=id;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
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

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
