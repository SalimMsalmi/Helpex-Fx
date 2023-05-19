/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entites.poste;
import services.CRUDPoste;
import utils.MyConnection;

import java.util.Properties;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author belkn
 */
public class Workshop3A35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {



        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("hedil.aouadi@esprit.tn", "bacmath2019");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hedil.aouadi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("naimihedia1@gmail.com"));
            message.setSubject(" Task  est completé");

            // Récupération des informations de la consultation

            // Construction du contenu de l'email
            String emailContent = "task : ";

            message.setText(emailContent);

            Transport.send(message);

            System.out.println("Email envoyé avec succès");

        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    
}
