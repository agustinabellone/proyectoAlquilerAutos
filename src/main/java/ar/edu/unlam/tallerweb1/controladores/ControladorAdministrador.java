package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosAlquiladosException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosDisponiblesException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayClientesSuscriptos;
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

    private ModelMap modelMap = new ModelMap();
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
        if (elRolEstaSeteadoYEsAdministrador(request))
            return enviarAlPanelPrincipal();
        else return enviarALoginConMensajeDeError();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/panel-principal")
    public ModelAndView mostrarElPanelPrincipalConLaInformacionDelAdministrador(HttpServletRequest request) {
        if (this.elRolEstaSeteadoYEsAdministrador(request))
            return this.intentaMostrarLaVistaDelPanelPrincipalSiNoLanzaUnException(request);
        else return this.enviarALoginConMensajeDeError();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-alquilados")
    public ModelAndView mostrarAutosAlquilados(HttpServletRequest request) {
        if (elRolEstaSeteadoYEsAdministrador(request))
            return this.intentaMostrarLaVistaDelPanelPrincipalSiNoLanzaUnException(request);
        else return this.enviarALoginConMensajeDeError();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-disponibles")
    public ModelAndView mostrarAutosDisponibles(HttpServletRequest usuarioConRol) {
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol))
            return intentaMostrarLaVistaDeLosAutosDisponiblesSiNoLanzaUnaException();
        else return enviarALoginConMensajeDeError();

    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-en-mantenimiento")
    public ModelAndView mostrarAutosEnMantenimiento(HttpServletRequest usuarioConRol) {
        if (elRolEstaSeteadoYEsAdministrador(usuarioConRol))
            return intentaMostrarLaVistaDeLosAutosEnMantenimientoSiNoLanzaUnaException();
        else return enviarALoginConMensajeDeError();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clientes-suscriptos")
    public ModelAndView mostrarClientesSuscriptos(HttpServletRequest elUsuarioQueVienePorLaSesion) {
        if (elRolEstaSeteadoYEsAdministrador(elUsuarioQueVienePorLaSesion))
            return intentaMostrarLaVistaDeLosClientesSuscriptosConLaLista();
        else
            return enviarALoginConMensajeDeError();
    }

    private ModelAndView intentaMostrarLaVistaDeLosClientesSuscriptosConLaLista() {
        try {
            return enviaALaVistaDeLosClientesSuscriptosMotrandoUnaLista();
        } catch (NoHayClientesSuscriptos e) {
            return enviaALaVistaDeLosClientesSuscriptosConMensajeDeError();
        }
    }

    private ModelAndView enviaALaVistaDeLosClientesSuscriptosConMensajeDeError() {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("error_no_hay_clientes_suscriptos", "No hay clientes suscriptos actualmente");
        return new ModelAndView("clientes-suscriptos", modelMap);
    }

    private ModelAndView enviaALaVistaDeLosClientesSuscriptosMotrandoUnaLista() throws NoHayClientesSuscriptos {
        List<Suscripcion> usuariosSuscriptos = this.obtenerListaDeClientesSuscriptos();
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("lista_de_suscriptos", usuariosSuscriptos);
        return new ModelAndView("clientes-suscriptos", modelMap);
    }

    private ModelAndView enviarAlPanelPrincipal() {
        this.viewName = "redirect:/panel-principal";
        return getView(viewName);
    }

    private ModelAndView enviarALoginConMensajeDeError() {
        guardaElMensajeParaPasarloALaVista(this.modelMap);
        this.viewName = "login";
        return getModelAndView(this.modelMap, this.viewName);
    }

    private ModelAndView intentaMostrarLaVistaDelPanelPrincipalSiNoLanzaUnException(HttpServletRequest request) {
        try {
            return enviaAlPanelPrincipalMostrandoListaDeAutosAlquilados(request);
        } catch (NoHayAutosAlquiladosException e) {
            return enviaAlPanelPrincipalConMensajeDeError();
        }
    }

    private ModelAndView enviaAlPanelPrincipalConMensajeDeError() {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("error_no_hay_autos_alquilados", "No hay autos alquilados actualmente");
        this.viewName = "panel-principal";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private ModelAndView enviaAlPanelPrincipalMostrandoListaDeAutosAlquilados(HttpServletRequest request) throws
            NoHayAutosAlquiladosException {
        List<Auto> autosAlquilados = this.obtenerListaDeAutosAlquilados();
        this.guardaElNombreDelUsuarioQueIniciaSesionYLaListaDeAutosAlquilados(request, autosAlquilados);
        this.viewName = "panel-principal";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private void guardaElMensajeParaPasarloALaVista(ModelMap modelMap) {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("datosLogin", new DatosLogin());
    }

    private void guardaElNombreDelUsuarioQueIniciaSesionYLaListaDeAutosAlquilados(HttpServletRequest
                                                                                          request, List<Auto> autosAlquilados) {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("nombre", request.getSession().getAttribute("nombre"));
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("autosAlquilados", autosAlquilados);
    }

    private void guardaLaClaveYElValorEnElMapQueSePasaEnLaVista(String nombreDeLaClave, Object valor) {
        this.modelMap.put(nombreDeLaClave, valor);
    }

    private ModelAndView intentaMostrarLaVistaDeLosAutosEnMantenimientoSiNoLanzaUnaException() {
        try {
            return enviarALaVistaDeLosAutosEnMantenimientoMostrandoUnaLista();
        } catch (NoHayAutosEnMantenientoException e) {
            return enviaALaVistaDeLosAutosEnMantenimientoConMensajeDeError();
        }
    }

    private ModelAndView enviaALaVistaDeLosAutosEnMantenimientoConMensajeDeError() {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("error_no_hay_autos_en_mantenimiento", "No hay autos en mantenimiento actualmente");
        this.viewName = "autos_en_mantenimiento";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private ModelAndView enviarALaVistaDeLosAutosEnMantenimientoMostrandoUnaLista() throws
            NoHayAutosEnMantenientoException {
        List<Auto> autosEnMantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("en_mantenimiento", autosEnMantenimiento);
        this.viewName = "autos_en_mantenimiento";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private ModelAndView intentaMostrarLaVistaDeLosAutosDisponiblesSiNoLanzaUnaException() {
        try {
            return enviarALaVistaDeAutosDisponiblesMostrandolaListaDeAutosDisponibles();
        } catch (NoHayAutosDisponiblesException e) {
            return enviaALaVistaDeAutosDisponiblesConMensajeDeError();
        }
    }

    private ModelAndView enviaALaVistaDeAutosDisponiblesConMensajeDeError() {
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("error_sin_autos_disponibles", "No hay autos disponibles actualmente");
        this.viewName = "disponibles";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private ModelAndView enviarALaVistaDeAutosDisponiblesMostrandolaListaDeAutosDisponibles() throws
            NoHayAutosDisponiblesException {
        List<Auto> autosDisponibles = this.obtenerListaDeAutosDisponibles();
        guardaLaClaveYElValorEnElMapQueSePasaEnLaVista("autosDisponibles", autosDisponibles);
        this.viewName = "disponibles";
        return this.getModelAndView(this.modelMap, this.viewName);
    }

    private boolean elRolEstaSeteadoYEsAdministrador(HttpServletRequest request) {
        return elRolEstaSeteado(request) && elRolEsAdministrador(request);
    }

    private boolean elRolEstaSeteado(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean elRolEsAdministrador(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("admin");
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

    public List<Suscripcion> obtenerListaDeClientesSuscriptos() throws NoHayClientesSuscriptos {
        return servicioSuscripcion.obtenerClientesSuscriptos();
    }

    private ModelAndView getView(String viewName) {
        return new ModelAndView(viewName);
    }

    private ModelAndView getModelAndView(ModelMap modelMap, String viewName) {
        return new ModelAndView(viewName, modelMap);
    }

    public ModelAndView mostrarClientesNoSuscriptos(HttpServletRequest administrador) {
        if (elRolEstaSeteadoYEsAdministrador(administrador)) {
            List<Suscripcion> clientesNoSuscriptos = this.obtenerListaDeClientesSinSuscripcion();
            modelMap.put("clientes_no_suscriptos", clientesNoSuscriptos);
            return new ModelAndView("clientes-no-suscriptos",modelMap);
        } else {
            return enviarALoginConMensajeDeError();
        }
    }

    public List<Suscripcion> obtenerListaDeClientesSinSuscripcion() {
        return servicioSuscripcion.obtenerListaDeUsuariosNoSuscriptos();
    }
}
