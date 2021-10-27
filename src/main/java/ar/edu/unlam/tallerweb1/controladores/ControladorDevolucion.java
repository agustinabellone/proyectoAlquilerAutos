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
    public ModelAndView irFinalizarAlquiler(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        //Alquiler alquilerActivo = servicioDevolucion.obtenerAlquilerActivoDeCliente(usuario);
        Garage garagePartida = servicioGarage.obtenerGaragePorID(alquilerActivo.getGaragePartida().getId());
        Garage garageLlegadaEst = servicioGarage.obtenerGaragePorID(alquilerActivo.getGarageLlegadaEst().getId());
        Auto auto = alquilerActivo.getAuto();

        if (clienteID != null) {
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
        irFinalizarAlquiler(request); //PARA QUE ACTUALIZE DATOS, esta bien en esta clase ya que manejo vistas de controlador
        return new ModelAndView("mostrarConfirmacionDeFin");
    }

    @RequestMapping("/confirmacion-fin-alquiler")
    public ModelAndView procesarConfirmacionFinDeAlquiler(@RequestParam(value = "alquilerID") Long alquilerID, HttpServletRequest request) {
        Long clienteID = (Long) request.getAttribute("id");
        Usuario usuario = servicioUsuario.buscarPorId(clienteID);
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID); //SIEMPRE PARA MANEJAR ALQUILER CON SESSION?
        servicioDevolucion.finalizarAlquilerCliente(alquiler);
        return new ModelAndView("mostrarConfirmacionDeFin");
    }


}

