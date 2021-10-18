package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorAlquiler {

    private ServicioAlquiler servicioAlquiler;

    @Autowired
    public ControladorAlquiler(ServicioAlquiler servicioAlquiler) {
        this.servicioAlquiler = servicioAlquiler;
    }

    @RequestMapping(path = "/alquilar-auto", method = RequestMethod.GET)
    public ModelAndView mostrarAlquilerAuto() {
        return new ModelAndView("alquilarAuto");
    }

    @RequestMapping(path = "/elegir-fechas", method = RequestMethod.GET)
    public ModelAndView mostrarFechasAlquiler() {
        return new ModelAndView("alquilarAutoFechasDisponibles");
    }

    @RequestMapping(path = "/alquiler-confirmación", method = RequestMethod.GET)
    public ModelAndView mostrarConfirmacionAlquiler() {
        return new ModelAndView("alquilarAutoConfirmacion");
    }

    @RequestMapping(path = "/validar-alquiler", method = RequestMethod.POST)
    public ModelAndView alquilarAuto(@ModelAttribute("datosAlquiler") DatosAlquiler datosAlquiler){
        ModelMap modelo = new ModelMap();

        try {
            servicioAlquiler.AlquilarAuto(datosAlquiler);
        }
        catch (AutoYaAlquiladoException e) {
            return alquilerFallido(modelo, "El auto ya fue alquilado.");
        }
        return alquilerExitoso();

    }

    private ModelAndView alquilerExitoso() {
        return new ModelAndView("pantallaAlquilerRealizado");
    }

    private ModelAndView alquilerFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("alquilarAutoConfirmacion", modelo);
    }
}
