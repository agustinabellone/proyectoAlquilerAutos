package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorios.TablaAlquiler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


public class ControladorAlquiler {

    private TablaAlquiler tablaAlquiler= TablaAlquiler.getInstance();

    @RequestMapping(path = "/alquilarAuto", method = RequestMethod.POST)
    public ModelAndView alquilarAuto(@ModelAttribute("datosAlquiler")DatosAlquiler datosAlquiler){
        //request.getSession().getAttribute("Rol");
        ModelMap model= new ModelMap();

        if(tablaAlquiler.existeAlquilerCon(datosAlquiler.getAuto())){

            return new ModelAndView("ir-alquiler-auto"); //VISTA PLACEHOLDER

        }

        tablaAlquiler.agregar(new Alquiler(datosAlquiler));

        return new ModelAndView("home"); //VISTA PLACEHOLDER

    }
}
