
package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.Exceptions.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.Exceptions.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.Exceptions.ClienteYaExisteException;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Properties;


@Service("ServicioMail")
@Transactional
public class ServicioMailImpl implements ServicioMail {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioMailImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
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



        try {
            Session session =Session.getDefaultInstance(props);
            MimeMessage msj=new MimeMessage(session);
            msj.setFrom(new InternetAddress(username));
            msj.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            msj.setSubject("bienvenide");
            msj.setText("confirmaBlda");
            Transport transport=session.getTransport("smtp");
            transport.connect("smtp.gmail.com",username,password);
            transport.sendMessage(msj,msj.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }





}

