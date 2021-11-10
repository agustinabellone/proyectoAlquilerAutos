package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(path = "/eliminar-cuenta", method = RequestMethod.GET)
    public ModelAndView eliminarCuenta(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        Long id_usuario = (Long) request.getSession().getAttribute("id");
        servicioUsuario.eliminarUsuario(id_usuario);
        modelo.put("mensaje", "Usuario eliminado con éxito.");
        return new ModelAndView("configuracion", modelo);
    }

    @RequestMapping(path = "/guardar-cambios", method = RequestMethod.GET)
    public ModelAndView guardarCambios(HttpServletRequest request,
                                       @RequestParam("nombre") String nombre,
                                       @RequestParam("password") String contraseña) {
        ModelMap modelo = new ModelMap();

        try {
            Long id_usuario = (Long) request.getSession().getAttribute("id");
            servicioUsuario.actualizarUsuario(id_usuario, nombre, contraseña);
            modelo.put("cambiosActualizados", "Datos actualizados con éxito.");
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("contraseña", contraseña);
            return new ModelAndView("configuracion", modelo);
        }
        catch(ClaveLongitudIncorrectaException e) {
            modelo.put("error", "La clave debe tener al menos 8 caracteres.");
            return new ModelAndView("configuracion", modelo);
        }
    }

}
