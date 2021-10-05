package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMantenimiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioMantenimientoImpl;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorEnviarAutoAMenteniminetoTest {

    private static final String FECHA_INICIAL = "3/10/21";
    private static final int KILOMETROS_DEFINIDDOS = 100;

    private ServicioMantenimiento servicioMantenimiento = mock(ServicioMantenimientoImpl.class);
    private ControladorEnviarAutoAMantenimiento controladorEnviarAutoAMantenimiento = new ControladorEnviarAutoAMantenimiento(servicioMantenimiento);
    private Usuario usuario = new Usuario();
    private ModelAndView modelAndView = new ModelAndView();
    private Auto auto = new Auto();
    private DatosEnvioAMantenimiento datosEnvioAMantenimiento = new DatosEnvioAMantenimiento(auto, FECHA_INICIAL, usuario);

    @Test
    public void queUnUsuarioConRolDeAdministradorPuedaEnviarUnAutoAMantenimiento() throws Exception {
        givenQueExisteUnUsuarioConRolAdministrador();
        givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnvioAMantenimiento);

        thenElEnvioEsExitoso("El auto se envio correctamente a mantenimiento", "mantenimiento");
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnaAutoAMantenimiento() throws Exception {
        givenUnUsuarioSinRolDeAdministrador();
        givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnvioAMantenimiento);

        thenElEnvioEsFalla("Error: no tiene permisos de administrador", "home");
    }

    @Test
    public void cuandoUnAutoLlegaAUnKilometrajeDefinidoDebeSerEnviadoAMantenimientoCorrectamente() throws Exception {
        givenQueExisteUnUsuarioConRolAdministrador();
        Auto conKmDefinidos = givenQueExisteUnAutoCon(KILOMETROS_DEFINIDDOS);

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnvioAMantenimiento);


        thenElAutoConKilometrosDefinidosEsExitoso(conKmDefinidos, "El auto se envio correctamente a mantenimiento", "mantenimiento");
    }


    @Test(expected = Exception.class)
    public void queNoSePuedaEnviarAlMismoAutoDosVecesAMantenimiento() throws Exception {
        givenQueExisteUnUsuarioConRolAdministrador();
        givenQueExisteUnAutoEnMatenimiento();

        whenEnvioElAutoAMantenimientoCon(datosEnvioAMantenimiento);

    }

    private void givenQueExisteUnAutoEnMatenimiento() throws Exception {
        doThrow(Exception.class).when(servicioMantenimiento).enviarAutoAMantenimiento(datosEnvioAMantenimiento);
    }

    private Auto givenQueExisteUnAutoCon(int kilometrosDefiniddos) {
        auto.setKm(kilometrosDefiniddos);
        return auto;
    }


    private void givenQueExisteUnUsuarioConRolAdministrador() {
        usuario.setRol("Admin");
    }

    private void givenQueExisteUnAuto() {
    }

    private void givenUnUsuarioSinRolDeAdministrador() {
        usuario.setRol("Invitado");
    }

    private ModelAndView whenEnvioElAutoAMantenimientoCon(DatosEnvioAMantenimiento datos) throws Exception {
        return controladorEnviarAutoAMantenimiento.enviarAutoAManteniminento(datos);
    }

    private void thenElEnvioEsExitoso(String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("rolDelUsuario")).isEqualTo(usuario.getRol());
    }

    private void thenElEnvioEsFalla(String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("rolDelUsuario")).isEqualTo(usuario.getRol());

    }

    private void thenElAutoConKilometrosDefinidosEsExitoso(Auto conKmDefinidos, String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("kilometrosDefinidos")).isEqualTo(conKmDefinidos.getKm());
        assertThat(modelAndView.getModel().get("rolDelUsuario")).isEqualTo(usuario.getRol());
    }
}
