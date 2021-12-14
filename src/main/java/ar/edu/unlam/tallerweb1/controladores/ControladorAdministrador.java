package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ControladorAdministrador {
    private ServicioDeAuto servicioDeAuto;

    public ControladorAdministrador(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            List<Auto> autosAlquilados = servicioDeAuto.obtenerAutosAlquilados();
            model.put("autos_alquilados", autosAlquilados);
            return new ModelAndView("panel-principal", model);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-disponibles-para-alquilar")
    public ModelAndView mostrarAutosDisponiblesParaAlquilar(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            List<Auto> autosDisponiblesParaAlquilar = servicioDeAuto.obtenerAutosDisponiblesParaAlquilar();
            model.put("autos_disponibles_para_alquilar", autosDisponiblesParaAlquilar);
            return new ModelAndView("autos-disponibles-para-alquilar", model);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            List<Auto> enMantenimiento = null;
            try {
                enMantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
                model.put("autos_en_mantenimiento", enMantenimiento);
                return new ModelAndView("autos-en-mantenimiento", model);
            } catch (NoHayAutosEnMantenientoException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean esAdiministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }
}
