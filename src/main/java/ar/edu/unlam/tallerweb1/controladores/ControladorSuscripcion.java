package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaActivadaException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaCanceladaException;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@EnableScheduling
@Controller
public class ControladorSuscripcion {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorSuscripcion (ServicioSuscripcion servicioSuscripcion, ServicioUsuario servicioUsuario){
        this.servicioSuscripcion=servicioSuscripcion;
        this.servicioUsuario = servicioUsuario;
    }


    @RequestMapping(path = "/ir-a-suscribir", method = RequestMethod.GET)
    private ModelAndView mostrarFormularioSuscripcion(HttpServletRequest request){

        if(null != request.getSession().getAttribute("rol")){
            if(request.getSession().getAttribute("rol").equals("cliente")){
                if(!(Boolean)request.getSession().getAttribute("tieneSuscripcion")){
                    return new ModelAndView("ir-a-suscribir");
                }
            }
        }
        return new ModelAndView("redirect:/main");
    }

    @RequestMapping(path = "/confirmar-suscripcion-gratis", method = RequestMethod.GET)
    private ModelAndView mostrarConfirmacionSuscripcionGratuita(HttpServletRequest request){
        Long id_suscripcion = Long.valueOf(3);
        Long id_usuario = (Long)request.getSession().getAttribute("id");
        Usuario usuario = this.servicioUsuario.buscarPorId(id_usuario);
        TipoSuscripcion tipoSuscripcion= this.servicioSuscripcion.getTipoPorid(id_suscripcion);
        servicioSuscripcion.suscribir(usuario, tipoSuscripcion);
        request.getSession().setAttribute("tieneSuscripcion",true);
        servicioUsuario.restarPuntaje(1000, usuario);
        ModelMap modelo = new ModelMap();
        modelo.put("confirmacionSuscripcionGratuita","La suscripción fue dada de alta exitosamente");
        modelo.put("informacionPuntaje","Se te descontarán los 1000 puntos.");
        return new ModelAndView("suscripcion-gratis", modelo);
    }

    @RequestMapping(path = "/suscripcion-gratis", method = RequestMethod.GET)
    private ModelAndView mostrarSuscripcionGratis(HttpServletRequest request){

        if(null != request.getSession().getAttribute("rol")){
            if(request.getSession().getAttribute("rol").equals("cliente")){
                if(!(Boolean)request.getSession().getAttribute("tieneSuscripcion")){
                    return new ModelAndView("suscripcion-gratis");
                }
            }
        }
        return new ModelAndView("redirect:/main");
    }


    @RequestMapping(path = "/darDeBajaSuscripcion")
    public ModelAndView darDeBajaSuscripcion(HttpServletRequest request) {
        Long id= (Long)request.getSession().getAttribute("id");
        try{
            servicioSuscripcion.cancelarRenovacionAutomaticaDeSuscripcion(id);
        }catch(SuscripcionYaCanceladaException e){
            ModelMap model = new ModelMap();
            model.put("errorDarDeBaja","Su suscripcion ya fue dada de baja");
            return new ModelAndView("administrar-suscripcion", model);
        }
        ModelMap model = new ModelMap();
        model.put("confirmacionDarDeBaja","Su suscripcion fue dada de baja exitosamente");
        return new ModelAndView("main", model);
    }

    @RequestMapping(path = "/darDeAltaSuscripcion")
    public ModelAndView darDeAltaSuscripcion(HttpServletRequest request) {
        Long id= (Long)request.getSession().getAttribute("id");
        try{
            servicioSuscripcion.activarRenovacionAutomaticaDeSuscripcion(id);
        }catch(SuscripcionYaActivadaException e){
            ModelMap model = new ModelMap();
            model.put("errorDarDeAlta","Su suscripcion ya fue reactivada");
            return new ModelAndView("administrar-suscripcion", model);
        }
        ModelMap model = new ModelMap();
        model.put("confirmacionDarDeAlta","Su suscripcion fue reactivada exitosamente");
        return new ModelAndView("main", model);
    }

    @RequestMapping(path = "/administrar-suscripcion")
    private ModelAndView mostrarAdministrarSuscripcion(HttpServletRequest request){


        if(null != request.getSession().getAttribute("rol")){
            if(request.getSession().getAttribute("rol").equals("cliente")){
                obtenerDatosDeSuscripcion(request, (Long)request.getSession().getAttribute("id"));
                return new ModelAndView("administrar-suscripcion");
            }
        }
        return new ModelAndView("redirect:/main");
    }

    //@Scheduled(fixedRate = 10000)
    @Scheduled(cron = "0 29 19 ? * *")
    public void controlDeFechaDeSuscripciones(){
        //System.out.println("Fixed rate task ");
        servicioSuscripcion.revisionDeSuscripciones();
    }

    private void obtenerDatosDeSuscripcion(HttpServletRequest request, Long id) {
        if((Boolean) request.getSession().getAttribute("tieneSuscripcion")){
            request.getSession().setAttribute("suscripcion", servicioSuscripcion.buscarPorIdUsuario(id));
            request.getSession().setAttribute("tipoSuscripcion", servicioSuscripcion.buscarPorIdUsuario(id).getTipoSuscripcion());
        }
    }

}
