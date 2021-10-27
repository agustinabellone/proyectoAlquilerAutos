package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorEnviarAutoAMantenimiento {

    public ModelAndView enviarAutoAMantenimiento(HttpServletRequest request, Auto aEnviar) {
        ModelMap modelMap = new ModelMap();
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)) {
            modelMap.put("mensaje", "Se envio un auto correctamente a mantenimiento");
            return new ModelAndView("lista-de-autos", modelMap);
        } else {
            modelMap.put("mensaje", "No tienes permisos para realizar esta accion");
            return new ModelAndView("home", modelMap);
        }
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") == "admin";
    }
}
