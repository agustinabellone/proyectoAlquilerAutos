package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaSuscriptoException;
import ar.edu.unlam.tallerweb1.Exceptions.SuscripcionYaRenovadaException;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSuscripcion {

    @Autowired
    private ServicioSuscripcion servicioSuscripcion;

    @Autowired
    public ControladorSuscripcion (ServicioSuscripcion servicioSuscripcion){
        this.servicioSuscripcion=servicioSuscripcion;
    }


    @RequestMapping(path = "/ir-a-suscribir", method = RequestMethod.GET)
    private ModelAndView mostrarFormularioSuscripcion(){
        return new ModelAndView("ir-a-suscribir");
    }

    @RequestMapping(path = "/suscribirse", method = RequestMethod.GET)
    public ModelAndView suscribirCliente(@RequestParam(value = "id_tipo") Long id_tipo,
                                         @RequestParam(value = "id_cliente")Long id_cliente) {

        Cliente cliente = new Cliente(id_cliente);
        TipoSuscripcion tipoSuscripcion = new TipoSuscripcion(id_tipo);
        try{
            servicioSuscripcion.suscribir(cliente, tipoSuscripcion);
        }catch (ClienteYaSuscriptoException e){
            return new ModelAndView("ir-a-suscribir");
        }

        return new ModelAndView("home");
    }

    @RequestMapping(path = "/renovar-suscripcion", method = RequestMethod.POST)
    public ModelAndView renovarSuscripcion(@RequestParam("id") Long id) {

        Suscripcion suscripcion= servicioSuscripcion.buscarPorIdCliente(id);

        try{
            servicioSuscripcion.renovarSuscripcion(suscripcion);
        }catch(SuscripcionYaRenovadaException e){
            return new ModelAndView("perfil"); //EL USUARIO RENUEVA SU SUSCRIPCION DESDE SU PERFIL
        }
        return new ModelAndView("home");
    }
}
