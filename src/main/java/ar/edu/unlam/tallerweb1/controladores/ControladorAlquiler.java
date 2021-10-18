package ar.edu.unlam.tallerweb1.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorAlquiler {

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

}