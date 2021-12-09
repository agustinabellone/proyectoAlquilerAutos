package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
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
                try {
                    model.put("pendientes_de_rol", obtenerListaDeUsuariosConRolPendiente());
                } catch (NoHayUsuariosPendientesDeRol e) {

                }
                try {
                    model.put("clientes_no_suscriptos", obtenerListaDeClientesNoSuscriptos());
                } catch (NoHayClientesNoSuscriptos e) {

                }
                try {
                    model.put("lista_de_suscripto", obtenerClientesSuscriptos());
                } catch (NoHayClientesSuscriptos e) {

                }
                model.put("nombre", request.getSession().getAttribute("nombre"));
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                List<Alquiler> alquilers = servicioAlquiler.obtenerAlquileresAcitvos();
                model.put("autosAlquilados", alquilers);
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
                vista = "aquilados";
                List<Auto> autosAlquilados = obtenerListaDeAutosAlquilados();
                model.put("nombre", request.getSession().getAttribute("nombre"));
                model.put("autosAlquilados", autosAlquilados);
                return setModelAndView(model, vista);
            } catch (NoHayAutosAlquiladosException e) {
                vista = "alquilados";
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
                model.put("nombre", administrador.getSession().getAttribute("nombre"));
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
                List<Usuario> usuariosEncargadosDeVolucion = obtenerListaDeUsuariosConRol("encargado");
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
                List<Usuario> usuariosMecanicos = obtenerListaDeUsuariosConRol("mecanico");
                model.put("lista_mecanicos", usuariosMecanicos);
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

    @RequestMapping(method = RequestMethod.GET, path = "/confirmar-rol")
    public ModelAndView asignarRolAlEmpleado(@RequestParam(value = "rol") String rol, @RequestParam(value = "id_usuario") Long id_usuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            vista = "asignacion-de-rol";
            String obtenido = rol;
            Usuario pendienteDeRol = servicioUsuario.buscarPorId(id_usuario);
            if (obtenido != null && pendienteDeRol != null) {
                try {
                    Usuario actualizado = servicioUsuario.asignarRol(obtenido, pendienteDeRol.getId());
                    model.put("usuario", actualizado);
                    model.put("rol", obtenido);
                    model.put("mensaje_exito", "Al usuario " + actualizado.getEmail() + " se le asigno el rol de " + obtenido);
                } catch (NoSeAsignoElRol e) {
                    model.put("error", "No se pudo asignar el rol correctamente");
                }
            } else {
                model.put("error", "No se pudo asignar el rol correctamente");
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/enviar-a-matenimiento")
    public ModelAndView enviarAMantenimiento(@RequestParam(value = "id_auto") Long id_auto, HttpServletRequest administrador) throws AutoNoExistente {
        ModelMap model = new ModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            vista = "autos_disponibles";
            Auto buscado = obtenerAuto(id_auto);
            if (buscado != null) {
                try {
                    Auto actualizado = servicioDeAuto.enviarAMantenimiento(buscado.getId());
                    model.put("autoAEnviar", actualizado);
                    model.put("mensaje_exito", "Se envio un auto correctamente el auto: " + "\n Patente: " + actualizado.getPatente() + "" + "\n Marca: " + actualizado.getMarca() + "" + "\n Modelo: " + actualizado.getModelo() + "" + "\n Kilomtraje: " + actualizado.getKm() + "" + "\n Situacion: " + actualizado.getSituacion() + "");
                } catch (NoEnviaAutoAMantenimiento e) {
                    model.put("error", "No se envio el auto a mantenimiento porque esta ocupado");
                    model.put("auto", buscado);
                }
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(model);
        }
        return setModelAndView(model, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/revisados")
    public ModelAndView mostrarHistorialDeRevisionesPorMenanico(HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            List<Revision> revionesFinalizadas = null;
            try {
                revionesFinalizadas = servicioDeAuto.obtenerRevisionesFinalizadas();
                modelMap.put("revisiones", revionesFinalizadas);
                return new ModelAndView("revisiones", modelMap);
            } catch (NoHayAutosParaRevision e) {
                modelMap.put("error", "No hay revisiones hechas actualmente");
                return new ModelAndView("revisiones", modelMap);
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(modelMap);
        }
        return setModelAndView(modelMap, vista);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/revisiones-mecanico")
    public ModelAndView mostrarRevisionesPorMecanico(@RequestParam(value = "id") Long id, HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        String vista;
        if (elRolEstaSeteadoYEsAdministrador(request)) {
            Usuario mecanico = servicioUsuario.buscarPorId(id);
            try {
                List<Revision> revisionesPorMecanico = servicioDeAuto.obtenerRevisionesPorMecanico(mecanico);
                modelMap.put("revisiones", revisionesPorMecanico);
                return new ModelAndView("revisiones-mecanico", modelMap);
            } catch (NoHayAutosParaRevision e) {
                modelMap.put("error", "No hay revisiones hechas por este usuario");
                return new ModelAndView("revisiones-mecanico", modelMap);
            }
        } else {
            vista = enviaAlaVistaDeLoginConMensajeDeError(modelMap);
        }
        return setModelAndView(modelMap, vista);
    }

    private Auto obtenerAuto(Long id_auto) throws AutoNoExistente {
        return servicioDeAuto.buscarAutoPorId(id_auto);
    }

    private boolean elRolEstaSeteadoYEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null && request.getSession().getAttribute("rol").equals("admin");
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


    public List<Usuario> obtenerListaDeUsuariosConRol(String rol) throws NoHayEmpladosException {
        return servicioUsuario.obtenerListaDeUsuariosPorRol(rol);
    }

    public List<Usuario> obtenerListaDeUsuariosConRolPendiente() throws NoHayUsuariosPendientesDeRol {
        return servicioUsuario.obtenerListaDeUsuariosPendienteDeRol();
    }

}
