package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public class DatosSuscripcion {

    private Cliente cliente;
    private TipoSuscripcion tipoSuscripcion;


    public DatosSuscripcion(Cliente cliente, TipoSuscripcion tipoSuscripcion) {
        this.cliente=cliente;
        this.tipoSuscripcion=tipoSuscripcion;
    }

    public DatosSuscripcion() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcion tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }
}
