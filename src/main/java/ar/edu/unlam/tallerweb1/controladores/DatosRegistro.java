package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro {
    private String email;
    private String clave;
    private String repiteClave;

    public DatosRegistro(){}

    public DatosRegistro(String email, String clave, String repiteClave) {
        this.email = email;
        this.clave = clave;
        this.repiteClave = repiteClave;
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
}
