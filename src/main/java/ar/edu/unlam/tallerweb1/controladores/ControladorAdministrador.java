package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Rol;
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

    public ControladorAdministrador() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irALaVistaPrincipal(HttpServletRequest request) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            vista = "redirect:/panel-principal";
            return new ModelAndView(vista);
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
            return setModelAndView(model, vista);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        ModelMap model = getModelMap();
        String vista;
        if (this.elRolEstaSeteadoYEsAdministrador(request)) {
            try {
                vista = "panel-principal";
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                model.put("autosAlquilados", autosAlquilados);
                model.put("nombre", request.getSession().getAttribute("nombre"));
            } catch (NoHayAutosAlquiladosException e) {
                vista = "panel-principal";
                model.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-alquilados")
    public ModelAndView mostrarAutosAlquilados(HttpServletRequest request) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            try {
                vista = "panel-principal";
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                model.put("nombre", request.getSession().getAttribute("nombre"));
                model.put("autosAlquilados", autosAlquilados);
                return setModelAndView(model, vista);
            } catch (NoHayAutosAlquiladosException e) {
                vista = "panel-principal";
                model.put("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-disponibles")
    public ModelAndView mostrarAutosDisponibles(HttpServletRequest usuarioConRol) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol)) {
            try {
                vista = "autos_disponibles";
                List<Auto> autosDisponibles = obtenerListaDeAutosDisponibles();
                model.put("autosDisponibles", autosDisponibles);
            } catch (NoHayAutosDisponiblesException e) {
                vista = "autos_disponibles";
                model.put("error_sin_autos_disponibles", "No hay autos disponibles actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest usuarioConRol) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol)) {
            try {
                vista = "autos_en_mantenimiento";
                List<Auto> autosEnMantenimiento = obtenerListaDeAutosEnMantenimiento();
                model.put("en_mantenimiento", autosEnMantenimiento);
            } catch (NoHayAutosEnMantenientoException e) {
                model.put("error_no_hay_autos_en_mantenimiento", "No hay autos en mantenimiento actualmente");
                vista = "autos_en_mantenimiento";
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clientes-suscriptos")
    public ModelAndView mostrarClientesSuscriptos(HttpServletRequest elUsuarioQueVienePorLaSesion) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(elUsuarioQueVienePorLaSesion)) {
            try {
                vista = "clientes-suscriptos";
                List<Suscripcion> usuariosSuscriptos = obtenerClientesSuscriptos();
                model.put("lista_de_suscriptos", usuariosSuscriptos);
            } catch (NoHayClientesSuscriptos e) {
                vista = "clientes-suscriptos";
                model.put("error_no_hay_clientes_suscriptos", "No hay clientes suscriptos actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clientes-no-suscriptos")
    public ModelAndView mostrarClientesNoSuscriptos(HttpServletRequest administrador) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            try {
                vista = "clientes-no-suscriptos";
                List<Usuario> clientesNoSuscriptos = obtenerListaDeClientesNoSuscriptos();
                model.put("clientes_no_suscriptos", clientesNoSuscriptos);
            } catch (NoHayClientesNoSuscriptos e) {
                vista = "clientes-no-suscriptos";
                model.put("error_no_hay_clientes_no_suscriptos", "Al parecer todos los clientes estan suscriptos");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/encargados-devolucion")
    public ModelAndView mostrarEmpleadosEncargadosDeDevolucion(HttpServletRequest administrador) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            try {
                vista = "encargados-devolucion";
                List<Usuario> usuariosEncargadosDeVolucion = obtenerListaDeUsuariosConRol(Rol.ENCARGADO_DEVOLUCION);
                model.put("encargados_devolucion", usuariosEncargadosDeVolucion);
            } catch (NoHayEmpladosException e) {
                vista = "encargados-devolucion";
                model.put("error_no_hay_encargados", "no hay encargados de devolucion actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mecanicos")
    public ModelAndView mostrarEmpleadosMecanicos(HttpServletRequest usuario_de_request) {
        ModelMap model = getModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(usuario_de_request)) {
            try {
                vista = "mecanicos";
                List<Usuario> usuariosMecanicos = obtenerListaDeUsuariosConRol(Rol.MECANICO);
                model.put("mecanicos", usuariosMecanicos);
            } catch (NoHayEmpladosException e) {
                vista = "mecanicos";
                model.put("error_no_hay_mecanicos", "No hay mecanicos actualmente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/asignacion-de-rol")
    public ModelAndView mostrarAsignacionDeRol(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            try {
                vista = "asignacion-de-rol";
                List<Usuario> pendientesDeRol = obtenerListaDeUsuariosConRolPendiente();
                model.put("pendientes_de_rol", pendientesDeRol);
            } catch (NoHayUsuariosPendientesDeRol e) {
                vista = "asignacion-de-rol";
                model.put("error_no_hay_pendientes_de_rol", "No hay usuarios pendientes de rol");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    private boolean elRolEstaSeteadoYEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null && request.getSession().getAttribute("rol").equals(Rol.ADMIN);
    }

    private ModelMap getModelMap() {
        return new ModelMap();
    }

    private ModelAndView setModelAndView(ModelMap model, String vista) {
        return new ModelAndView(vista, model);
    }

    private String enviaAlaVistaDeLoginConMensajeDeError(ModelMap model) {
        String vista;
        model.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
        model.put("datosLogin", new DatosLogin());
        vista = "login";
        return vista;
    }

    public List<Auto> obtenerListaDeAutosAlquilados() throws NoHayAutosAlquiladosException {
        return servicioAlquiler.obtenerAutosAlquilados();
    }

    public List<Auto> obtenerListaDeAutosDisponibles() throws NoHayAutosDisponiblesException {
        return servicioAlquiler.obtenerAutosDisponibles();
    }

    public List<Auto> obtenerListaDeAutosEnMantenimiento() throws NoHayAutosEnMantenientoException {
        return servicioDeAuto.obtenerAutosEnMantenimiento();
    }

    public List<Suscripcion> obtenerClientesSuscriptos() throws NoHayClientesSuscriptos {
        return servicioSuscripcion.obtenerClientesSuscriptos();
    }

    public List<Usuario> obtenerListaDeClientesNoSuscriptos() throws NoHayClientesNoSuscriptos {
        return servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos();
    }

    public List<Usuario> obtenerListaDeUsuariosConRol(Rol rol) throws NoHayEmpladosException{
        return servicioUsuario.obtenerListaDeUsuariosPorRol(rol);
    }

    public List<Usuario> obtenerListaDeUsuariosConRolPendiente() throws NoHayUsuariosPendientesDeRol {
        return servicioUsuario.obtenerListaDeUsuariosPendienteDeRol();
    }
}
