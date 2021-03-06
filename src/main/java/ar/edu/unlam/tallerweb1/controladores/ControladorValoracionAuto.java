package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.Exceptions.AutoNoValorado;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorValoracionAuto(ServicioValoracion servicioValoracion, ServicioUsuario servicioUsuario){
        this.servicioValoracion=servicioValoracion;
        this.servicioUsuario = servicioUsuario;
    }


    @RequestMapping(path = "/lista-viajes", method = RequestMethod.GET)
    public ModelAndView irListaViajes(HttpServletRequest request){
        Long clienteID = (Long) request.getSession().getAttribute("id");
        ModelMap modelo=new ModelMap();

        if(elUsuarioEsCliente(request)){
            List<Alquiler> viajesObtenidos = servicioValoracion.obtenerAlquileresHechos(clienteID);
            modelo.put("viajesObtenidos",viajesObtenidos);
        }else{
            return new ModelAndView("home");
        }

        return new ModelAndView("lista-de-viajes", modelo);
    }

    private boolean elUsuarioEsCliente(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("cliente");
    }


    @RequestMapping(path = "valorar-auto" , method = RequestMethod.GET)
    public ModelAndView valorarAuto(@RequestParam(value = "id_auto")Long autoID,
                                    @RequestParam(value = "id_alquiler") Long alquilerID){
        ModelMap modelo=new ModelMap();
        Auto auto=servicioValoracion.obtenerAutoPorId(autoID);
        modelo.put("auto",auto);
        modelo.put("alquilerID",alquilerID);
        return new ModelAndView("valorar-auto",modelo);
    }

    @RequestMapping(path = "guardar-valoracion-Auto", method = RequestMethod.POST)
    public ModelAndView guardarValoracionAuto(HttpServletRequest request,
                                              @RequestParam(value = "estrellasValoracion") int cantidadEstrellas,
                                              @RequestParam(value = "comentario") String comentarioAuto,
                                              @RequestParam(value ="autoID") Long autoID,
                                              @RequestParam(value = "alquilerID")Long alquilerID) throws NoHayEmpladosException {
        ModelMap modelo=new ModelMap();
        Auto auto=servicioValoracion.obtenerAutoPorId(autoID);
        modelo.put("auto", auto);

        servicioValoracion.guardarValoracionAuto(cantidadEstrellas,comentarioAuto, auto,alquilerID);

        Long id_usuario = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(id_usuario);

        Puntaje puntaje = new Puntaje();

        servicioUsuario.actualizarPuntaje(puntaje.getValoracion(), usuario);

        modelo.put("mensaje", "Auto valorado exitosamente");
        return new ModelAndView("valorar-auto-confirmacion", modelo);

    }

    @RequestMapping(path = "valoraciones-auto" , method = RequestMethod.GET)
    public ModelAndView verValoracionesAuto(@RequestParam(value = "id_auto")Long autoID){
        ModelMap modelo=new ModelMap();

        try {
            List<ValoracionAuto> valoracionesAuto = servicioValoracion.obtenerValoracionesAuto(autoID);
            Long valoracionPromedioAuto=servicioValoracion.obtenerValoracionPromedioAuto(autoID);
            Auto auto=servicioValoracion.obtenerAutoPorId(autoID);
            int cantValoraciones=valoracionesAuto.size();
            modelo.put("auto", auto);
            modelo.put("listadoValoracionesAuto",valoracionesAuto);
            modelo.put("valoracionPromedio",valoracionPromedioAuto);
            modelo.put("cantidadValoraciones",cantValoraciones);
        }
        catch (AutoNoValorado e) {
           modelo.put("mensaje", "El auto no tiene valoraciones");
           return new ModelAndView("vista-valoraciones-auto",modelo);
        }

        return new ModelAndView("vista-valoraciones-auto",modelo);

    }



}

