package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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

    private ModelMap modelMap = getModelMap();
    private String viewName;
    private ServicioAlquiler servicioAlquiler;
    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorAdministrador(ServicioAlquiler servicioAlquiler, ServicioDeAuto servicioDeAuto, ServicioSuscripcion servicioSuscripcion, ServicioUsuario servicioUsuario) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioDeAuto = servicioDeAuto;
        this.servicioSuscripcion = servicioSuscripcion;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            this.viewName = "redirect:/panel-principal";
            return new ModelAndView(viewName);
        } else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        ModelMap model = getModelMap();
        if (this.elRolEstaSeteadoYEsAdministrador(request))
            try {
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                this.modelMap.put("nombre", request.getSession().getAttribute("nombre"));
                this.modelMap.put("autosAlquilados", autosAlquilados);
                this.viewName = "panel-principal";
                return new ModelAndView(this.viewName, this.modelMap);
            } catch (NoHayAutosAlquiladosException e) {
                this.modelMap.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
                this.viewName = "panel-principal";
                return new ModelAndView(this.viewName, this.modelMap);
            }
        else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    private ModelMap getModelMap() {
        return new ModelMap();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-alquilados")
    public ModelAndView mostrarAutosAlquilados(HttpServletRequest request) {
        if (elRolEstaSeteadoYEsAdministrador(request))
            try {
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                this.modelMap.put("nombre", request.getSession().getAttribute("nombre"));
                this.modelMap.put("autosAlquilados", autosAlquilados);
                this.viewName = "panel-principal";
                return new ModelAndView(this.viewName, this.modelMap);
            } catch (NoHayAutosAlquiladosException e) {
                this.modelMap.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
                this.viewName = "panel-principal";
                return new ModelAndView(this.viewName, this.modelMap);
            }
        else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-disponibles")
    public ModelAndView mostrarAutosDisponibles(HttpServletRequest usuarioConRol) {
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol))
            try {
                List<Auto> autosDisponibles = obtenerListaDeAutosDisponibles();
                this.modelMap.put("autosDisponibles", autosDisponibles);
                this.viewName = "autos_disponibles";
                return new ModelAndView(this.viewName, this.modelMap);
            } catch (NoHayAutosDisponiblesException e) {
                this.modelMap.put("error_sin_autos_disponibles", "No hay autos disponibles actualmente");
                this.viewName = "autos_disponibles";
                return new ModelAndView(this.viewName, this.modelMap);
            }
        else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }

    }

    public List<Auto> obtenerListaDeAutosDisponibles() throws NoHayAutosDisponiblesException {
        return servicioAlquiler.obtenerAutosDisponibles();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest usuarioConRol) {
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol))
            try {
                List<Auto> autosEnMantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
                this.modelMap.put("en_mantenimiento", autosEnMantenimiento);
                this.viewName = "autos_en_mantenimiento";
                return new ModelAndView(this.viewName, this.modelMap);
            } catch (NoHayAutosEnMantenientoException e) {
                this.modelMap.put("error_no_hay_autos_en_mantenimiento", "No hay autos en mantenimiento actualmente");
                this.viewName = "autos_en_mantenimiento";
                return new ModelAndView(this.viewName, this.modelMap);
            }
        else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clientes-suscriptos")
    public ModelAndView mostrarClientesSuscriptos(HttpServletRequest elUsuarioQueVienePorLaSesion) {
        if (elRolEstaSeteadoYEsAdministrador(elUsuarioQueVienePorLaSesion))
            try {
                List<Suscripcion> usuariosSuscriptos = obtenerClientesSuscriptos();
                this.modelMap.put("lista_de_suscriptos", usuariosSuscriptos);
                return new ModelAndView("clientes-suscriptos", modelMap);
            } catch (NoHayClientesSuscriptos e) {
                this.modelMap.put("error_no_hay_clientes_suscriptos", "No hay clientes suscriptos actualmente");
                return new ModelAndView("clientes-suscriptos", modelMap);
            }
        else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clientes-no-suscriptos")
    public ModelAndView mostrarClientesNoSuscriptos(HttpServletRequest administrador) {
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            try {
                List<Suscripcion> clientesNoSuscriptos = obtenerListaDeClientesNoSuscriptos();
                modelMap.put("clientes_no_suscriptos", clientesNoSuscriptos);
                return new ModelAndView("clientes-no-suscriptos", modelMap);
            } catch (NoHayClientesNoSuscriptos e) {
                modelMap.put("error_no_hay_clientes_no_suscriptos", "Al parecer todos los clientes estan suscriptos");
                return new ModelAndView("clientes-no-suscriptos", modelMap);
            }
        } else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/encargados-devolucion")
    public ModelAndView mostrarEmpleadosEncargadosDeDevolucion(HttpServletRequest administrador) {
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            try {
                List<Usuario> usuariosEncargadosDeVolucion = obtenerListaDeUsuariosConRol("encargadosDevolucion");
                modelMap.put("encargados_devolucion", usuariosEncargadosDeVolucion);
                return new ModelAndView("encargados-devolucion", modelMap);
            } catch (NoHayEmpladosException e) {
                modelMap.put("error_no_hay_encargados", "no hay encargados de devolucion actualmente");
                return new ModelAndView("encargados-devolucion", modelMap);
            }
        } else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mecanicos")
    public ModelAndView mostrarEmpleadosMecanicos(HttpServletRequest usuario_de_request) {
        if (elRolEstaSeteadoYEsAdministrador(usuario_de_request)) {
            try {
                List<Usuario> usuariosMecanicos = obtenerListaDeUsuariosConRol("mecanico");
                modelMap.put("mecanicos", usuariosMecanicos);
                return new ModelAndView("mecanicos", modelMap);
            } catch (NoHayEmpladosException e) {
                modelMap.put("error_no_hay_mecanicos", "No hay mecanicos actualmente");
                return new ModelAndView("mecanicos", modelMap);
            }
        } else {
            this.modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
            this.modelMap.put("datosLogin", new DatosLogin());
            this.viewName = "login";
            return new ModelAndView(this.viewName, this.modelMap);
        }
    }

    private boolean elRolEstaSeteadoYEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null && request.getSession().getAttribute("rol").equals("admin");
    }

    public List<Auto> obtenerListaDeAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return servicioDeAuto.obtenerAutosEnMantenimiento();
    }

    public List<Auto> obtenerListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        return servicioAlquiler.obtenerAutosAlquilados();
    }

    public List<Suscripcion> obtenerClientesSuscriptos() throws NoHayClientesSuscriptos {
        return servicioSuscripcion.obtenerClientesSuscriptos();
    }

    public List<Suscripcion> obtenerListaDeClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        return servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos();
    }

    public List<Usuario> obtenerListaDeUsuariosConRol(String rol) throws NoHayEmpladosException {
        return servicioUsuario.obtenerListaDeUsuariosPorRol(rol);
    }

}
