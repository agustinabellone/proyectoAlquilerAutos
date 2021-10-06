package ar.edu.unlam.tallerweb1.modelo;

public class Usuario {
    private String rol;

    public Usuario(String rol){
        this.rol = rol;
    }
    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
