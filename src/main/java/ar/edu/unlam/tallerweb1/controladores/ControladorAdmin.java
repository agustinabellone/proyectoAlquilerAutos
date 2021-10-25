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
    private ModelMap model = new ModelMap();

    @RequestMapping(method = RequestMethod.GET, path = "/administrador")
    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) {
            viewName = "panel-principal";
            model.put("mensaje-de-bienvenida","Bienvenido!!!");
        } else {
            viewName = "home";
            model.put("mensaje-de-error","No tienes los permisos necesarios para acceder a esta pagina.");
        }
        return new ModelAndView(viewName,model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-lista-de-autos")
    public ModelAndView irALaListaDeAutos(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) viewName = "lista-de-autos";
        else viewName = "home";
        return new ModelAndView(viewName);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrar-auto")
    public ModelAndView irARegistrarUnNuevoAuto(HttpServletRequest request) {
        if (elUsuarioEsAdministrador(request)) viewName = "registrar-auto";
        else viewName = "home";
        return new ModelAndView(viewName);
    }

    private boolean elUsuarioEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("ADMIN");
    }
}
