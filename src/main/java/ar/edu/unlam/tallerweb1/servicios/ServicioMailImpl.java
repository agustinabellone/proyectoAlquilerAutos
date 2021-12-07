
package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.EstadoUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Properties;


@Service("ServicioMail")
@Transactional
public class ServicioMailImpl implements ServicioMail {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioMailImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }



    public void enviarMail(String mensaje,String asunto, String email) {
        String username="infoalquilerautos";
        String password="Aa123456.";


        Properties props =new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");//465
        props.put("mail.smtp.user",username);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.mail.sender",username+"@gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");




        try {
            Session session =Session.getDefaultInstance(props);
            MimeMessage msj=new MimeMessage(session);
            msj.setFrom(new InternetAddress(username));
            msj.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            msj.setSubject(asunto);
            msj.setContent(mensaje, "text/html");
            Transport transport=session.getTransport("smtp");
            transport.connect("smtp.gmail.com",username,password);
            transport.sendMessage(msj,msj.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new MailNoEnviado();
        }

    }

    @Override
    public void enviarMailRegistro(String email,String hash) {
        String asunto="confirme su email";
        String mensaje= "<h2>¡Gracias por registrarte!</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>Su cuenta fue creada pero debe confirmar su email en el siguiente link</h4><br>"
                +"<a href='http://localhost:8080/proyecto_limpio_spring_war_exploded/validar-mail?email="+email+ "&"+ "hash="+hash+"'> VERIFICAR EMAIL</a>"
                +"<p>Confirmar tu dirección de correo electrónico nos ayuda a mantener la seguridad de tu cuenta.</p><br>"
                +"<p>Verifica si esta es la dirección correcta: "+email+"</p><br> <br>"
                +"<p>Si crees que esto es un error y no tenes la intencion de registrarte a nuestra pagina, podes ignorar este mensaje y nada mas sucedera</p><br>";;

        enviarMail(mensaje,asunto,email);

    }

    @Override
    public void enviarMailSuscripcion(String email, String nombrePlan) {
        String asunto="Registro confirmado";
        String mensaje= "<h2>¡Hola !</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>Gracias por suscribirte a el plan "+nombrePlan+"</h4><br>"
                +"<p>Estas son la promociones especiales exclusivas para clientes como vos:</p><br>"
                +"<p>etc etc etc</p><br>";

        enviarMail(mensaje,asunto,email);

    }

    @Override
    public void enviarMailMejorarSuscripcion(String email, String nombrePlan) {
        String asunto="Registro mejorado actualizado";
        String mensaje= "<h2>¡Hola !</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>Usted avanzo a el plan "+nombrePlan+"</h4><br>"
                +"<p>Estas son la promociones especiales exclusivas para clientes como vos:</p><br>"
                +"<p>etc etc etc</p><br>";

        enviarMail(mensaje,asunto,email);

    }





    @Override
    public void enviarMailAlquiler(String email, String lugarRetiro, String lugarDevolucion, LocalDate salida, LocalDate ingreso) {
        String asunto="Alquiler confirmado";
        String mensaje= "<h2>¡Confirmamos tu alquiler!</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>Alquilaste el auto"+"modelooo"+"</h4><br>"
                +"<table>\n" +
                "<tr><th>Lugar retiro</th>\n" +
                "    <th>Lugar devolucion</th>\n" +
                "    <th>Fecha retiro</th>\n" +
                "    <th>Fecha Devolucion</th>\n" +
                "  </tr>\n" +
                "  <tr><td>"+lugarRetiro+"</td>\n" +
                "      <td>"+lugarDevolucion+"</td>\n" +
                "      <td>"+salida+"</td>\n" +
                "      <td>"+ingreso+"</td>\n" +
                "  </tr>\n" +
                "</table><br>"+
                "<p>Si no cumple con algunas de las fechas o garages se le cobrara un monto segun su plan</p><br>";

        enviarMail(mensaje,asunto,email);

    }

    @Override
    public void enviarMailActivarCuenta(String email) {
        String asunto="Reactivar cuenta ";
        String mensaje= "<h2>¡Reactive su cuenta!</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>Para reactivar tu cuenta necesitas confirmar con el siguiente link</h4><br>"
                +"<a href='http://localhost:8080/proyecto_limpio_spring_war_exploded/confirmar-reactivar-cuenta?emailUsuario="+email+"'>REACTIVAR CUENTA</a>"
                +"<p>Si crees que esto es un error y no tenes la intencion de reactivar tu cuenta, podes ignorar este mensaje y nada mas sucedera</p><br>";;

        enviarMail(mensaje,asunto,email);

    }




    @Override
    public void verificarHash(String mail, String hash){
        Usuario usuario=repositorioUsuario.buscarPorEmailYHash(mail,hash);
        if (usuario==null) {
            throw new HashIncorrecto();
        }

        usuario.setEstado(EstadoUsuario.ACTIVO);

    }





}

