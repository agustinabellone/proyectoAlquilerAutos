package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro {
    private String email;
    private String clave;
    private String repiteClave;
    private String nombre;

    public DatosRegistro(){}

    public DatosRegistro(String email, String clave, String repiteClave) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
    }

    public DatosRegistro(String email, String clave, String repiteClave, String nombre) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
        this.nombre=nombre;
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

    public String getRepiteClave() {
        return repiteClave;
    }

    public void setRepiteClave(String repiteClave) {
        this.repiteClave = repiteClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
