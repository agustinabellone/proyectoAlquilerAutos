package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAdmin {

    private String viewName;
    private String mensaje;
    private ModelMap modelMap = new ModelMap();
    private Mensaje claseDeMensajes = new Mensaje();

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
}
