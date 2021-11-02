package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorAdministrador {

    private ModelAndView modelAndView = new ModelAndView();

    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)){
            modelAndView.addObject("mensaje","Bienvenido!!!");
            modelAndView.setViewName("redirect:/panel-principal");
        } else{
            modelAndView.addObject("error","No tienes los permisos necesarios");
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }
}
