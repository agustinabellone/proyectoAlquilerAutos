package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDevolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorDevolucion {

    private ServicioDevolucion servicioDevolucion;

    @Autowired
    public ControladorDevolucion(ServicioDevolucion servicioDevolucion) {
        this.servicioDevolucion = servicioDevolucion;
    }

    public ControladorDevolucion() {
    }


    //<<<<METODOS NECESARIOS QUE SON DE ALQUILER>>>>///

   /* @RequestMapping("/obtenerAlquileres")
    public ModelAndView obtenerAlquileresDeUsuario() {

    }*/

    @RequestMapping("/probando")
    public ModelAndView irAMain() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("main");
    }

    @RequestMapping("/finalizarAlquiler")
    public ModelAndView devolver(Alquiler alquiler) {
        ModelMap model = new ModelMap();
        if(alquiler.getId()!=null) {
            servicioDevolucion.finalizarAlquiler(alquiler);
            return new ModelAndView("alquilerFinalizado");
        } else
            model.put("Error", "Error de Devolucion");
        return new ModelAndView("errorDevolucion", model);
    }



}

