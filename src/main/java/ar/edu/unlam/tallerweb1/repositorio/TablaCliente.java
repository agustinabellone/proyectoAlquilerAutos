package ar.edu.unlam.tallerweb1.repositorio;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

import java.util.HashMap;
import java.util.Map;

public class TablaCliente {

    private static final TablaCliente instance = new TablaCliente();
    private Map<String, Cliente> tablaCliente = new HashMap<>();

    private TablaCliente(){}

    public static TablaCliente getInstance() {
        return instance;
    }

    public boolean existeClienteCon(String email){
        return this.tablaCliente.containsKey(email);
    }

    public void agregar(Cliente cliente){
        this.tablaCliente.put(cliente.getEmail(), cliente);
    }

    public void reset(){
        this.tablaCliente.clear();
    }
}