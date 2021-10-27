package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuario {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorUsuario (ServicioSuscripcion servicioSuscripcion){
        this.servicioSuscripcion=servicioSuscripcion;
    }

    @RequestMapping(path = "/ir-a-perfil")
    private ModelAndView mostrarPerfil(HttpServletRequest request){

        // ESTO SE CAMBIA CUANDO HAGAMOS EL MANEJO DE SESIONES
        request.getSession().setAttribute("rol", "cliente");
        request.getSession().setAttribute("id_usuario", 200);
        request.getSession().setAttribute("nombre_usuario", "Ian");
        //////////////////////////////////////////////////////

        if(request.getSession().getAttribute("rol").equals("cliente")){
            return new ModelAndView("perfil");
        }
        return new ModelAndView("/home.jsp");
    }
}