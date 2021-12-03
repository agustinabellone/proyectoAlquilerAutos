package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuario {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorUsuario (ServicioSuscripcion servicioSuscripcion,ServicioUsuario servicioUsuario){
        this.servicioSuscripcion=servicioSuscripcion;
        this.servicioUsuario= servicioUsuario;
    }

    @RequestMapping(path = "/ir-a-perfil")
    private ModelAndView mostrarPerfil(HttpServletRequest request){

        if(request.getSession().getAttribute("rol").equals("cliente")){
            return new ModelAndView("perfil");
        }
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/reactivar-cuenta")
    private ModelAndView reactivarCuenta( @RequestParam("emailUsuario") String email){
        Usuario usuario = servicioUsuario.buscarPorEmail(email);
        servicioUsuario.reactivarCuenta(usuario);
        ModelMap modelo = new ModelMap();
        modelo.put("cuentaReactivada", "¡Cuenta Reactivada con éxito! Ya podés iniciar sesión.");
        return new ModelAndView("redirect:/login",modelo);
    }
}
