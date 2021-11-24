package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
public class ControladorMail {

    private static final String username="infoalquilerautos";
    private static final String password="Aa123456.";
    String dirijido="luzjuarez2016@gmail.com";



    @RequestMapping(path = "/enviarMail", method = RequestMethod.GET)
    public ModelAndView enviarMail(){
        Properties props =new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");//465
        props.put("mail.smtp.user",username);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.starttls.enable", "true");


        Session session =Session.getDefaultInstance(props);
        MimeMessage msj=new MimeMessage(session);

        try {
            msj.setFrom(new InternetAddress(username));
            msj.addRecipient(Message.RecipientType.TO,new InternetAddress(dirijido));
            msj.setSubject("bienvenide");
            msj.setText("confirmaBlda");
            Transport transport=session.getTransport("smtp");
            transport.connect("smtp.gmail.com",username,password);
            transport.sendMessage(msj,msj.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new ModelAndView("aviso-confirmar-mail");


    }



}
