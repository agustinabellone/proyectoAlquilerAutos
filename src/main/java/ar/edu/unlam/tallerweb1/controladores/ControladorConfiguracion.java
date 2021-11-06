package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ControladorConfiguracion {

    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorConfiguracion(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }


    @RequestMapping(path = "/configuracion", method = RequestMethod.GET)
    public ModelAndView mostrarConfiguracion(HttpServletRequest request) {
        Long id_usuario = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(id_usuario);
        ModelMap modelo = new ModelMap();
        modelo.put("usuario", usuario);
        return new ModelAndView("configuracion", modelo);
    }

}
