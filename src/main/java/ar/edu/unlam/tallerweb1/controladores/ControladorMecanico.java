package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.AutoYaEnRevision;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosParaRevision;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class ControladorMecanico {

    private ModelMap modelMap = new ModelMap();
    private String vista;
    private ServicioDeAuto servicioDeAuto;

    @Autowired
    public ControladorMecanico(ServicioDeAuto servicioDeAuto) {
        this.servicioDeAuto = servicioDeAuto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/para-mantenimiento")
    public ModelAndView mostrarListadoDeAutosParaMantenimiento(HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request))
            accedeALaVistaDeAutosParaMantenimientoMostrandoUnaListaSiNoLanzaUnaExceptionMostrandoUnMensaje(request);
        else siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        return new ModelAndView(vista, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/revisar-auto/patente/${patente}")
    public ModelAndView enviarARevision(@PathVariable String patente, HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request)) {
            redirigeAlaVistaDeAutosEnMantenimientoConMensajeDeExitoSiNoLanzaUnaExceptionMostrandoUnMensaje(patente, request);
        } else {
            siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        }
        return new ModelAndView(vista, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/en-revision")
    public ModelAndView mostrarListaDeAutosEnRevision(HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request)) {
            vista = "en-revision";
            try {
                List<Auto> para_revision = servicioDeAuto.obtenerAutosEnRevision();
                modelMap.put("para_revision", para_revision);
            } catch (NoHayAutosParaRevision e) {
                modelMap.put("error_no_hay_autos_para_revision", "No hay autos para revision actualmente");
            }
        } else {
            siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        }
        return new ModelAndView(vista, modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/formulario-revision/id-auto/${id_auto}")
    public ModelAndView completarFormularioDeRevision(@PathVariable Long id_auto, HttpServletRequest request) {
        if (esMecanico(request) && estaSeteadoElRol(request)) {
            try {
                vista = "formulario-revision";
                Auto para_revision = servicioDeAuto.buscarAutoPorId(id_auto);
                modelMap.put("auto_para_revision", para_revision);
            } catch (AutoNoExistente e) {
                vista = "en-revision";
                modelMap.put("error_no_existe_auto", "No existe el auto que queres revisar");
            }
        } else {
            siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError();
        }
        return new ModelAndView(vista, modelMap);
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return !(Objects.isNull(request.getSession().getAttribute("rol")));
    }

    private boolean esMecanico(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("mecanico");
    }

    private void siNoEsMecanicoElUsuarioLoEnviaAlLoginConMensajeDeError() {
        vista = "login";
        modelMap.put("datosLogin", new DatosLogin());
        modelMap.put("errorSinPermisos", "No tienes los permisos necesarios para acceder a esta pagina");
    }

    private void accedeALaVistaDeAutosParaMantenimientoMostrandoUnaListaSiNoLanzaUnaExceptionMostrandoUnMensaje(HttpServletRequest request) {
        Long id_mecanico = obtenerIdMecanicoQueVieneComoVariableDeSesion(request);
        String patente = obtenerPatenteDelAutoQueVieneComoVariableDeSesion(request);
        String autoInexistente = getError_no_existeQueVieneComoVariableDeSession(request);
        String autoEnRevision = getError_ya_existeQueVieneComoVariableDeSesion(request);
        vista = "en-mantenimiento";
        try {
            obtieneLosAutosParaMantenimientoYVerificaQueNoHayErrores(id_mecanico, patente, autoInexistente, autoEnRevision);
        } catch (NoHayAutosEnMantenientoException e) {
            enviaMensajeDeErrorQueNoHayAutosParaMantenimientoAlaVista();
        }
    }

    private void redirigeAlaVistaDeAutosEnMantenimientoConMensajeDeExitoSiNoLanzaUnaExceptionMostrandoUnMensaje(String patente, HttpServletRequest request) {
        vista = "redirect:/para-mantenimiento";
        try {
            obtieneElAutosParaRevisarPorPatenteYLoEnviaARevision(patente, request);
        } catch (AutoNoExistente e) {
            siNoExisteElAutosObtenidoPorPatenteEnviarPorVariableDeSesionUnMensajeDeError(request);
        } catch (AutoYaEnRevision e) {
            siElAutoObtenidoYaEstaEnRevisionSeEnviarPorVariableDeSesionUnaMensajeDeError(request);
        }
    }

    private void siElAutoObtenidoYaEstaEnRevisionSeEnviarPorVariableDeSesionUnaMensajeDeError(HttpServletRequest request) {
        request.getSession().setAttribute("error_ya_existe", "No se puede enviar un auto a revision que ya esta enviado");
    }

    private void siNoExisteElAutosObtenidoPorPatenteEnviarPorVariableDeSesionUnMensajeDeError(HttpServletRequest request) {
        request.getSession().setAttribute("error_no_existe", "No existe el auto que queres mandar");
    }

    private void siLaPatenteEnviadaPorVariableDeSesionExisteMandarMensajeDeExito(String patente) {
        if (patente != null)
            modelMap.put("auto_enviado", "Se envio correctamente el auto: \n" + "Patente: " + patente);
    }

    private void obtieneElAutosParaRevisarPorPatenteYLoEnviaARevision(String patente, HttpServletRequest request) throws AutoNoExistente, AutoYaEnRevision {
        Auto paraRevision = servicioDeAuto.buscarAutoPorPatente(patente);
        Long id_mecanico = obtenerIdMecanicoQueVieneComoVariableDeSesion(request);
        servicioDeAuto.enviarARevision(paraRevision.getPatente(), id_mecanico);
        request.getSession().setAttribute("patente", paraRevision.getPatente());
    }

    private void siEstanSeteadosLosErroresQueVienenEnVariablesDeSesionEnviarlosALaVista(String autoInexistente, String autoEnRevision) {
        if (autoInexistente != null) modelMap.put("error_no_existe", autoInexistente);
        if (autoEnRevision != null) modelMap.put("error_ya_existe", autoEnRevision);
    }

    private Long obtenerIdMecanicoQueVieneComoVariableDeSesion(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute("id");
    }

    private String obtenerPatenteDelAutoQueVieneComoVariableDeSesion(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("patente");
    }

    private String getError_no_existeQueVieneComoVariableDeSession(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("error_no_existe");
    }

    private String getError_ya_existeQueVieneComoVariableDeSesion(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("error_ya_existe");
    }

    private void obtieneLosAutosParaMantenimientoYVerificaQueNoHayErrores(Long id_mecanico, String patente, String autoInexistente, String autoEnRevision) throws NoHayAutosEnMantenientoException {
        List<Auto> para_mantenimiento = servicioDeAuto.obtenerAutosEnMantenimiento();
        modelMap.put("para_mantenimiento", para_mantenimiento);
        modelMap.put("mecanico", id_mecanico);
        siLaPatenteEnviadaPorVariableDeSesionExisteMandarMensajeDeExito(patente);
        siEstanSeteadosLosErroresQueVienenEnVariablesDeSesionEnviarlosALaVista(autoInexistente, autoEnRevision);
    }

    private void enviaMensajeDeErrorQueNoHayAutosParaMantenimientoAlaVista() {
        modelMap.put("error", "No hay autos para mantenimiento actualmente");
    }
}
