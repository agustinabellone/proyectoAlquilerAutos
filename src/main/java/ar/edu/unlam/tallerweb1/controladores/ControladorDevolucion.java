package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioDevolucion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEncargado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
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

    @Autowired
    public ControladorDevolucion(ServicioDevolucion servicioDevolucion, ServicioEncargado servicioEncargado, ServicioGarage servicioGarage) {
        this.servicioDevolucion = servicioDevolucion;
        this.servicioEncargado=servicioEncargado;
        this.servicioGarage=servicioGarage;
    }

    public ControladorDevolucion() {
    }

    @RequestMapping("/alMain")
    public ModelAndView irAMain() {
        Auto auto = new Auto();
        Alquiler alquiler = new Alquiler(1L, auto);
        ModelMap modelo = new ModelMap();
        modelo.put("alquiler", alquiler);
        return new ModelAndView("main", modelo);
    }

   /* @RequestMapping("/finalizarAlquiler")
    public ModelAndView devolver() {

        ModelMap model = new ModelMap();
        if(alquiler.getId()!=null) {

            return new ModelAndView("alquilerFinalizado");
        } else
            model.put("Error", "Error de Devolucion");
        return new ModelAndView("errorDevolucion", model);

    }*/

    @RequestMapping("finalizar-alquiler")
    public ModelAndView irFinalizarAlquiler(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Alquiler alquilerActivo = servicioDevolucion.obtenerAlquilerActivoDeCliente(clienteID);
        Auto auto = alquilerActivo.getAuto();
        if(alquilerActivo!=null) {
            model.put("alquiler", alquilerActivo);
            model.put("auto", auto);
            return new ModelAndView("mostrarConfirmacionDeFin", model);
        } else {
            return new ModelAndView("errorDevolucion");
        }
    }

    @RequestMapping("/modificar-garage-llegada")
    public ModelAndView modificarGarageDeLlegada(@RequestParam (value = "alquilerID") Long alquilerID) {
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerPorID(alquilerID);
        List<Garage> garages = servicioGarage.obtenerListadoDeGarages();
        ModelMap model = new ModelMap();
        model.put("alquiler", alquiler);
        model.put("garages", garages);
        return new ModelAndView("modificarGarage", model);
    }

    @RequestMapping("/seleccionNuevoGarageSeleccionado")
    public ModelAndView procesarSeleccionNuevoGarageLlegada(@RequestParam (value = "nuevoGarage") Long garageID, @RequestParam (value="alquiler") Alquiler alquiler, HttpServletRequest request) {
        Garage garage = servicioGarage.obtenerGaragePorID(garageID);
        alquiler.setGarageLlegada(garage);
        irFinalizarAlquiler(request); //PARA QUE ACTUALIZE DATOS, esta bien en esta clase ya que manejo vistas de controlador
        servicioDevolucion.adicionarAumentoPorCambioDeLugarFecha(alquiler);
        return new ModelAndView("mostrarConfirmacionDeFin");
    }

    @RequestMapping("/confirmacion-fin-alquiler")
    public ModelAndView procesarConfirmacionFinDeAlquiler(HttpServletRequest request) {
        Long clienteID = (Long) request.getSession().getAttribute("id");
        Alquiler alquiler = servicioDevolucion.obtenerAlquilerActivoDeCliente(clienteID); //SIEMPRE PARA MANEJAR ALQUILER CON SESSION?
        //FINALIZAR ALQUILER METODO EN EL SERV/REPO O EN EL MODELO
        servicioDevolucion.finalizarAlquilerCliente(alquiler);
        return new ModelAndView("mostrarConfirmacionDeFin");
    }







}

