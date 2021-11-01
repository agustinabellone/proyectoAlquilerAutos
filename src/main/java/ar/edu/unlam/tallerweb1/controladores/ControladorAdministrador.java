package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAdministrador {

    public ModelAndView irALaVistaPrincipal() {
        ModelMap model = new ModelMap();
        model.put("error","No tienes los permisos necesarios");
        return new ModelAndView("redirect:/login",model);
    }
}
