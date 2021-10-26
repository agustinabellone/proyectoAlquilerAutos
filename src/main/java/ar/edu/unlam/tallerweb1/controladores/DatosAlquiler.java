package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
<<<<<<< HEAD
import ar.edu.unlam.tallerweb1.modelo.Cliente;
=======
import ar.edu.unlam.tallerweb1.modelo.Usuario;
>>>>>>> 8c3b25bc877e8f470b1ef06ad3d1dfdd09f50adb

public class DatosAlquiler {

    private Auto auto;
<<<<<<< HEAD
    private Cliente cliente;
    private String f_ingreso;
    private String f_regreso;

    public DatosAlquiler(Cliente cliente, Auto auto, String fechaInicio, String fechaRegreso) {

        this.auto=auto;
        this.cliente=cliente;
=======
    private Usuario usuario;
    private String f_ingreso;
    private String f_regreso;

    public DatosAlquiler(Usuario usuario, Auto auto, String fechaInicio, String fechaRegreso) {

        this.auto=auto;
        this.usuario = usuario;
>>>>>>> 8c3b25bc877e8f470b1ef06ad3d1dfdd09f50adb
        this.f_ingreso=fechaInicio;
        this.f_regreso=fechaRegreso;

    }

    public Auto getAuto() {
        return auto;
    }

<<<<<<< HEAD
    public Cliente getCliente() {
        return cliente;
    }

    public Long idCliente(){
        return cliente.getId();
    }

    public Long idAuto(){
        return auto.getId();
=======
    public Usuario getCliente() {
        return usuario;
>>>>>>> 8c3b25bc877e8f470b1ef06ad3d1dfdd09f50adb
    }

    public String getF_Inicio() {
        return this.f_ingreso;
    }

    public String getF_Regreso() {
        return this.f_regreso;
    }
}
