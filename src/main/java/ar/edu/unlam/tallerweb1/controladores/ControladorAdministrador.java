package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorAdministrador {

    private ModelMap modelMap = new ModelMap();
    private String viewName;
    private ServicioAlquiler servicioAlquiler;

    @Autowired
    public ControladorAdministrador(ServicioAlquiler servicioAlquiler) {
        this.servicioAlquiler = servicioAlquiler;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)) {
            viewName = "redirect:/panel-principal";
            return new ModelAndView(viewName);
        } else {
            modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            modelMap.put("datosLogin", new DatosLogin());
            return new ModelAndView("login", modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)) {
            try {
                List<Auto> autosAlquilados = servicioAlquiler.obtenerAutosAlquilados();
                modelMap.put("nombre", request.getSession().getAttribute("nombre"));
                modelMap.put("lista-de-autos-alquilados", autosAlquilados);
                return new ModelAndView("panel-principal", modelMap);
            } catch (NoHayAutosAlquiladosException e) {
                modelMap.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
                return new ModelAndView("panel-principal");
            }
        } else {
            modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            modelMap.put("datosLogin", new DatosLogin());
            return new ModelAndView("login", modelMap);
        }
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    public List<Auto> obtenerListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        return null;
    }
}
