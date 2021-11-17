package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDevolucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEncargado;
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
    private ServicioEncargado servicioEncargado;
    private ServicioGarage servicioGarage;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorDevolucion(ServicioDevolucion servicioDevolucion, ServicioEncargado servicioEncargado, ServicioGarage servicioGarage, ServicioUsuario servicioUsuario) {
        this.servicioDevolucion = servicioDevolucion;
        this.servicioEncargado = servicioEncargado;
        this.servicioGarage = servicioGarage;
        this.servicioUsuario = servicioUsuario;
    }

    public ControladorDevolucion() {
    }

    @RequestMapping("/alMain")
    public ModelAndView irAMain(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        Auto auto = new Auto();
        Alquiler alquiler = new Alquiler(1L, auto);
        ModelMap modelo = new ModelMap();
        modelo.put("alquiler", alquiler);
        return new ModelAndView("main", modelo);
    }

    @RequestMapping("finalizar-alquiler")
    public ModelAndView irFinalizarAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Alquiler alquilerActivo = servicioDevolucion.obtenerAlquilerPorID(alquilerID);
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Garage garagePartida = servicioGarage.obtenerGaragePorID(alquilerActivo.getGaragePartida().getId());
        Garage garageLlegada = servicioGarage.obtenerGaragePorID(alquilerActivo.getGarageLlegada().getId());
        Auto auto = alquilerActivo.getAuto();
        String fechaInicio = alquilerActivo.getF_egreso().toString();

        if (clienteID != null) {
            model.put("fechaInicio", fechaInicio);
            model.put("garagePartida", garagePartida);
            model.put("garageLlegada", garageLlegada);
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
    public ModelAndView procesarConfirmacionFinDeAlquilerr(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID); //SIEMPRE PARA MANEJAR ALQUILER CON SESSION?
        Auto auto = alquiler.getAuto();
        modelo.put("alquilerID", alquiler.getId());
        modelo.put("auto", auto);
        modelo.put("valorarLuego", "valorarLuego");
        servicioDevolucion.finalizarAlquilerCliente(alquiler);
        return new ModelAndView("valorar-auto", modelo);
    }


}

