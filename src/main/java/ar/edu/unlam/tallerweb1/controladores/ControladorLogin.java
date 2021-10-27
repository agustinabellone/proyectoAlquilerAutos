package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.Exceptions.PasswordIncorrectaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario) {
        this.servicioLogin = servicioLogin;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView mostrarFormularioDeLogin(HttpServletRequest request) {

        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView ingresar(@ModelAttribute("datosLogin") DatosLogin datosLogin,
                                 HttpServletRequest request) {
        ModelMap modelo = new ModelMap();

        try {
            servicioLogin.ingresar(datosLogin);
        }
        catch (ClienteNoExisteException e) {
            return registroFallido(modelo, "El usuario no existe");
        }catch (PasswordIncorrectaException e) {
            return registroFallido(modelo, "Datos incorrectos");
        }

        iniciarSesion(servicioUsuario.buscarPorEmail(datosLogin.getEmail()), request);

        return registroExitoso();
    }

    @RequestMapping(path = "/logout")
    public ModelAndView cerrarSesion(HttpServletRequest request) {

        if (request.getSession().getAttribute("id")!=null){
            request.getSession().setAttribute("id",null);
            request.getSession().setAttribute("rol",null);
        }

        return new ModelAndView("redirect:/home");

    }

    private void iniciarSesion(Usuario buscado, HttpServletRequest request) {

        // EL SWITCH ES UTIL SI LOS 4 ROLES TIENEN DISTINTOS DATOS QUE GUARDAR, POR EL MOMENTO NO LOS TIENEN
        /*
        switch (buscado.getRol()){
            case "cliente":
                request.getSession().setAttribute("rol", "cliente");
                request.getSession().setAttribute("id", buscado.getId());
                break;
            case "admin":
                request.getSession().setAttribute("rol", "admin");
                request.getSession().setAttribute("id", buscado.getId());
                break;
            case "encargado":
                request.getSession().setAttribute("rol", "encargado");
                request.getSession().setAttribute("id", buscado.getId());
                break;
            default:
                break;
        }*/

        request.getSession().setAttribute("rol", buscado.getRol());
        request.getSession().setAttribute("id", buscado.getId());
        request.getSession().setAttribute("nombre", buscado.getNombre());

    }

    private ModelAndView registroExitoso() {

        return new ModelAndView("redirect:/main");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("login", modelo);
    }
}



