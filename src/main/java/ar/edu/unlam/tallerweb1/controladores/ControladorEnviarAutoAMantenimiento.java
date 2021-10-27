package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorEnviarAutoAMantenimiento {

    public ModelAndView enviarAutoAMantenimiento(Usuario conRolAdmin, Auto aEnviar) {
        ModelMap modelMap = new ModelMap();
        if (conRolAdmin.getRol() == "admin") {
            modelMap.put("mensaje", "Se envio un auto correctamente a mantenimiento");
            return new ModelAndView("lista-de-autos", modelMap);
        }else{
            modelMap.put("mensaje", "No tienes permisos para realizar esta accion");
            return new ModelAndView("home", modelMap);
        }
    }
}
