package ar.edu.unlam.tallerweb1.repositorios;

import java.util.HashMap;
import java.util.Map;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;

public class TablaAlquiler {

    private static final TablaAlquiler instance = new TablaAlquiler();
    private Map<Long, Alquiler> TablaAlquiler = new HashMap<>();

    //EN ESTA TABLA, IDENTIFICAMOS UN ALQUILER CON EL MISMO AUTO QUE CONTIENE, POR EL MOMENTO

    private TablaAlquiler() {

    }

    public static TablaAlquiler getInstance() {

        return instance;
    }

    public boolean existeAlquilerCon(Auto auto) {

        return this.TablaAlquiler.containsKey(auto.getId());
    }

    public void agregar(Alquiler alquiler) {

        this.TablaAlquiler.put(alquiler.getAuto_id(), alquiler);
    }

    public void reset() {

        this.TablaAlquiler.clear();
    }

    public Alquiler buscoAlquiler(Auto auto) {

        if(existeAlquilerCon(auto)) {
            return TablaAlquiler.get(auto.getId());
        }

        return null;
    }
}
