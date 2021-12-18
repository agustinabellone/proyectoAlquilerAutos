package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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

    private ServicioDeAuto servicioDeAuto;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorAdministrador(ServicioDeAuto servicioDeAuto, ServicioSuscripcion servicioSuscripcion, ServicioUsuario servicioUsuario) {
        this.servicioDeAuto = servicioDeAuto;
        this.servicioSuscripcion = servicioSuscripcion;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-panel-principal")
    public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> autosAlquilados = servicioDeAuto.obtenerAutosAlquilados();
                model.put("autos_alquilados", autosAlquilados);
                return new ModelAndView("panel-principal", model);
            } catch (NoHayAutosAlquiladosException e) {
                model.put("error_no_hay_alquilados", "No hay autos alquilados actualmente");
                return new ModelAndView("panel-principal", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-disponibles-para-alquilar")
    public ModelAndView mostrarAutosDisponiblesParaAlquilar(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> autosDisponiblesParaAlquilar = servicioDeAuto.obtenerAutosDisponiblesParaAlquilar();
                model.put("autos_disponibles_para_alquilar", autosDisponiblesParaAlquilar);
                return new ModelAndView("autos-disponibles-para-alquilar", model);
            } catch (NoHayAutosDisponiblesException e) {
                model.put("error_no_hay_disponibles", "No hay autos disponibles para alquilar actualmente");
                return new ModelAndView("autos-disponibles-para-alquilar", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> enMantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
                model.put("autos_en_mantenimiento", enMantenimiento);
                return new ModelAndView("autos-en-mantenimiento", model);
            } catch (NoHayAutosEnMantenientoException e) {
                model.put("error_no_hay_en_mantenimiento", "No hay autos para mantenimiento actualmente");
                return new ModelAndView("autos-en-mantenimiento", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "autos-en-revision")
    public ModelAndView mostrarAutosEnRevision(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Auto> enRevision = servicioDeAuto.obtenerAutosEnRevision();
                model.put("autos_en_revision", enRevision);
                return new ModelAndView("autos-en-revision", model);
            } catch (NoHayAutosParaRevision e) {
                model.put("error_no_hay_en_revision", "No hay autos para revision actualmente");
                return new ModelAndView("autos-en-revision", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "clientes-suscriptos")
    public ModelAndView mostrarClientesSuscriptos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Suscripcion> clientes_suscriptos = servicioSuscripcion.obtenerClientesSuscriptos();
                model.put("clientes_suscriptos", clientes_suscriptos);
                return new ModelAndView("clientes-suscriptos", model);
            } catch (NoHayClientesSuscriptos e) {
                model.put("error_no_hay_clientes_suscriptos", "No hay clientes suscriptos actualmente");
                return new ModelAndView("clientes-suscriptos", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "clientes-no-suscriptos")
    public ModelAndView mostrarClientesNoSuscriptos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Usuario> clientes_no_suscriptos = servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos();
                model.put("clientes_no_suscriptos", clientes_no_suscriptos);
                return new ModelAndView("clientes-no-suscriptos", model);
            } catch (NoHayClientesNoSuscriptos e) {
                model.put("error_no_hay_sin_suscripcion", "No hay clientes sin suscripcion actualmente");
                return new ModelAndView("clientes-no-suscriptos", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "encargados-devolucion")
    public ModelAndView mostrarEncargadosDeDevolucion(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Usuario> encargados_devolucion = servicioUsuario.obtenerListaDeUsuariosPorRol("encargado");
                model.put("encargados_devolucion", encargados_devolucion);
                return new ModelAndView("encargados-devolucion", model);
            } catch (NoHayEmpladosException e) {
                model.put("error_no_hay_encargados_devolucion", "No hay encargados actualmente");
                return new ModelAndView("encargados-devolucion", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "mecanicos")
    public ModelAndView mostrarMecanicos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Usuario> mecanicos = servicioUsuario.obtenerListaDeUsuariosPorRol("mecanico");
                model.put("empleados_mecanicos", mecanicos);
                return new ModelAndView("mecanicos", model);
            } catch (NoHayEmpladosException e) {
                model.put("error_no_hay_mecanicos", "No hay mecanicos registrados actualmente");
                return new ModelAndView("mecanicos", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "administradores")
    public ModelAndView mostrarAdministradores(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Usuario> administradores = servicioUsuario.obtenerListaDeUsuariosPorRol("admin");
                model.put("empleados_administradores", administradores);
                return new ModelAndView("administradores", model);
            } catch (NoHayEmpladosException e) {
                model.put("error_no_hay_administradores", "No hay administradores registrados actualmente");
                return new ModelAndView("administradores", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "empleados-pendientes-de-rol")
    public ModelAndView mostrarEmpleadosPendientesDeRol(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                List<Usuario> pendientes_de_rol = servicioUsuario.obtenerListaDeUsuariosPorRol("empleado");
                model.put("empleados_pendientes_de_rol", pendientes_de_rol);
                return new ModelAndView("asignacion-de-rol", model);
            } catch (NoHayEmpladosException e) {
                model.put("error_no_hay_empledos_pendientes_de_rol", "No hay empleados pendientes de rol actualmente");
                return new ModelAndView("asignacion-de-rol", model);
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "asignacion-de-rol")
    public ModelAndView asignarRol(@RequestParam(value = "id_usuario") Long id_usuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esAdiministrador(request)) {
            try {
                Usuario paraAsignarleElRol = servicioUsuario.buscarPorId(id_usuario);
                model.put("usuario_con_rol_asignado", paraAsignarleElRol);
                return new ModelAndView("asignacion-de-rol", model);
            } catch (NoHayEmpladosException e) {
                e.printStackTrace();
            }
        }
        return enviarAlLoginConUnMensajeDeError(model);
    }

    private boolean esAdiministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private ModelAndView enviarAlLoginConUnMensajeDeError(ModelMap model) {
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", model);
    }
}
