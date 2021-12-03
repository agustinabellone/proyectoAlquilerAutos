package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
