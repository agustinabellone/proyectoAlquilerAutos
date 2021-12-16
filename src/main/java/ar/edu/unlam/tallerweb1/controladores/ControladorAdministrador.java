package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosParaRevision;
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
            try {
                List<Auto> autosAlquilados = servicioDeAuto.obtenerAutosAlquilados();
                model.put("autos_alquilados", autosAlquilados);
                return new ModelAndView("panel-principal", model);
            } catch (NoHayAutosAlquiladosException e) {
                model.put("error_no_hay_alquilados", "No hay autos alquilados actualmente");
                return new ModelAndView("panel-principal", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-disponibles-para-alquilar")
    public ModelAndView mostrarAutosDisponiblesParaAlquilar(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> autosDisponiblesParaAlquilar = servicioDeAuto.obtenerAutosDisponiblesParaAlquilar();
                model.put("autos_disponibles_para_alquilar", autosDisponiblesParaAlquilar);
                return new ModelAndView("autos-disponibles-para-alquilar", model);
            } catch (NoHayAutosDisponiblesException e) {
                model.put("error_no_hay_disponibles", "No hay autos disponibles para alquilar actualmente");
                return new ModelAndView("autos-disponibles-para-alquilar", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> enMantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
                model.put("autos_en_mantenimiento", enMantenimiento);
                return new ModelAndView("autos-en-mantenimiento", model);
            } catch (NoHayAutosEnMantenientoException e) {
                model.put("error_no_hay_en_mantenimiento", "No hay autos para mantenimiento actualmente");
                return new ModelAndView("autos-en-mantenimiento", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-en-revision")
    public ModelAndView mostrarAutosEnRevision(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> enRevision = servicioDeAuto.obtenerAutosEnRevision();
                model.put("autos_en_revision", enRevision);
                return new ModelAndView("autos-en-revision", model);
            } catch (NoHayAutosParaRevision e) {
                model.put("error_no_hay_en_revision", "No hay autos para revision actualmente");
                return new ModelAndView("autos-en-revision", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    private boolean esAdiministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private ModelAndView enviarAlLoginConUnMensajeDeError(ModelMap model) {
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", model);
    }
}
