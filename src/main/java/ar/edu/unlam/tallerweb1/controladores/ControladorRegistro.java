package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorRegistro {

    private ServicioRegistro servicioRegistro;
    private ServicioMail servicioMail;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro, ServicioMail servicioMail) {
        this.servicioRegistro = servicioRegistro;
        this.servicioMail=servicioMail;
    }

    @RequestMapping(path = "/registro", method = RequestMethod.GET)
    public ModelAndView mostrarFormularioDeRegistro() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro", modelo);
    }

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {

        ModelMap modelo = new ModelMap();

        try {
            servicioRegistro.registrar(datosRegistro);
            servicioMail.enviarMail("confirme su correo aqui para verificar que se yo...","confirmar mail registro",datosRegistro.getEmail());
        }
        catch (ClavesDistintasException e){
            return registroFallido(modelo, "Las claves deben ser iguales");
        } catch (ClaveLongitudIncorrectaException e){
            return registroFallido(modelo, "La clave debe tener al menos 8 caracteres");
        } catch (ClienteYaExisteException e){
            return registroFallido(modelo, "El usuario ya se encuentra registrado");
        }


        return new ModelAndView ("aviso-confirmar-mail");
    }




    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("registro", modelo);
    }
}
