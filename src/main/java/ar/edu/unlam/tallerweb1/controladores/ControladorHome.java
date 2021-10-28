package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorHome {

    private ServicioAlquiler servicioAlquiler;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorHome(ServicioAlquiler servicioAlquiler, ServicioUsuario servicioUsuario) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioUsuario=servicioUsuario;
    }


    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView mostrarHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView mostrarMain(HttpServletRequest request) {
        ModelMap model = new ModelMap();
            if(request.getSession().getAttribute("id")!=null){
                Usuario usuario = servicioUsuario.buscarPorId((Long) request.getSession().getAttribute("id"));
                List<Alquiler> alquileresUsuario = servicioAlquiler.obtenerAlquileresDeUsuario(usuario);
                model.put("alquileres", alquileresUsuario);
                return new ModelAndView("main", model);
            }

        return new ModelAndView("redirect:/home");
    }

}
