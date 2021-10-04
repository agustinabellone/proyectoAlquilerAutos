package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorUsuarioAdministrador {

    private ModelMap model = new ModelMap();
    private String viewName;
    private String mensaje;

    public ModelAndView enviarAutoAManteniminento(Auto auto, String fechaInicial, Usuario usuario) {

        if (esAdministrador(usuario)) {
            enviarElAutoAMantenimiento(auto);
        } else {
            noEnviarElAutoAMantenimientoYredirigirAlHome(usuario);
        }
        model.put("mensaje", mensaje);
        model.put("rolDelUsuario", usuario.getRol());
        return new ModelAndView(viewName, model);
    }

    private boolean esAdministrador(Usuario administrador) {
        return administrador.getRol() == "Admin";
    }

    private void enviarElAutoAMantenimiento(Auto auto) {
        viewName = "mantenimiento";
        mensaje = "El auto se envio correctamente a mantenimiento";
        model.put("kilometrosDefinidos",auto.getKm());

    }

    private void noEnviarElAutoAMantenimientoYredirigirAlHome(Usuario usuario) {
        viewName = "home";
        mensaje = "Error: no tiene permisos de administrador";
    }
}
