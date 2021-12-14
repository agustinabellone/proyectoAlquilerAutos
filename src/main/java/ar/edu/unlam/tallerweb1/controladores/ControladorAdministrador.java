package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ControladorAdministrador {
    private ServicioDeAuto servicioDeAuto;

    public ControladorAdministrador(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    public ControladorAdministrador(ServicioAlquiler servicioAlquiler, ServicioDeAuto servicioDeAuto, ServicioSuscripcion servicioSuscripcion, ServicioUsuario servicioUsuario) {
    }

    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadElRol(request) && esAdiministrador(request)) {
            List<Auto> autosAlquilados = servicioDeAuto.obtenerAutosAlquilados();
            model.put("autos_alquilados", autosAlquilados);
            return new ModelAndView("panel-principal", model);
        }
        return null;
    }

    private boolean esAdiministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    private boolean estaSeteadElRol(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    public ModelAndView mostrarAsignacionDeRol(HttpServletRequest request) {
        return null;
    }

    public List<Usuario> obtenerListaDeUsuariosConRolPendiente() {
        return null;
    }

    public ModelAndView asignarRolAlEmpleado(String rol, Long pendienteDeRol, HttpServletRequest request) {
        return null;
    }

    public void mostrarAutosDisponibles(HttpServletRequest administrador) {

    }

    public ModelAndView enviarAMantenimiento(Long id_auto, HttpServletRequest administrador) {
        return null;
    }
}
