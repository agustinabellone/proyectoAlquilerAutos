package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorUsuarioAdministrador {
    public ModelAndView enviarAutoAManteniminento(Auto auto, String fechaInicial, Usuario administrador) {
        ModelMap model = new ModelMap();
        if (administrador.getRol() == "Admin") {
            model.put("mensaje", "El auto se envio correctamente a mantenimiento");
            return new ModelAndView("mantenimiento", model);
        }
    }
}
