package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {

    private String email;
    private String clave;

    public DatosLogin(){}

    public DatosLogin(String email, String clave) {
        this.email = email;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
