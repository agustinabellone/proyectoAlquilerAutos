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

public class testControladorEnviarAutoAMentenimineto {

    @Test
    public void queUnUsuarioConRolDeAdministradorPuedaEnviarUnAutoAMantenimiento() throws Exception {
        Usuario administrador = givenQueExisteUnUsuarioConRolDe(ADMINISTRADOR);
        givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnviadosPorUsuarioAdministrador);

        thenElEnvioEsExitoso(administrador, "El auto se envio correctamente a mantenimiento", "mantenimiento");
    }

    @Test
    public void queUnUsuarioSinRolDeAdministradorNoPuedaEnviarUnaAutoAMantenimiento() throws Exception {
        Usuario invitado = givenQueExisteUnUsuarioConRolDe(INVITADO);
        givenQueExisteUnAuto();

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnviadosPorUsuarioInvitado);

        thenElEnvioEsFalla(invitado, "No se envio correctamente ya que el usuario no es administrador", "lista-de-autos");
    }

    @Test
    public void cuandoUnAutoLlegaAUnKilometrajeDefinidoDebeSerEnviadoAMantenimientoCorrectamente() throws Exception {
        givenQueExisteUnUsuarioConRolDe(ADMINISTRADOR);
        Auto conKmDefinidos = givenQueExisteUnAutoCon(KILOMETROS_DEFINIDDOS);

        modelAndView = whenEnvioElAutoAMantenimientoCon(datosEnviadosPorUsuarioAdministrador);

        thenElAutoConKilometrosDefinidosEsExitoso(conKmDefinidos, "El auto se envio correctamente a mantenimiento", "mantenimiento");
    }


    @Test(expected = Exception.class)
    public void queNoSePuedaEnviarAlMismoAutoDosVecesAMantenimiento() throws Exception {
        Usuario administrador = givenQueExisteUnUsuarioConRolDe(ADMINISTRADOR);
        givenQueExisteUnAutoEnMatenimiento();
        whenEnvioElAutoAMantenimientoCon(datosEnviadosPorUsuarioAdministrador);
        thenElEnvioEsFalla(administrador, "No se envio correctamente ya que existe un mismo auto en matenimiento", "lista-de-autos");
    }

    private Usuario givenQueExisteUnUsuarioConRolDe(Usuario usuario) {
        return usuario;
    }

    private Auto givenQueExisteUnAuto() {
        return AUTO;
    }

    private Auto givenQueExisteUnAutoCon(int kilometrosDefiniddos) {
        AUTO.setKilometros(100);
        return AUTO;
    }

    private void givenQueExisteUnAutoEnMatenimiento() throws Exception {
        doThrow(Exception.class).when(servicioMantenimiento).enviar(datosEnviadosPorUsuarioAdministrador.getAuto(),datosEnviadosPorUsuarioAdministrador.getFechaInicial());
    }

    private ModelAndView whenEnvioElAutoAMantenimientoCon(DatosEnvioAMantenimiento datos) throws Exception {
        return controladorEnviarAutoAMantenimiento.enviarAutoAManteniminento(datos);
    }

    private void thenElEnvioEsExitoso(Usuario usuario, String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("usuario")).isEqualTo(usuario.getRol());
    }

    private void thenElEnvioEsFalla(Usuario usuario, String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("usuario")).isEqualTo(usuario.getRol());

    }

    private void thenElAutoConKilometrosDefinidosEsExitoso(Auto conKmDefinidos, String mensaje, String viewName) {
        assertThat(modelAndView.getViewName()).isEqualTo(viewName);
        assertThat(modelAndView.getModel().get("mensaje")).isEqualTo(mensaje);
        assertThat(modelAndView.getModel().get("km-del-auto")).isEqualTo(conKmDefinidos.getKilometros());
    }

    private static final String FECHA_INICIAL = "3/10/21";
    private static final int KILOMETROS_DEFINIDDOS = 100;
    private static Usuario INVITADO = new Usuario("Invitado");
    private static Usuario ADMINISTRADOR = new Usuario("Admin");
    private static Auto AUTO = new Auto();

    private ServicioMantenimiento servicioMantenimiento = mock(ServicioMantenimientoImpl.class);
    private ModelAndView modelAndView = new ModelAndView();
    private ControladorEnviarAutoAMantenimiento controladorEnviarAutoAMantenimiento
            = new ControladorEnviarAutoAMantenimiento(servicioMantenimiento);
    private DatosEnvioAMantenimiento datosEnviadosPorUsuarioAdministrador
            = new DatosEnvioAMantenimiento(AUTO, FECHA_INICIAL, ADMINISTRADOR);
    private DatosEnvioAMantenimiento datosEnviadosPorUsuarioInvitado
            = new DatosEnvioAMantenimiento(AUTO, FECHA_INICIAL, INVITADO);
}
