package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorEnviarAutoAMantenimiento {

    private ServicioDeAuto servicioDeAuto;

    @Autowired
    public ControladorEnviarAutoAMantenimiento(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    public ControladorEnviarAutoAMantenimiento() {
    }


    @RequestMapping(method = RequestMethod.GET, path = "/enviar-a-mantenimiento")
    public ModelAndView enviarAutoAMantenimiento(HttpServletRequest request, Long idDelAuto) {
        ModelMap modelMap = new ModelMap();
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)) {
            Auto auto = servicioDeAuto.buscarAutoPorId(idDelAuto);
            modelMap.put("mensaje", "Se envio un auto correctamente a mantenimiento");
            return new ModelAndView("lista-de-autos", modelMap);
        } else {
            modelMap.put("mensaje", "No tienes permisos para realizar esta accion");
            return new ModelAndView("home", modelMap);
        }
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") == "admin";
    }
}
