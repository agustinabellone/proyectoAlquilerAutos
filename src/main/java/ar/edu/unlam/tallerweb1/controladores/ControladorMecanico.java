package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaEnRevision;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class ControladorMecanico {

    private ModelMap modelMap = new ModelMap();
    private String vista;
    private ServicioDeAuto servicioDeAuto;

    @Autowired
    public ControladorMecanico(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/para-mantenimiento")
    public ModelAndView mostrarListadoDeAutosParaMantenimiento(HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request))
            accedeALaVistaDeAutosParaMantenimientoMostrandoUnaListaSiNoLanzaUnaExceptionMostrandoUnMensaje(request);
        else siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        return new ModelAndView(vista, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/revisar-auto/patente/${patente}")
    public ModelAndView enviarARevision(String patente, HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request)) {
            vista = "redirect:/para-mantenimiento";
            try {
                Auto paraRevision = servicioDeAuto.buscarAutoPorPatente(patente);
                servicioDeAuto.enviarARevision(paraRevision.getPatente(), (Long) request.getSession().getAttribute("id"));
                request.getSession().setAttribute("patente", paraRevision.getPatente());
            } catch (AutoNoExistente e) {
                request.getSession().setAttribute("error_no_existe", "No existe el auto que queres mandar");
            } catch (AutoYaEnRevision e) {
                request.getSession().setAttribute("error_ya_existe", "No se puede enviar un auto a revision que ya esta enviado");
            }
        } else {
            siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        }
        return new ModelAndView(vista, modelMap);
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return !(Objects.isNull(request.getSession().getAttribute("rol")));
    }

    private boolean esMecanico(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("mecanico");
    }

    private void accedeALaVistaDeAutosParaMantenimientoMostrandoUnaListaSiNoLanzaUnaExceptionMostrandoUnMensaje(HttpServletRequest request) {
        Long id_mecanico = (Long) request.getSession().getAttribute("id");
        String patente = (String) request.getSession().getAttribute("patente");
        String autoInexistente = (String) request.getSession().getAttribute("error_no_existe");
        String autoEnRevision = (String) request.getSession().getAttribute("error_ya_existe");
        vista = "en-mantenimiento";
        try {
            List<Auto> para_mantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
            modelMap.put("para_mantenimiento", para_mantenimiento);
            if (patente != null)
                modelMap.put("auto_enviado", "Se envio correctamente el auto: \n" + "Patente: " + patente);
            if (autoInexistente != null) modelMap.put("error_no_existe", autoInexistente);
            if (autoEnRevision != null) modelMap.put("error_ya_existe", autoEnRevision);
            modelMap.put("mecanico", id_mecanico);
        } catch (NoHayAutosEnMantenientoException e) {
            modelMap.put("error", "No hay autos para mantenimiento actualmente");
        }
    }

    private void siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError() {
        vista = "login";
        modelMap.put("datosLogin", new DatosLogin());
        modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
    }
}
