package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAdmin {

    @RequestMapping(method = RequestMethod.GET, path = "ir-a-panel-principal")
    public ModelAndView irAlPanelPrincipal() {
        return new ModelAndView("panel-principal");
    }
}
