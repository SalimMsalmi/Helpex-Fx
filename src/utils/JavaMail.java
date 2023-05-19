/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.utils;

import entities.Formation;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hp
 */
public class JavaMail {

    public static void sendMail(String recepient, Formation f) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // String myAccountEmail = "apex.pidev1@gmail.com";
        // String password = "smzibedrpqanjggp";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("apex.pidev1@gmail.com", "smzibedrpqanjggp"); //To change body of generated methods, choose Tools | Templates.
            }

        });

        Message message = prepareMessage(session, "apex.pidev1@gmail.com", recepient,f);

        Transport.send(message);
        System.out.println("Message sent successfully");

    }

   private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Formation f) {
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("my first email");
        String messageText = "Hey i wanna be you client" + "\n\n" +
            "Formation nom: " + f.getNomFormation() + "\n" +
            "Formation Description: " + f.getDescriptionFormation() + "\n" +
            "Formation cout: " + f.getCoutFormation() + "\n" +
            "Formation duree: " + f.getDuree();
        message.setText(messageText);
        return message;
    } catch (Exception ex) {
        Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}


    

    
}