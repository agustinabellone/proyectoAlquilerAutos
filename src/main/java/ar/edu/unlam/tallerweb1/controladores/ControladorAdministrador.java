package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorAdministrador {

    private ModelMap modelMap = new ModelMap();
    private String viewName;

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)){
            viewName = "redirect:/panel-principal";
            return new ModelAndView(viewName);
        } else{
            modelMap.put("errorSinPermisos","No tienes los permisos necesarios para acceder a esta pagina");
            modelMap.put("datosLogin",new DatosLogin());
            return new ModelAndView("login",modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        if (elRolEstaSeteado(request)&& elRolEsAdministrador(request)){
            modelMap.put("nombre",request.getSession().getAttribute("nombre"));
            return new ModelAndView("panel-principal",modelMap);
        }else{
            modelMap.put("errorSinPermisos","No tienes los permisos necesarios para acceder a esta pagina");
            modelMap.put("datosLogin",new DatosLogin());
            return new ModelAndView("login",modelMap);
        }
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }
}
