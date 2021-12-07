package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteNoExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorUsuario {

    @Autowired
    private ServicioMail servicioMail;

    @Autowired
    public ControladorUsuario (ServicioMail servicioMail){
       this.servicioMail = servicioMail;
    }

    @RequestMapping(path = "/reactivar-cuenta")
    private ModelAndView reactivarCuenta( @RequestParam("emailUsuario") String email){
        servicioMail.enviarMailActivarCuenta(email);
        return new ModelAndView("cuenta-reactivada");
    }


}
