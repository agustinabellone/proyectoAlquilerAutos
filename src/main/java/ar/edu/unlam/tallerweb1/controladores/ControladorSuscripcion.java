package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.repositorios.TablaSuscripcion;
import org.springframework.web.servlet.ModelAndView;

public class ControladorSuscripcion {

    private TablaSuscripcion tablaSuscripcion= TablaSuscripcion.getInstance();

    public ModelAndView suscribirCliente(DatosSuscripcion datosSuscripcion) {

        if(tablaSuscripcion.existeSuscripcionCon(datosSuscripcion.getCliente()))
        {
            return new ModelAndView("ir-a-suscribir");
        }

        tablaSuscripcion.agregar(new Suscripcion(datosSuscripcion));

        return new ModelAndView("home");
    }
}
