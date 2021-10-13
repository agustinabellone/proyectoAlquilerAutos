package ar.edu.unlam.tallerweb1.controladores;


//import ar.edu.unlam.tallerweb1.repositorio.TablaVehiculos;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorValorarVehiculo {


    private TablaVehiculos tablaVehiculos;

    @Autowired
    public ControladorValorarVehiculo (TablaVehiculos tablaVehiculos){
        this.tablaVehiculos=tablaVehiculos;
    }



    @RequestMapping(path = "/ir-lista-autos-utilizados", method = RequestMethod.GET)
    public ModelAndView irListaAutosUtilizados(){



        return new ModelAndView("lista-autos-utilizados");
    }




    @RequestMapping(path = "/valorar-vehiculo", method = RequestMethod.GET)
    public ModelAndView valorarVehiculo() {
        return new ModelAndView("home");
    }





}
