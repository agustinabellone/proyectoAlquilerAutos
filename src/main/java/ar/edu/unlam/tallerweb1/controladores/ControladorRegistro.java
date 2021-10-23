package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorRegistro {

    private ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
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
        }
        catch (ClavesDistintasException e){
            return registroFallido(modelo, "Las claves deben ser iguales");
        } catch (ClaveLongitudIncorrectaException e){
            return registroFallido(modelo, "La clave debe tener al menos 8 caracteres");
        } catch (ClienteYaExisteException e){
            return registroFallido(modelo, "El usuario ya se encuentra registrado");
        }
        return registroExitoso();
    }

    private ModelAndView registroExitoso() {
        return new ModelAndView("redirect:/login");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("registro", modelo);
    }

}