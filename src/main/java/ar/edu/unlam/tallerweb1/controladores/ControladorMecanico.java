package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.AutoNoExistente;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosEnMantenientoException;
import ar.edu.unlam.tallerweb1.Exceptions.NoHayAutosParaRevision;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Revision;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorMecanico {

    private ServicioDeAuto servicioAuto;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorMecanico(ServicioDeAuto servicio, ServicioUsuario servicioUsuario) {
        this.servicioAuto = servicio;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-pantalla-principal")
    public ModelAndView irAPantallaPrincipal(HttpServletRequest mecanico) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(mecanico) && esMecanico(mecanico)) {
            model.put("nombre", mecanico.getSession().getAttribute("nombre"));
            return new ModelAndView("redirect:/pantalla-principal");
        }
        return enviarAlLoginConMensajeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pantalla-principal")
    public ModelAndView mostrarAutosParaMantenimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esMecanico(request)) {
            try {
                List<Auto> paraMantenimiento = servicioAuto.obtenerAutosEnMantenimiento();
                model.put("lista_autos_mantenimiento", paraMantenimiento);
                return new ModelAndView("pantalla-principal-mecanico", model);
            } catch (NoHayAutosEnMantenientoException e) {
                model.put("error", "No hay autos para mantenimiento actualmente");
                return new ModelAndView("pantalla-principal-mecanico", model);
            }
        }
        return enviarAlLoginConMensajeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/enviar-a-revision")
    public ModelAndView enviarAutoARevision(@RequestParam(value = "id_auto") Long id_auto, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esMecanico(request)) {
            try {
                Usuario mecanico = servicioUsuario.buscarPorId((Long) request.getSession().getAttribute("id"));
                Auto queVienePorRequestParam = servicioAuto.buscarAutoPorId(id_auto);
                Auto conSituacionActualizada = servicioAuto.enviarARevision(queVienePorRequestParam, mecanico, LocalDate.now());
                model.put("envio_exitoso", "El auto se envio correctamente");
                model.put("auto_en_revision", conSituacionActualizada);
                return new ModelAndView("envio-a-revision", model);
            } catch (AutoNoExistente e) {
                model.put("error_no_existe_el_auto", "No existe el auto que queres enviar a revision");
                return new ModelAndView("envio-a-revision", model);
            }
        }
        return enviarAlLoginConMensajeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/autos-en-revision")
    public ModelAndView mostrarAutosParaRevision(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esMecanico(request)) {
            try {
                List<Auto> enRevision = servicioAuto.obtenerAutosEnRevision();
                model.put("lista_de_autos_en_revision", enRevision);
                return new ModelAndView("autos-en-revision", model);
            } catch (NoHayAutosParaRevision e) {
                model.put("error", "No hay autos para revision actualmente");
                return new ModelAndView("autos-en-revision", model);
            }
        }
        return enviarAlLoginConMensajeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/formulario-revision")
    public ModelAndView mostrarFormularioParaFinaLizarLaRevision(@RequestParam(value = "id_auto") Long id_auto, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esMecanico(request)) {
            try {
                Auto queVienePorRequestParam = servicioAuto.buscarAutoPorId(id_auto);
                model.put("auto_para_formulario", queVienePorRequestParam);
                return new ModelAndView("formulario-completar-revision", model);
            } catch (AutoNoExistente e) {
                model.put("error", "No existe el auto con el cual queres completar el formulario de revision");
                return new ModelAndView("formulario-completar-revision", model);
            }
        }
        return enviarAlLoginConMensajeError(model);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/finalizar-revision")
    public ModelAndView finalizarFormularioDeRevision(@RequestParam(value = "id_auto") Long id_auto, @RequestParam(value = "comentario") String comentario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (estaSeteadoElRol(request) && esMecanico(request)) {
            try {
                String comentarioAGuardar = servicioAuto.estaVacioElComentario(comentario);
                LocalDate fecha_fin_revision = LocalDate.now();
                Auto queVienePorRequestParam = servicioAuto.buscarAutoPorId(id_auto);
                Revision conSituacionActualizada = servicioAuto.finalizarRevision(queVienePorRequestParam, fecha_fin_revision, comentarioAGuardar);
                model.put("auto_con_situacion_actualizada", conSituacionActualizada.getAuto());
                model.put("usuario_mecanico", conSituacionActualizada.getUsuario());
                model.put("fecha_fin_revision", conSituacionActualizada.getFechaFinRevision());
                model.put("formulario_exitoso", "Se envio correctamente el formulario y el auto esta diponibles para alquiler nuevamente");
                return new ModelAndView("finaliza-formulario-revision", model);
            } catch (AutoNoExistente e) {
                model.put("error_no_existe_auto", "No existe el auto con el cual vas a finlizar la revision");
                return new ModelAndView("finaliza-formulario-revision", model);
            }
        }
        return enviarAlLoginConMensajeError(model);
    }

    private boolean estaSeteadoElRol(HttpServletRequest request) {
        return request.getSession().getAttribute("rol") != null;
    }

    private boolean esMecanico(HttpServletRequest request) {
        return request.getSession().getAttribute("rol").equals("mecanico");
    }

    private ModelAndView enviarAlLoginConMensajeError(ModelMap model) {
        model.put("errorSinPermisos", "No tenes los permisos necesarios para acceder a esta pagina");
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", model);
    }
}
