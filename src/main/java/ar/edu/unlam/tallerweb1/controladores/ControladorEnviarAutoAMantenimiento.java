package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaExistente;
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
    ModelMap model = new ModelMap();
    private String viewName;

    @Autowired
    public ControladorEnviarAutoAMantenimiento(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/enviar-a-mantenimiento")
    public ModelAndView enviarAutoAMantenimiento(HttpServletRequest request, Long idDelAuto) {
        if (elUsuarioTieneRolDeAdministrador(request) && elRolEstaSeteado(request)) {
            try {
                Auto existente = servicioDeAuto.buscarAutoPorId(idDelAuto);
                servicioDeAuto.enviarAutoMantenimiento(existente);
                model.put("mensaje", "Se envio correctamente un auto a mantenimiento");
                viewName = "lista-de-autos";
            } catch (AutoNoExistente e) {
                model.put("mensaje", "No existe el auto que queres mandar");
                viewName = "lista-de-autos";
            } catch (AutoYaExistente e) {
                model.put("mensaje", "No existe el auto que queres mandar");
                viewName = "lista-de-autos";
            }
        } else {
            model.put("mensaje", "No tenes permiso para realizar esta accion");
            viewName = "home";
        }
        return new ModelAndView(viewName, model);
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elUsuarioTieneRolDeAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") == "admin";
    }

}
