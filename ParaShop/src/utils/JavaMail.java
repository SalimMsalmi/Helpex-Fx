/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

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
 * @author FaroukDev
 */
public class JavaMail {

    public static void sendMail(String recepient) throws Exception {
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
                return new PasswordAuthentication("oussema.ayari2001@gmail.com", "osdysqsfjqbwveyn"); //To change body of generated methods, choose Tools | Templates.
            }

        });

        Message message = prepareMessage(session, "oussema.ayari2001@gmail.com", recepient);

        Transport.send(message);
        System.out.println("Message sent successfully");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("my first email");
            message.setText("Hey i wanna be you client");
            String htmlContent = "<html><body><h1 style='color: #008080;'>This Product is Authorised Welcome To Helpex's ParaShop !</h1>"
                + "<p style='font-size: 16px;'>Thank you for your interest in our services.</p>"
                + "<p style='font-size: 16px;'>We would love to hear more about your needs.</p>"
                + "<a href='http://127.0.0.1:8000/produits/' style='font-size: 16px;'>Visit our website</a>"
                + "</body></html>";
        message.setContent(htmlContent, "text/html");

            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    
}