package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Exceptions.NoHayEmpladosException;
import ar.edu.unlam.tallerweb1.modelo.Puntaje;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mercadopago.MercadoPago;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorMercadoPago {

    private ServicioLogin servicioLogin;
    private ServicioUsuario servicioUsuario;
    private ServicioSuscripcion servicioSuscripcion;
    private ServicioMail servicioMail;

    @Autowired
    public ControladorMercadoPago(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario, ServicioSuscripcion servicioSuscripcion,ServicioMail servicioMail) {
        this.servicioLogin = servicioLogin;
        this.servicioUsuario = servicioUsuario;
        this.servicioSuscripcion=servicioSuscripcion;
        this.servicioMail=servicioMail;
    }

    @RequestMapping(path = "/confirmar-suscripcion", method = RequestMethod.GET)
    private ModelAndView confirmarSuscripcion(HttpServletRequest request,
                                              @RequestParam(value="id_tipo") Long id_tipo ) throws MPException, NoHayEmpladosException {

        ModelMap model = obtenerPreferencia(id_tipo, request, 0);

        Long id= (Long)request.getSession().getAttribute("id");
        Puntaje puntaje = new Puntaje();
        Usuario usuario = servicioUsuario.buscarPorId(id);
        servicioUsuario.actualizarPuntaje(puntaje.getSuscripcion(), usuario);

        return new ModelAndView("confirmar-suscripcion", model);
    }

    @RequestMapping(path = "/mejorar-suscripcion")
    private ModelAndView mejorarSuscripcion(Long id_mejora, String nombre_mejora, HttpServletRequest request) throws MPException {

        ModelMap model = obtenerPreferencia(id_mejora, request, 1);

        model.put("id_mejora", id_mejora);
        model.put("id_usuarioMejora", request.getSession().getAttribute("id"));
        model.put("nombre_mejora", nombre_mejora);

        return new ModelAndView("mejorar-suscripcion", model);

    }

    private ModelMap obtenerPreferencia(Long id_tipo, HttpServletRequest request, int opcion) throws MPException {
        TipoSuscripcion tipoSuscripcion= this.servicioSuscripcion.getTipoPorid(id_tipo);

        ModelMap model= new ModelMap();
        model.put("id_tipo", id_tipo);
        model.put("id_usuario", request.getSession().getAttribute("id"));

        //MercadoPago
        // Agrega credenciales
        MercadoPago.SDK.setAccessToken("TEST-2241173070671113-111703-42847b110b67bd51ddce8c5741ee15ba-1020789785");

        // Crea un objeto de preferencia
        Preference preference = new Preference();

        // Crea un ítem en la preferencia
        Item item = new Item();
        item.setTitle("Plan "+tipoSuscripcion.getNombre())
                .setQuantity(1)
                .setUnitPrice(tipoSuscripcion.getPrecio());
        preference.appendItem(item);

        BackUrls backUrls = new BackUrls();

        if(opcion==0){
            backUrls = new BackUrls(
                    "http://localhost:8080/proyecto_limpio_spring_war_exploded/pagoRealizado?opcion=0&id_tipo="+tipoSuscripcion.getId(),
                    "http://www.tu-sitio/pending",
                    "http://localhost:8080/proyecto_limpio_spring_war_exploded/pagoRealizado?opcion=0&id_tipo=1");
        }else{
            backUrls = new BackUrls(
                    "http://localhost:8080/proyecto_limpio_spring_war_exploded/pagoRealizado?opcion=1&id_tipo="+tipoSuscripcion.getId(),
                    "http://www.tu-sitio/pending",
                    "http://localhost:8080/proyecto_limpio_spring_war_exploded/pagoRealizado?opcion=1&id_tipo=1");
        }

        preference.setBackUrls(backUrls);

        preference.save();


        model.put("preferencia", preference);

        return model;
    }

    @RequestMapping(path = "/pagoRealizado", method = RequestMethod.GET)
    private ModelAndView pagoRealizado(HttpServletRequest request,
                                              @RequestParam(value="collection_status") String resultado,
                                              @RequestParam(value="id_tipo") Long id_tipo,
                                              @RequestParam(value="opcion") int opcion) throws NoHayEmpladosException {

        String mail = (String) request.getSession().getAttribute("email");

        if(resultado.equals("approved") && opcion == 0){
            Long id_usuario= (Long)request.getSession().getAttribute("id");
            Usuario usuario = this.servicioUsuario.buscarPorId(id_usuario);
            TipoSuscripcion tipoSuscripcion= this.servicioSuscripcion.getTipoPorid(id_tipo);

            servicioSuscripcion.suscribir(usuario, tipoSuscripcion);
            request.getSession().setAttribute("tieneSuscripcion",true);
            servicioMail.enviarMailSuscripcion(mail,tipoSuscripcion.getNombre());
        }

        if(resultado.equals("approved") && opcion == 1){
            Usuario usuario = new Usuario((Long)request.getSession().getAttribute("id"));
            TipoSuscripcion tipoSuscripcion = servicioSuscripcion.getTipoPorid(id_tipo);

            servicioSuscripcion.cancelarSuscripcionForzada(usuario);

            servicioSuscripcion.suscribir(usuario, tipoSuscripcion);

            request.getSession().setAttribute("tieneSuscripcion",true);
            servicioMail.enviarMailMejorarSuscripcion(mail,tipoSuscripcion.getNombre());
        }




        ModelMap model = new ModelMap();
        model.put("resultado", resultado);

        return new ModelAndView("pagoResultado", model);
    }




}
