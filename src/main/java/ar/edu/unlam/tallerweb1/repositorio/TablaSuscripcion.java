package ar.edu.unlam.tallerweb1.repositorio;

import java.util.HashMap;
import java.util.Map;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;

public class TablaSuscripcion {

    private static final TablaSuscripcion instance = new TablaSuscripcion();
    private Map<Long, Suscripcion> TablaSuscripcion = new HashMap<>();

    //EN ESTA TABLA, IDENTIFICAMOS UNA SUSCRIPCION CON EL Id DEL CLIENTE QUE SE SUSCRIBE, POR EL MOMENTO

    private TablaSuscripcion() {

    }

    public static TablaSuscripcion getInstance() {

        return instance;
    }

    // COMPROBAMOS SI UN CLIENTE POSEE UN SUSCRIPCION
    public boolean existeSuscripcionCon(Cliente cliente) {

        return this.TablaSuscripcion.containsKey(cliente.getId());
    }

    // AGREGAMOS UNA SUSCRIPCION NUEVA
    public void agregar(Suscripcion suscripcion) {

        this.TablaSuscripcion.put(suscripcion.getCliente_id(), suscripcion);
    }

    public void reset() {

        this.TablaSuscripcion.clear();
    }

    // BUSCAMOS UNA SUSCRIPCION DE UN CLIENTE EN ESPECIFICO
    public Suscripcion buscoSuscripcion(Cliente cliente) {

        if(existeSuscripcionCon(cliente)) {
            return TablaSuscripcion.get(cliente.getId());
        }

        return null;
    }
}
