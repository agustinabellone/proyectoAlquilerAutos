package ar.edu.unlam.tallerweb1.controladores;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
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

    @RequestMapping(path = "/confirmar-suscripcion", method = RequestMethod.GET)
    private ModelAndView confirmarSuscripcion(HttpServletRequest request,
                                              @RequestParam(value="id_tipo") Long id_tipo ) throws MPException {
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
        item.setTitle("Mi producto")
                .setQuantity(1)
                .setUnitPrice((float) 75.56);
        preference.appendItem(item);

        BackUrls backUrls = new BackUrls(
                "http://localhost:8080/proyecto_limpio_spring_war_exploded/main",
                "http://www.tu-sitio/pending",
                "http://localhost:8080/proyecto_limpio_spring_war_exploded/ir-a-suscribir");

        preference.setBackUrls(backUrls);

        preference.save();


        model.put("preferencia", preference);


        return new ModelAndView("confirmar-suscripcion", model);
    }
}
