package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMantenimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioMantenimientoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorEnviarAutoAMantenimiento {

    private ModelMap model = new ModelMap();
    private String viewName;
    private String mensaje;
    private ServicioMantenimiento servicioMantenimiento;

    @Autowired
    public ControladorEnviarAutoAMantenimiento(ServicioMantenimiento servicioMantenimiento){
        this.servicioMantenimiento = servicioMantenimiento;
    }

    public ModelAndView enviarAutoAManteniminento(DatosEnvioAMantenimiento datosEnvioAMantenimiento) throws Exception {

        if (esAdministrador(datosEnvioAMantenimiento.getUsuario())) {
            try {
                enviarElAutoAMantenimientoCon(datosEnvioAMantenimiento);
                servicioMantenimiento.enviarAutoAMantenimiento(datosEnvioAMantenimiento);
            } catch (Exception e) {
                throw new Exception();
            }
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
