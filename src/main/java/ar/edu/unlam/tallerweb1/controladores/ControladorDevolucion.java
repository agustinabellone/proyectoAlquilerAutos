package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
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
    private ServicioGarage servicioGarage;
    private ServicioUsuario servicioUsuario;
    private ServicioSolicitud servicioSolicitud;

    @Autowired
    public ControladorDevolucion(ServicioDevolucion servicioDevolucion, ServicioGarage servicioGarage, ServicioUsuario servicioUsuario, ServicioSolicitud servicioSolicitud) {
        this.servicioDevolucion = servicioDevolucion;
        this.servicioGarage = servicioGarage;
        this.servicioUsuario = servicioUsuario;
        this.servicioSolicitud=servicioSolicitud;
    }

    public ControladorDevolucion() {
    }


    @RequestMapping("finalizar-alquiler")
    public ModelAndView irFinalizarAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Alquiler alquilerActivo = servicioDevolucion.obtenerAlquilerPorID(alquilerID);
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Garage garagePartida = servicioGarage.obtenerGaragePorID(alquilerActivo.getGaragePartida().getId());
        Garage garageLlegadaEst = servicioGarage.obtenerGaragePorID(alquilerActivo.getGarageLlegadaEst().getId());
        Auto auto = alquilerActivo.getAuto();
        String fechaInicio = alquilerActivo.getF_egreso().toString();

        if (clienteID != null) {
            model.put("fechaInicio", fechaInicio);
            model.put("garagePartida", garagePartida);
            model.put("garageLlegadaEst", garageLlegadaEst);
            model.put("cliente", usuario);
            model.put("alquiler", alquilerActivo);
            model.put("auto", auto);
            return new ModelAndView("mostrarConfirmacionDeFin", model);
        } else {
            return new ModelAndView("errorDevolucion");
        }
    }

    @RequestMapping("/modificar-garage-llegada")
    public ModelAndView modificarGarageDeLlegada(@RequestParam(value = "alquilerID") Long alquilerID) {
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID);
        List<Garage> garages = servicioGarage.obtenerListadoDeGarages();
        ModelMap model = new ModelMap();
        model.put("alquiler", alquiler);
        model.put("garages", garages);
        return new ModelAndView("modificarGarage", model);
    }

    @RequestMapping("/seleccionNuevoGarageSeleccionado")
    public ModelAndView procesarSeleccionNuevoGarageLlegada(@RequestParam(value = "nuevoGarage") Long garageID, @RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID);
        Garage garage = servicioGarage.obtenerGaragePorID(garageID);
        alquiler.setGarageLlegada(garage);
        servicioDevolucion.adicionarAumentoPorCambioDeLugarFecha(alquiler);
        return new ModelAndView("redirect:/finalizar-alquiler?alquilerID=" + alquilerID);
    }

    @RequestMapping("/confirmacion-fin-alquiler")
    public ModelAndView procesarConfirmacionFinDeAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID); //SIEMPRE PARA MANEJAR ALQUILER CON SESSION?
        servicioSolicitud.realizarPeticionDeDevolucion(alquiler);
        modelo.put("alquilerID", alquiler.getId());
        modelo.put("auto", alquiler.getAuto());
        modelo.put("solicitud", "Espere la confirmacion de devolución...");
        modelo.put("valorarLuego", "valorarLuego");
        //servicioDevolucion.finalizarAlquilerCliente(alquiler);
        return new ModelAndView("valorar-auto", modelo);
    }

    @RequestMapping("/finalizarAlquiler")
    public ModelAndView darPorFinalizadoElAlquiler(@RequestParam(value = "solicitud") Long solicitudID) {
        ModelMap modelo = new ModelMap();
        Solicitud solicitud = servicioSolicitud.obtenerSolicitudPorId(solicitudID);
        servicioDevolucion.finalizarAlquilerCliente(solicitud);
        modelo.put("funciono", "Alquiler finalizado");
        return new ModelAndView("mainEncargado", modelo);
    }

}

