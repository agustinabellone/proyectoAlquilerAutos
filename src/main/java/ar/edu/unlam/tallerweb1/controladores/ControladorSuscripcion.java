package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSuscripcion {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorSuscripcion (ServicioSuscripcion servicioSuscripcion){
        this.servicioSuscripcion=servicioSuscripcion;
    }


    @RequestMapping(path = "/ir-a-suscribir", method = RequestMethod.GET)
    private ModelAndView mostrarFormularioSuscripcion(){
        ModelMap modelo= new ModelMap();
        modelo.put("datosSuscripcion", new DatosSuscripcion());
        return new ModelAndView("ir-a-suscribir",modelo);
    }

    @RequestMapping(path = "/suscribirse", method = RequestMethod.POST)
    public ModelAndView suscribirCliente(DatosSuscripcion datosSuscripcion) {

        try{
            servicioSuscripcion.suscribir(datosSuscripcion);
        }catch (ClienteYaSuscriptoException e){
            return new ModelAndView("ir-a-suscribir");
        }

        return new ModelAndView("home");
    }
}
