package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@EnableScheduling
@Controller
public class ControladorSuscripcion {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorSuscripcion (ServicioSuscripcion servicioSuscripcion){
        this.servicioSuscripcion=servicioSuscripcion;
    }


    @RequestMapping(path = "/ir-a-suscribir", method = RequestMethod.GET)
    private ModelAndView mostrarFormularioSuscripcion(HttpServletRequest request){

        // ESTO SE CAMBIA CUANDO HAGAMOS EL MANEJO DE SESIONES
        request.getSession().setAttribute("nombre_usuario", "Ian");
        //////////////////////////////////////////////////////

        if(request.getSession().getAttribute("rol").equals("cliente")){
            return new ModelAndView("ir-a-suscribir");
        }
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/confirmar-suscripcion", method = RequestMethod.GET)
    private ModelAndView confirmarSuscripcion(HttpServletRequest request,
                                               @RequestParam (value="id_tipo") Long id_tipo ){
        ModelMap model= new ModelMap();
        model.put("id_tipo", id_tipo);
        model.put("id_usuario", request.getSession().getAttribute("id"));
        return new ModelAndView("confirmar-suscripcion", model);
    }

    @RequestMapping(path = "/suscribirse", method = RequestMethod.GET)
    public ModelAndView suscribirUsuario(@RequestParam(value = "id_tipo") Long id_tipo,
                                         @RequestParam(value = "id_usuario")Long id_usuario) {

        //ACA ES NECESARIO BUSCAR EL USUARIO Y EL TIPO EN LA BD
        Usuario usuario = new Usuario(id_usuario);
        TipoSuscripcion tipoSuscripcion = new TipoSuscripcion(id_tipo);
        try{
            servicioSuscripcion.suscribir(usuario, tipoSuscripcion);
        }catch (ClienteYaSuscriptoException e){
            return new ModelAndView("ir-a-suscribir");
        }

        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/renovar-suscripcion", method = RequestMethod.POST)
    public ModelAndView renovarSuscripcion(@RequestParam("id") Long id) {


        try{
            servicioSuscripcion.renovarAutomaticamenteSuscripcion(id);
        }catch(SuscripcionYaRenovadaException e){
            return new ModelAndView("perfil"); //EL USUARIO RENUEVA SU SUSCRIPCION DESDE SU PERFIL
        }
        return new ModelAndView("home");
    }

    //@Scheduled(cron = "0 29 19 ? * *")
    //@Scheduled(fixedRate = 10000)
    public void controlDeFechaDeSuscripciones(){
        //System.out.println("Fixed rate task ");
        servicioSuscripcion.revisionDeSuscripciones();
    }
}
