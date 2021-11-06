package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorAdministrador {

    private ModelMap modelMap = new ModelMap();
    private String viewName;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;

    @Autowired
    public ControladorAdministrador(ServicioAlquiler servicioAlquiler, ServicioDeAuto servicioDeAuto) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            return redirigirAlPanelPrincipal();
        } else {
            return enviarAlLoginConMensajeDeErrorDeQueNoTienePermisosParaAccederALaVista();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            try {
                return obtenerLaListaDeLosAutosAlquiladosYLaInformacionDelAdministradorParaMostrarlaEnElPanelPrincipal(request);
            } catch (NoHayAutosAlquiladosException e) {
                return enviarAlPanelPrincipalConUnAvisoDeQueTodaviaNoHayAutosAlquilados();
            }
        } else {
            return enviarAlLoginConMensajeDeErrorDeQueNoTienePermisosParaAccederALaVista();
        }
    }

    private boolean elRolEstaSeteadoYEsAdministrador(HttpServletRequest request) {
        return elRolEstaSeteado(request) && elRolEsAdministrador(request);
    }

    private ModelAndView redirigirAlPanelPrincipal() {
        viewName = "redirect:/panel-principal";
        return new ModelAndView(viewName);
    }

    private ModelAndView enviarAlLoginConMensajeDeErrorDeQueNoTienePermisosParaAccederALaVista() {
        modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
        modelMap.put("datosLogin", new DatosLogin());
        viewName = "login";
        return getModelAndView();
    }

    private ModelAndView obtenerLaListaDeLosAutosAlquiladosYLaInformacionDelAdministradorParaMostrarlaEnElPanelPrincipal(HttpServletRequest request) throws NoHayAutosAlquiladosException {
        List<Auto> autosAlquilados = this.obtenerListaDeAutosAlquilados();
        guardarEnElModelMapElNombreDelAdministradorYLaLisstaDeAutosAlquilados(request, autosAlquilados);
        viewName = "panel-principal";
        return getModelAndView();
    }

    private ModelAndView enviarAlPanelPrincipalConUnAvisoDeQueTodaviaNoHayAutosAlquilados() {
        modelMap.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
        viewName = "panel-principal";
        return getModelAndView();
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    private ModelAndView getModelAndView() {
        return new ModelAndView(viewName, modelMap);
    }

    public List<Auto> obtenerListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        return servicioAlquiler.obtenerAutosAlquilados();
    }

    private void guardarEnElModelMapElNombreDelAdministradorYLaLisstaDeAutosAlquilados(HttpServletRequest request, List<Auto> autosAlquilados) {
        modelMap.put("nombre", request.getSession().getAttribute("nombre"));
        modelMap.put("lista-de-autos-alquilados", autosAlquilados);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos-los-autos")
    public ModelAndView mostrarTodosLosAutos(HttpServletRequest usuarioConRol) {
        if(elRolEstaSeteadoYEsAdministrador(usuarioConRol)){
            List<Auto> autosObtenidos = servicioDeAuto.obtenerTodoLosAutos();
            modelMap.put("lista-de-autos",autosObtenidos);
            return new ModelAndView("todos-los-autos", modelMap);
        }
        else {
            return enviarAlLoginConMensajeDeErrorDeQueNoTienePermisosParaAccederALaVista();
        }
    }
}
