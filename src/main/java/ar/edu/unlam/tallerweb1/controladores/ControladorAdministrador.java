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

    private ModelAndView modelAndView = new ModelAndView();
    private ModelMap modelMap = new ModelMap();

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteado(request) && elRolEsAdministrador(request)){
            modelAndView.addObject("mensaje","Bienvenido!!!");
            modelAndView.setViewName("redirect:/panel-principal?idUsuario="+request.getSession().getAttribute("id")+"&nombre="+request.getSession().getAttribute("nombre"));
        } else{
            modelAndView.addObject("error","No tienes los permisos necesarios");
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(@RequestParam("idUsuario") Long idDelAdministrador, @RequestParam("nombre") String nombre) {
        modelMap.put("nombre",nombre);
        return new ModelAndView("panel-principal",modelMap);
    }
}
