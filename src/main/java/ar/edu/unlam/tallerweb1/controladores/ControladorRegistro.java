package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;


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


        return registroBasicoExitoso(datosRegistro,modelo);
    }

    @RequestMapping(path = "/cargar-documentacion", method = RequestMethod.POST)
    public ModelAndView cargarDocumentacion(HttpServletRequest request,
                                            @RequestParam("licenciaFrente") MultipartFile licenciaFrente,
                                            @RequestParam("licenciaDorso") MultipartFile licenciaDorso) {

        ModelMap modelo=new ModelMap();


      /*  if(!licenciaFrente.isEmpty() && !licenciaDorso.isEmpty()) {
            // Subir ruta de archivo
            String path = request.getRealPath("/images/");
            // Subir nombre de archivo
            String filename = licenciaFrente.getOriginalFilename();
            File filepath = new File(path,filename);

            String filename2 = licenciaDorso.getOriginalFilename();
            File filepath2 = new File(path,filename2);
            // Juzga si la ruta existe, crea una si no existe
            if (!filepath.getParentFile().exists() && !filepath2.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
                filepath2.getParentFile().mkdirs();
            }
            // Guardar el archivo cargado en un archivo de destino
            try {
                licenciaFrente.transferTo(new File(path + File.separator + filename));
                licenciaFrente.transferTo(new File(path + File.separator + filename2));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/



        return registroExitoso();
    }





    private ModelAndView registroBasicoExitoso(DatosRegistro datosRegistro, ModelMap modelo) {
        modelo.put("datos", datosRegistro);
        return new ModelAndView("cargarRegistro",modelo);
    }



    private ModelAndView registroExitoso() {
        return new ModelAndView("redirect:/enviarMail");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("registro", modelo);
    }

}