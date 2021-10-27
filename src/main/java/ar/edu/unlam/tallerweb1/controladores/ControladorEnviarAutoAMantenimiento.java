package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Marca;
import ar.edu.unlam.tallerweb1.modelo.Modelo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMantenimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorEnviarAutoAMantenimiento {

    private ModelMap model = new ModelMap();
    private ServicioMantenimiento servicioMantenimiento;
    private String viewName;

    @Autowired
    public ControladorEnviarAutoAMantenimiento(ServicioMantenimiento servicioMantenimiento) {
        this.servicioMantenimiento = servicioMantenimiento;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ir-lista-de-autos")
    public ModelAndView irAListaDeAutos() {
        DatosEnvioAMantenimiento datos = new DatosEnvioAMantenimiento();
        ModelMap model = new ModelMap();
        model.put("datosMantenimiento", datos);
        return new ModelAndView("lista-de-autos", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/mantenimiento")
    public ModelAndView enviarAutoAManteniminento(@ModelAttribute("datosMantenimiento") DatosEnvioAMantenimiento datosEnvioAMantenimiento) throws Exception {
        //TODO CUANDO SE UTILIZEN LAS VISTAS DESCOMENTAR ESTE METODO PARA QUE DE ESA FORMA MANDE EL AUTO Y EL USUARIO HARDCODEADO
        //hardCodeoLosDatosQueLleganPorPost(datosEnvioAMantenimiento);

        if (esAdministradorElUsuarioQueLlegaCon(datosEnvioAMantenimiento)) {
            elServicioDeMantenimientoEnviaElAutoAMantenientoSiNoLanzaUnaException(datosEnvioAMantenimiento);
            enviarAlaVistaDeMantenimientoYPasarLosDatosDeMantenimentoAlModelMap(datosEnvioAMantenimiento);
        } else {
            enviarALaVistadeListaDeAutosYMandaUnMensajeDeError(datosEnvioAMantenimiento);
        }
        return new ModelAndView(viewName, model);
    }

    private boolean esAdministradorElUsuarioQueLlegaCon(DatosEnvioAMantenimiento datosEnvioAMantenimiento) {
        return datosEnvioAMantenimiento.getUsuario().getRol() == "Admin";
    }

    private void elServicioDeMantenimientoEnviaElAutoAMantenientoSiNoLanzaUnaException(DatosEnvioAMantenimiento datosEnvioAMantenimiento) throws Exception {
        try {
            servicioMantenimiento.enviar(datosEnvioAMantenimiento.getAuto(),datosEnvioAMantenimiento.getFechaInicial());
        } catch (Exception e) {
            viewName = "lista-de-autos";
            throw new Exception();
        }
    }

    private void enviarAlaVistaDeMantenimientoYPasarLosDatosDeMantenimentoAlModelMap(DatosEnvioAMantenimiento datosEnvioAMantenimiento) {
        model.put("datosMantenimiento", datosEnvioAMantenimiento);
        model.put("mensaje", "El auto se envio correctamente a mantenimiento");
        model.put("usuario", datosEnvioAMantenimiento.getUsuario().getRol());
        model.put("marca", datosEnvioAMantenimiento.getAuto().getMarca());
        model.put("modelo", datosEnvioAMantenimiento.getAuto().getModelo());
        model.put("km-del-auto", datosEnvioAMantenimiento.getAuto().getKm());
        model.put("fecha",datosEnvioAMantenimiento.getFechaInicial());
        viewName = "mantenimiento";
    }

    private void enviarALaVistadeListaDeAutosYMandaUnMensajeDeError(DatosEnvioAMantenimiento datosEnvioAMantenimiento) {
        model.put("mensaje", "No se envio correctamente ya que el usuario no es administrador");
        model.put("usuario", datosEnvioAMantenimiento.getUsuario().getRol());
        viewName = "lista-de-autos";
    }

    private void hardCodeoLosDatosQueLleganPorPost(DatosEnvioAMantenimiento datosEnvioAMantenimiento) {
        Auto auto = creoUnAuto();
        Usuario usuario = creoUnUsuario();
        seteoElAutoYElUsuarioALosDatosDeMantenimiento(datosEnvioAMantenimiento, auto, usuario);
    }

    private Auto creoUnAuto() {
        Auto auto = new Auto();
        Marca marca = new Marca(1, "Ford");
        Modelo modelo = new Modelo(1, "Fiesta", marca);
        auto.setMarca(marca);
        auto.setModelo(modelo);
        auto.setKm(100);
        return auto;
    }

    private Usuario creoUnUsuario() {
        Usuario usuario = new Usuario("Admin");
        return usuario;
    }

    private void seteoElAutoYElUsuarioALosDatosDeMantenimiento(DatosEnvioAMantenimiento datosEnvioAMantenimiento, Auto auto, Usuario usuario) {
        datosEnvioAMantenimiento.setAuto(auto);
        datosEnvioAMantenimiento.setUsuario(usuario);
        datosEnvioAMantenimiento.setFechaInicial("07/10/21");
    }

}
