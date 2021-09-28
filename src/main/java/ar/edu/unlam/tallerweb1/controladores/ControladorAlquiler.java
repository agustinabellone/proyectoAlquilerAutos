package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorio.TablaAlquiler;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


public class ControladorAlquiler {

    private TablaAlquiler tablaAlquiler= TablaAlquiler.getInstance();

    public ModelAndView alquilarAuto(DatosAlquiler datosAlquiler){

        ModelMap model= new ModelMap();

        if(tablaAlquiler.existeAlquilerCon(datosAlquiler.getAuto())){

            return new ModelAndView("ir-alquiler-auto"); //VISTA PLACEHOLDER

        }

        tablaAlquiler.agregar(new Alquiler(datosAlquiler));

        return new ModelAndView("home"); //VISTA PLACEHOLDER

    }
}
