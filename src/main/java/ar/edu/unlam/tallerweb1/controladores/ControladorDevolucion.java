package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioDevolucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ControladorDevolucion {

    private ServicioDevolucion servicioDevolucion;
    private ServicioAlquiler servicioAlquiler;
    private ServicioGarage servicioGarage;
    private ServicioUsuario servicioUsuario;
    private ServicioSolicitud servicioSolicitud;

    @Autowired
    public ControladorDevolucion(ServicioDevolucion servicioDevolucion, ServicioGarage servicioGarage, ServicioUsuario servicioUsuario, ServicioSolicitud servicioSolicitud, ServicioAlquiler servicioAlquiler) {
        this.servicioDevolucion = servicioDevolucion;
        this.servicioGarage = servicioGarage;
        this.servicioUsuario = servicioUsuario;
        this.servicioSolicitud = servicioSolicitud;
        this.servicioAlquiler = servicioAlquiler;
    }

    public ControladorDevolucion() {
    }


    @RequestMapping("finalizar-alquiler")
    public ModelAndView irFinalizarAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Alquiler alquilerActivo = servicioAlquiler.obtenerAlquilerPorID(alquilerID);

        if (clienteID != null) {
            model.put("garagePartida", servicioGarage.obtenerGaragePorID(alquilerActivo.getGaragePartida().getId()));
            model.put("garageLlegada", servicioGarage.obtenerGaragePorID(alquilerActivo.getGarageLlegada().getId()));
            model.put("cliente", servicioUsuario.buscarPorId(clienteID));
            model.put("alquiler", alquilerActivo);
            model.put("auto", alquilerActivo.getAuto());
            model.put("fechaInicio", alquilerActivo.getF_egreso().toString());
            return new ModelAndView("mostrarConfirmacionDeFin", model);
        } else {
            return new ModelAndView("errorDevolucion");
        }
    }

    @RequestMapping("/modificar-garage-llegada")
    public ModelAndView modificarGarageDeLlegada(@RequestParam(value = "alquilerID") Long alquilerID) {
        Alquiler alquiler = servicioAlquiler.obtenerAlquilerPorID(alquilerID);
        List<Garage> garages = servicioGarage.obtenerListadoDeGarages();
        ModelMap model = new ModelMap();
        model.put("alquiler", alquiler);
        model.put("garages", garages);
        return new ModelAndView("modificarGarage", model);
    }

    @RequestMapping("/seleccionNuevoGarageSeleccionado")
    public ModelAndView procesarSeleccionNuevoGarageLlegada(@RequestParam(value = "nuevoGarage") Long garageID, @RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        Alquiler alquiler = servicioAlquiler.obtenerAlquilerPorID(alquilerID);
        Garage garage = servicioGarage.obtenerGaragePorID(garageID);
        servicioDevolucion.adicionarAumentoPorCambioDeLugarFecha(alquiler, garage);
        return new ModelAndView("redirect:/finalizar-alquiler?alquilerID=" + alquilerID);
    }


    @RequestMapping("/confirmacion-fin-alquiler")
    public ModelAndView procesarConfirmacionFinDeAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Alquiler alquiler = servicioAlquiler.obtenerAlquilerPorID(alquilerID);
        servicioSolicitud.realizarPeticionDeDevolucion(alquiler);
        modelo.put("alquilerID", alquiler.getId());
        modelo.put("auto", alquiler.getAuto());
        modelo.put("solicitud", "Espere la confirmacion de devolución...");
        modelo.put("valorarLuego", "valorarLuego");
        return new ModelAndView("valorar-auto", modelo);
    }

}
