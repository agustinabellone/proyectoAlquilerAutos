package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorHome {

    private ServicioAlquiler servicioAlquiler;
    private ServicioUsuario servicioUsuario;
    private ServicioHome servicioHome;
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorHome(ServicioAlquiler servicioAlquiler, ServicioUsuario servicioUsuario, ServicioHome servicioHome, ServicioSuscripcion servicioSuscripcion) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioUsuario=servicioUsuario;
        this.servicioHome=servicioHome;
        this.servicioSuscripcion=servicioSuscripcion;
    }


    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView mostrarHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView mostrarMain(HttpServletRequest request) {
        ModelMap model = new ModelMap();
            if(request.getSession().getAttribute("id")!=null){
                Usuario usuario = servicioUsuario.buscarPorId((Long) request.getSession().getAttribute("id"));
                List<Alquiler> alquileresUsuario = servicioAlquiler.obtenerAlquileresDeUsuario(usuario);
                List <String> fechasAlquileresUsuario = servicioHome.obtenerFechasEnString(alquileresUsuario);
                List <Marca> autosMarca = servicioHome.obtenerMarcaAutoAlquiler(alquileresUsuario);
                List <Modelo> autosModelo = servicioHome.obtenerModeloAutoAlquiler(alquileresUsuario);

                Integer puntajeUsuario = usuario.getPuntaje();

                Alquiler alquileresEsperandoConfirmacion = servicioAlquiler.obtenerAlquilerPendienteDeUsuario(usuario);
                model.put("esperandoConfirmacion", alquileresEsperandoConfirmacion);

                model.put("alquileres", alquileresUsuario);
                model.put("fechas", fechasAlquileresUsuario);
                model.put("modelos", autosModelo);
                model.put("marcas", autosMarca);
                model.put("puntaje", puntajeUsuario);
                Long id=(Long) request.getSession().getAttribute("id");
                model=obtenerDatosDeSuscripcion(request, model, id);

                return new ModelAndView("main", model);
            }

        return new ModelAndView("redirect:/home");
    }

    private ModelMap obtenerDatosDeSuscripcion(HttpServletRequest request, ModelMap model, Long id) {
        if((Boolean) request.getSession().getAttribute("tieneSuscripcion")){
            Suscripcion suscripcion = servicioSuscripcion.buscarPorIdUsuario(id);
            model.put("suscripcion", suscripcion);
            model.put("tipoSuscripcion", suscripcion.getTipoSuscripcion());
        }
        return model;
    }

    @RequestMapping(path = "/actualizarNotificaciones", method = RequestMethod.GET)
    public void actualizarNotificaciones(@RequestParam(value="id_noti") Long id_noti,
                                         @RequestParam(value="id_usuario") Long id_usuario,
                                         HttpServletResponse response,
                                         HttpServletRequest request) throws IOException {

        this.servicioUsuario.actualizarNotificacion(id_noti);
        Usuario buscado = this.servicioUsuario.buscarPorId(id_usuario);
        List<Notificacion> notificaciones= servicioUsuario.getNotificacionesPorId(buscado);

        request.getSession().setAttribute("notificaciones", notificaciones);

        JsonObject json= new JsonObject();
        PrintWriter out = response.getWriter();

        json.addProperty("cantidadNotis", notificaciones.size());

        out.print(json);

    }

    @RequestMapping(path = "/ir-a-encargado-home", method = RequestMethod.GET)
    public ModelAndView mostrarMainEncargado(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if(request.getSession().getAttribute("id")!=null){
            Usuario usuario = servicioUsuario.buscarPorId((Long) request.getSession().getAttribute("id"));
            List<Solicitud> solicitudesEsperandoConfirmacion = servicioUsuario.obtenerSolicitudesPendientesDeUnEncargado(usuario);
            model.put("esperandoConfirmacion", solicitudesEsperandoConfirmacion);
            return new ModelAndView("mainEncargado", model);
        }
        return new ModelAndView("redirect:/home");
    }



}
