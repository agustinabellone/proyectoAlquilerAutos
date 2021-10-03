package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorUsuarioAdministrador {
    public ModelAndView enviarAutoAManteniminento(Auto auto, String fechaInicial) {
        ModelMap model = new ModelMap();
        model.put("mensaje", "El auto se envio correctamente a mantenimiento");
        return new ModelAndView("mantenimiento", model);
    }
}
