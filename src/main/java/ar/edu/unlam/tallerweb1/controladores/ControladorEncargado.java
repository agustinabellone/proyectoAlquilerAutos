package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoEnviaAutoAMantenimiento;
import ar.edu.unlam.tallerweb1.modelo.Solicitud;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
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
public class ControladorEncargado {

    private ServicioLogin servicioLogin;
    private ServicioUsuario servicioUsuario;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioSolicitud servicioSolicitud;
    private ServicioDevolucion servicioDevolucion;

    @Autowired
    public ControladorEncargado(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario, ServicioSuscripcion servicioSuscripcion, ServicioSolicitud servicioSolicitud, ServicioDevolucion servicioDevolucion) {
        this.servicioLogin = servicioLogin;
        this.servicioUsuario = servicioUsuario;
        this.servicioSuscripcion=servicioSuscripcion;
        this.servicioSolicitud=servicioSolicitud;
        this.servicioUsuario=servicioUsuario;
        this.servicioDevolucion=servicioDevolucion;
    }

    @RequestMapping(path = "/ir-a-encargado-home", method = RequestMethod.GET)
    public ModelAndView mostrarMainEncargado(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if((request.getSession().getAttribute("id")!=null) && (request.getSession().getAttribute("rol").equals("encargado"))){
           // Usuario usuario = servicioUsuario.buscarPorId((Long) request.getSession().getAttribute("id"));
            List<Solicitud> solicitudesEsperandoConfirmacion = servicioUsuario.obtenerSolicitudesPendientesDeUnEncargado((Long) request.getSession().getAttribute("id"));
            model.put("esperandoConfirmacion", solicitudesEsperandoConfirmacion);
            return new ModelAndView("mainEncargado", model);
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/cierreDevolucion")
    public ModelAndView datosDevolucionAlquiler(@RequestParam(value = "solicitud") Long solicitudID) {
        ModelMap modelo = new ModelMap();
        Solicitud solicitud = servicioSolicitud.obtenerSolicitudPorId(solicitudID);
        modelo.put("solicitud", solicitud);
        return new ModelAndView("cierreDevolucionEncargado", modelo);
    }

    @RequestMapping("/finalizarAlquiler")
    public ModelAndView darPorFinalizadoElAlquiler(@RequestParam(value = "solicitud") Long solicitudID, @RequestParam(value = "condicion", required = false) String enCondiciones,@RequestParam(value = "comentario", required = false) String comentario,@RequestParam(value = "kilometros", required = true) Integer km) throws NoEnviaAutoAMantenimiento, AutoNoExistente {
        ModelMap modelo = new ModelMap();
        Solicitud solicitud = servicioSolicitud.obtenerSolicitudPorId(solicitudID);
        servicioDevolucion.finalizarAlquilerCliente(solicitud, enCondiciones, comentario, km);
        modelo.put("funciono", "Alquiler finalizado");
        return new ModelAndView("mainEncargado", modelo);
    }

}
