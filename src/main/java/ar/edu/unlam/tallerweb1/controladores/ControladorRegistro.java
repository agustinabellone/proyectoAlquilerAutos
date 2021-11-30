package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


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
        String md5=crearMd5(datosRegistro.getEmail());

        try {
            servicioRegistro.registrar(datosRegistro,md5);
            servicioMail.enviarMailRegistro(datosRegistro.getEmail(),md5);
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


    @RequestMapping(path = "/validar-mail", method = RequestMethod.GET)
    public ModelAndView confirmarMail(@RequestParam(value = "email") String email,
                                      @RequestParam(value = "hash") String hash) {

        ModelMap modelo = new ModelMap();

        try {
            servicioMail.verificarHash(email, hash);
        }
        catch (HashIncorrecto e) {
            modelo.put("error", "Intentá verificar el email nuevamente");
            return new ModelAndView("registro", modelo);
        }
        return new ModelAndView("cuenta-verificada");


    }

    @RequestMapping(path = "/cuenta-verificada", method = RequestMethod.GET)
    public ModelAndView mostrarCuentaVerificada() {
        return new ModelAndView("cuenta-verificada");
    }



    private String crearMd5(String mail){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(mail.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
