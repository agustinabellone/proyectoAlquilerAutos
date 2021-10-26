package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Alquiler;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.ValoracionAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorValoracionAuto {

    private ServicioValoracion servicioValoracion;


    @Autowired
    public ControladorValoracionAuto(ServicioValoracion servicioValoracion){
        this.servicioValoracion=servicioValoracion;
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView prueba(){
        ModelMap modelo=new ModelMap();
        return new ModelAndView("main");
    }


    @RequestMapping(path = "/lista-viajes", method = RequestMethod.GET)
    public ModelAndView irListaViajes(HttpServletRequest request){
        Long clienteID = (Long) request.getSession().getAttribute("id");//me trae de la session
        ModelMap modelo=new ModelMap();
        Long id=3L;
        List<Alquiler> viajesObtenidos = servicioValoracion.obtenerAlquileresHechos(id);
        modelo.put("viajesObtenidos",viajesObtenidos);
        return new ModelAndView("lista-de-viajes", modelo);

    /*    if(elUsuarioEsCliente(request)){
            List<Alquiler> viajesObtenidos = servicioValoracion.obtenerAlquileresHechos(id);
            modelo.put("viajesObtenidos",viajesObtenidos);
        }else{
            return new ModelAndView("home");
        }

        return new ModelAndView("lista-de-viajes", modelo);*/
    }

    private boolean elUsuarioEsCliente(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("");
    }


    @RequestMapping(path = "valorar-auto" , method = RequestMethod.GET)
    public ModelAndView valorarAuto(@RequestParam(value = "id_auto")Long autoID){
        ModelMap modelo=new ModelMap();
        Auto auto=servicioValoracion.obtenerAutoPorId(autoID);
        modelo.put("auto",auto);
        return new ModelAndView("valorar-auto",modelo);
    }

    @RequestMapping(path = "guardar-valoracion-Auto", method = RequestMethod.POST)
    public ModelAndView guardarValoracionAuto(@RequestParam(value = "estrellasValoracion") int cantidadEstrellas,
                                              @RequestParam(value = "comentario") String comentarioAuto,
                                              @RequestParam(value ="autoID") Long autoID)
                                              {
        ModelMap modelo=new ModelMap();
        Auto auto=servicioValoracion.obtenerAutoPorId(autoID);
        servicioValoracion.guardarValoracionAuto(cantidadEstrellas,comentarioAuto, auto);
        return  new ModelAndView("main");
    }



}

