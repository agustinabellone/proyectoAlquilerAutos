package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
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
    private ServicioHome servicioHome;

    @Autowired
    public ControladorHome(ServicioAlquiler servicioAlquiler, ServicioUsuario servicioUsuario, ServicioHome servicioHome) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioUsuario=servicioUsuario;
        this.servicioHome=servicioHome;
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
                List <String> fechasAlquileresUsuario = servicioHome.obtenerFechasEnString(alquileresUsuario);
                List <Marca> autosMarca = servicioHome.obtenerMarcaAutoAlquiler(alquileresUsuario);
                List <Modelo> autosModelo = servicioHome.obtenerModeloAutoAlquiler(alquileresUsuario);
                model.put("alquileres", alquileresUsuario);
                model.put("fechas", fechasAlquileresUsuario);
                model.put("modelos", autosModelo);
                model.put("marcas", autosMarca);
                return new ModelAndView("main", model);
            }

        return new ModelAndView("redirect:/home");
    }

}
