package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ControladorMecanico {

    private ServicioDeAuto servicioDeAuto;

    @Autowired
    public ControladorMecanico(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/para-mantenimiento")
    public ModelAndView mostrarListadoDeAutosParaMantenimiento(HttpServletRequest mecanico) {
        ModelMap modelMap = new ModelMap();
        String vista;
        if (esMecaico(mecanico) && estaSeteadoElRol(mecanico)) {
            vista = "en-mantenimiento";
            try {
                List<Auto> para_mantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
                modelMap.put("para_mantenimiento", para_mantenimiento);
            } catch (NoHayAutosEnMantenientoException e) {
                modelMap.put("error","No hay autos para mantenimiento actualmente");
            }
            return new ModelAndView(vista, modelMap);
        } else {
            vista = "login";
            modelMap.put("datosLogin", new DatosLogin());
            modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            return new ModelAndView(vista, modelMap);
        }
    }

    private boolean estaSeteadoElRol(HttpServletRequest mecanico) {
        return !(Objects.isNull(mecanico.getSession().getAttribute("rol")));
    }

    private boolean esMecaico(HttpServletRequest mecanico) {
        return mecanico.getSession().getAttribute("rol").equals("mecanico");
    }
}
