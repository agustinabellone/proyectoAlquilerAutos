package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorHome {

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView mostrarHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView mostrarMain(HttpServletRequest request) {

            if(request.getSession().getAttribute("id")!=null){
                return new ModelAndView("main");
            }

        return new ModelAndView("redirect:/home");
    }

}
