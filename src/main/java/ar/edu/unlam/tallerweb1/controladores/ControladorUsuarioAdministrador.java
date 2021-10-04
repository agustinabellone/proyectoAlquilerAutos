package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorUsuarioAdministrador {

    private ModelMap model = new ModelMap();
    private String viewName;
    private String mensaje;

    public ModelAndView enviarAutoAManteniminento(DatosEnvioAMantenimiento datosEnvioAMantenimiento) {

        if (esAdministrador(datosEnvioAMantenimiento.getUsuario())) {
            enviarElAutoAMantenimientoCon(datosEnvioAMantenimiento);
        } else {
            noEnviarElAutoAMantenimientoYredirigirAlHome();
        }

        model.put("mensaje", mensaje);
        model.put("rolDelUsuario", datosEnvioAMantenimiento.getUsuario().getRol());
        return new ModelAndView(viewName, model);
    }

    private boolean esAdministrador(Usuario administrador) {
        return administrador.getRol() == "Admin";
    }

    private void enviarElAutoAMantenimientoCon(DatosEnvioAMantenimiento datos) {
        viewName = "mantenimiento";
        mensaje = "El auto se envio correctamente a mantenimiento";
        model.put("kilometrosDefinidos", datos.getAuto().getKm());
        model.put("fechaDeEnvioAMantenimiento", datos.getFechaInicial());
    }

    private void noEnviarElAutoAMantenimientoYredirigirAlHome() {
        viewName = "home";
        mensaje = "Error: no tiene permisos de administrador";
    }
}
