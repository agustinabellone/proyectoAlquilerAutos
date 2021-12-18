package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;
    private ServicioUsuario servicioUsuario;
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario, ServicioSuscripcion servicioSuscripcion) {
        this.servicioLogin = servicioLogin;
        this.servicioUsuario = servicioUsuario;
        this.servicioSuscripcion=servicioSuscripcion;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView mostrarFormularioDeLogin(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView ingresar(@ModelAttribute("datosLogin") DatosLogin datosLogin,
                                 HttpServletRequest request) throws NoHayEmpladosException {
        ModelMap modelo = new ModelMap();

        try {
            servicioLogin.ingresar(datosLogin);
        } catch (ClienteNoExisteException e) {
            return registroFallido(modelo, "El usuario no existe");
        }catch (ClienteEstaInactivoException e) {
            modelo.put("usuarioInactivo", "El usuario se encuentra inactivo.");
            modelo.put("emailUsuario", datosLogin.getEmail());
            return new ModelAndView("login", modelo);
        }catch (PasswordIncorrectaException e) {
            return registroFallido(modelo, "Datos incorrectos");
        }catch(ClienteNoConfirmoEmail e){
            return registroFallido(modelo, "Confirme su email");
        }

        iniciarSesion(servicioUsuario.buscarPorEmail(datosLogin.getEmail()), request);

        return registroExitoso(request);
    }

    @RequestMapping(path = "/logout")
    public ModelAndView cerrarSesion(HttpServletRequest request) {

        if (request.getSession().getAttribute("id") != null) {
            request.getSession().setAttribute("id", null);
            request.getSession().setAttribute("rol", null);
            request.getSession().setAttribute("notificaciones", null);
        }

        return new ModelAndView("redirect:/home");

    }

    private void iniciarSesion(Usuario buscado, HttpServletRequest request) throws NoHayEmpladosException {

        request.getSession().setAttribute("rol", buscado.getRol());
        request.getSession().setAttribute("id", buscado.getId());
        request.getSession().setAttribute("nombre", buscado.getNombre());
        request.getSession().setAttribute("email", buscado.getEmail());
        request.getSession().setAttribute("tieneSuscripcion", servicioSuscripcion.existeSuscripcionPorUsuario(servicioUsuario.buscarPorId(buscado.getId())));

        List<Notificacion> notificaciones= servicioUsuario.getNotificacionesPorId(buscado);

        if(notificaciones.size() > 0){
            request.getSession().setAttribute("notificaciones", notificaciones);
        }else{
            request.getSession().setAttribute("notificaciones", null);
        }

    }

    private ModelAndView registroExitoso(HttpServletRequest request) {
        if (request.getSession().getAttribute("rol").equals("admin")){
            return new ModelAndView("redirect:/ir-a-panel-principal");
        }
        if (request.getSession().getAttribute("rol").equals("encargado")){
            return new ModelAndView("redirect:/ir-a-encargado-home");
        }
        if (request.getSession().getAttribute("rol").equals("empleado")){
            return new ModelAndView("empleados");
        }
        if (request.getSession().getAttribute("rol").equals("mecanico")){
            return new ModelAndView("redirect:/ir-a-pantalla-principal");
        }
        return new ModelAndView("redirect:/main");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("login", modelo);
    }
}



