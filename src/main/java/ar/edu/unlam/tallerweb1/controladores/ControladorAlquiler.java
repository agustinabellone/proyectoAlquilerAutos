package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaAlquiladoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;


@Controller
public class ControladorAlquiler {

    private ServicioAlquiler servicioAlquiler;
    private ServicioMail servicioMail;
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorAlquiler(ServicioAlquiler servicioAlquiler, ServicioMail servicioMail,ServicioSuscripcion servicioSuscripcion) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioMail=servicioMail;
        this.servicioSuscripcion=servicioSuscripcion;
    }

    @RequestMapping(path = "/alquilar-auto", method = RequestMethod.GET)
    public ModelAndView mostrarListaDeAutos(HttpServletRequest request) throws NoHayAutosDisponiblesException {
        ModelMap modelo = new ModelMap();
        Long id= (Long) request.getSession().getAttribute("id");
       TipoSuscripcion tipoSuscripcion = servicioSuscripcion.buscarPorIdUsuario(id).getTipoSuscripcion();
        if(tipoSuscripcion.getId()==1){
            List<Auto> autosDisponibles = servicioAlquiler.obtenerAutosDisponiblesGamaBaja();
            modelo.put("autosDisponibles", autosDisponibles);
        }if (tipoSuscripcion.getId()==2){
            List<Auto> autosDisponibles = servicioAlquiler.obtenerAutosDisponiblesGamaMedia();
            modelo.put("autosDisponibles", autosDisponibles);
        }if (tipoSuscripcion.getId()==3){
            List<Auto> autosDisponibles = servicioAlquiler.obtenerAutosDisponiblesGamaAlta();
            modelo.put("autosDisponibles", autosDisponibles);
        }
            return new ModelAndView("alquilarAuto", modelo);
    }


    @RequestMapping(path = "/elegir-fechas", method = RequestMethod.GET)
    public ModelAndView mostrarFechasAlquiler(@RequestParam("id_auto") Long id_auto,
                                              @RequestParam("imagen_auto") String imagen_auto) {
        ModelMap modelo = new ModelMap();
        modelo.put("id_auto", id_auto);
        modelo.put("imagen_auto", imagen_auto);
        return new ModelAndView("alquilarAutoFechasDisponibles", modelo);
    }

    @RequestMapping(path = "/confirmacion", method = RequestMethod.POST)
    public ModelAndView mostrarConfirmacionAlquiler(@RequestParam("salida") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate salida,
                                                    @RequestParam("ingreso") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate ingreso,
                                                    @RequestParam("id_auto") Long id_auto,
                                                    @RequestParam("imagen_auto") String imagen_auto,
                                                    @RequestParam("lugarRetiro") Long lugarRetiro,
                                                    @RequestParam("lugarDevolucion") Long lugarDevolucion) {

        ModelMap modelo = new ModelMap();

        if(salida.isAfter(ingreso)) {
            modelo.put("errorFechas", "La fecha de retiro debe ser anterior a la fecha de devolución");
        }
        String formattedDateIngreso = ingreso.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale ( new Locale( "es" , "ES" )));
        String formattedDateSalida = salida.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale ( new Locale( "es" , "ES" )));

        Auto auto = servicioAlquiler.obtenerAutoPorId(id_auto);
        modelo.put("salida", salida);
        modelo.put("ingreso", ingreso);
        modelo.put("id_auto", id_auto);
        modelo.put("formattedDateIngreso", formattedDateIngreso);
        modelo.put("formattedDateSalida", formattedDateSalida);
        modelo.put("modelo_auto", auto.getModelo().getDescripcion());
        modelo.put("marca_auto", auto.getMarca().getDescripcion());
        modelo.put("imagen_auto", imagen_auto);

        Garage garageRetiro = servicioAlquiler.obtenerGaragePorId(lugarRetiro);
        Garage garageDevolucion = servicioAlquiler.obtenerGaragePorId(lugarDevolucion);

        modelo.put("lugarRetiro", garageRetiro);
        modelo.put("lugarDevolucion", garageDevolucion);

        return new ModelAndView("alquilarAutoConfirmacion", modelo);
    }

    @RequestMapping(path = "/validar-alquiler", method = RequestMethod.GET)
    public ModelAndView alquilarAuto(HttpServletRequest request,
                                     @RequestParam("id_auto") Long id_auto,
                                     @RequestParam("salida") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate salida,
                                     @RequestParam("ingreso") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate ingreso,
                                     @RequestParam("lugarRetiro") Long lugarRetiro,
                                     @RequestParam("lugarDevolucion") Long lugarDevolucion){

        ModelMap modelo = new ModelMap();

        Auto auto = servicioAlquiler.obtenerAutoPorId(id_auto);

        Long id_usuario = (Long) request.getSession().getAttribute("id");

        Usuario usuario = servicioAlquiler.obtenerUsuarioPorId(id_usuario);

        Garage garageRetiro = servicioAlquiler.obtenerGaragePorId(lugarRetiro);
        Garage garageDevolucion = servicioAlquiler.obtenerGaragePorId(lugarDevolucion);

        String mail = (String) request.getSession().getAttribute("email");

        DatosAlquiler datosAlquiler = new DatosAlquiler(usuario, auto, salida, ingreso, garageRetiro, garageDevolucion);

        try {
            servicioAlquiler.AlquilarAuto(datosAlquiler);
            servicioMail.enviarMailAlquiler(mail,garageRetiro.getDireccion(),garageDevolucion.getDireccion(),salida,ingreso);
        }
        catch (AutoYaAlquiladoException e) {
            return alquilerFallido(modelo, "El auto ya fue alquilado en esas fechas.");
        }

        return alquilerExitoso(modelo, "Alquiler realizado con éxito.");

    }

    private ModelAndView alquilerExitoso(ModelMap modelo, String mensaje) {
        modelo.put("mensaje", mensaje);
        return new ModelAndView("alquilarAutoConfirmacion", modelo);
    }

    private ModelAndView alquilerFallido(ModelMap modelo, String mensaje) {
        modelo.put("mensajeFallido", mensaje);
        return new ModelAndView("alquilarAutoConfirmacion", modelo);
    }
}
