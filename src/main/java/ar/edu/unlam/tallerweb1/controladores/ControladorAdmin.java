package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.Exceptions.NohayAutosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorAdmin {

    private String viewName;
    private String mensaje;
    private ModelMap modelMap = new ModelMap();
    private Mensaje claseDeMensajes = new Mensaje();
    private ServicioAdministrador servicio;

    @Autowired
    public ControladorAdmin(ServicioAdministrador servicio) {
        this.servicio = servicio;
    }

    public ControladorAdmin() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/administrador")
    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) {
            viewName = "panel-principal";
            mensaje = claseDeMensajes.getMensajeBienvenida();
        } else {
            viewName = "home";
            mensaje = claseDeMensajes.getMensajedeErrorSinPermisos();
        }
        modelMap.put("mensaje", this.mensaje);
        return new ModelAndView(viewName, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-lista-de-autos")
    public ModelAndView irALaListaDeAutos(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) {
            viewName = "lista-de-autos";
            obtenerListaDeAutosSinoLanzarUnaException();
        } else {
            viewName = "home";
            mensaje = claseDeMensajes.getMensajedeErrorSinPermisos();
        }
        modelMap.put("mensaje", this.mensaje);
        return new ModelAndView(viewName, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrar-auto")
    public ModelAndView irARegistrarUnNuevoAuto(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) {
            viewName = "registrar-auto";
        } else {
            viewName = "home";
            mensaje = claseDeMensajes.getMensajedeErrorSinPermisos();
        }
        modelMap.put("mensaje", this.mensaje);
        return new ModelAndView(viewName, modelMap);
    }

    private boolean elUsuarioEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("ADMIN");
    }

    private void obtenerListaDeAutosSinoLanzarUnaException() {
        try {
            List<Auto> autosObtenidos = servicio.obtenerTodosLoAutos();
            modelMap.put("lista-de-autos", autosObtenidos);
        } catch (NohayAutosException e) {
            mensaje = claseDeMensajes.getMensajeDeErrorSinAutos();
        }
    }
}
