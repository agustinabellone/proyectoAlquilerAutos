package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class ControladorAdmin {

    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        if (request.getSession().getAttribute("rol").equals("ADMIN")) {
            return new ModelAndView("panel-principal");
        }
        return new ModelAndView("home");
    }

}
