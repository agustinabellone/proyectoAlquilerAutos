package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestControladorAsignacionDeRol {

    private static final String ADMIN = "admin";
    private ModelAndView modelAndView = new ModelAndView();
    private ControladorAdministrador controladorAdministrador = new ControladorAdministrador();
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void queElUsuarioConRolDeAdministradorPuedaAccederAlASeccionDeAsignarRoles() {
        HttpServletRequest request = givenExisteUnUsuarioConRolDe(ADMIN);
        whenAccedeALaVistaDeAsignacionDeRoles(request);
        thenSeMuestraLaVistaCorrectamente(this.modelAndView,request);
    }

    private HttpServletRequest givenExisteUnUsuarioConRolDe(String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("rol")).thenReturn(rol);
        return request;
    }

    private void whenAccedeALaVistaDeAsignacionDeRoles(HttpServletRequest request) {
        this.modelAndView = controladorAdministrador.mostrarAsignacionDeRol(request);
    }

    private void thenSeMuestraLaVistaCorrectamente(ModelAndView modelAndView, HttpServletRequest request) {
        assertThat(this.modelAndView.getViewName()).isEqualTo("asignacion-de-rol");
        assertThat(request.getSession().getAttribute("rol")).isEqualTo("admin");
    }

}
