package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorMecanico {

    private ServicioDeAuto servicio;

    @Autowired
    public ControladorMecanico(ServicioDeAuto servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-pantalla-principal")
    public ModelAndView irAPantallaPrincipal(HttpServletRequest mecanico) {
        ModelMap model = new ModelMap();
        if (mecanico.getSession().getAttribute("rol") != null && mecanico.getSession().getAttribute("rol").equals("mecanico")) {
            model.put("nombre", mecanico.getSession().getAttribute("nombre"));
            return new ModelAndView("redirect:/pantalla-principal");
        }
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pantalla-principal")
    public ModelAndView mostrarAutosParaMantenimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (request.getSession().getAttribute("rol") != null && request.getSession().getAttribute("rol").equals("mecanico")) {
            try {
                List<Auto> paraMantenimiento = servicio.obtenerAutosEnMantenimiento();
                model.put("lista_autos_mantenimiento", paraMantenimiento);
                return new ModelAndView("pantalla-principal-mecanico", model);
            } catch (NoHayAutosEnMantenientoException e) {
                e.printStackTrace();
            }
        }
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", model);
    }
}
