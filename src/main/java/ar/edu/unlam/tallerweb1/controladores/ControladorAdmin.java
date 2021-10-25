package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAdmin {

	@RequestMapping(method = RequestMethod.GET, path = "/administrador")
	public ModelAndView irAlPanelPrincipal(HttpServletRequest request) {
		if (request.getSession().getAttribute("rol").equals("ADMIN")) {
			return new ModelAndView("panel-principal");
		}
		return new ModelAndView("home");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ir-a-lista-de-autos")
	public ModelAndView irALaListaDeAutos(HttpServletRequest usuarioAdminitrador) {
		if (usuarioAdminitrador.getSession().getAttribute("rol").equals("ADMIN")) {
			return new ModelAndView("lista-de-autos");
		} else {
			return new ModelAndView("home");
		}
	}
}
