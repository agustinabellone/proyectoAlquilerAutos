package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;


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
    public ModelAndView mostrarFechasAlquiler(@RequestParam("id_auto") Long id_auto) {
        ModelMap modelo = new ModelMap();
        modelo.put("id_auto", id_auto);
        return new ModelAndView("alquilarAutoFechasDisponibles", modelo);
    }

    @RequestMapping(path = "/confirmacion", method = RequestMethod.POST)
    public ModelAndView mostrarConfirmacionAlquiler(@RequestParam("salida") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate salida,
                                                    @RequestParam("ingreso") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate ingreso) {
        ModelMap modelo = new ModelMap();
        modelo.put("salida", salida);
        modelo.put("ingreso", ingreso);

        return new ModelAndView("alquilarAutoConfirmacion", modelo);
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
