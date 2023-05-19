/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entities.Accompagnement;
import entities.Item;
import entities.Tasks;
import entities.User;
import help.Help;

import javafx.fxml.FXML;
import com.itextpdf.text.Document;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.xhtmlrenderer.pdf.ITextRenderer;
import services.AccompagnementService;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.twilio.Twilio;

import com.twilio.type.PhoneNumber;
import javafx.scene.layout.HBox;
import services.ItemService;

import javax.swing.plaf.ButtonUI;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ItemUserFrontController implements Initializable {
    @FXML
    MenuButton accompagnment_button ;
    private User myUser= Help.loggedUser;
    private static User thisUser =new User(12);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAccompagnment_button();


    }


    public void setAccompagnment_button() {
        AccompagnementService accompagnementService = new AccompagnementService();
        List<Tasks> myTasks = accompagnementService.lister_tasks_for_user(myUser.getId()) ;
        //    accompagnment_button.
        for(Tasks task :myTasks){
            int idTask = task.getId();

            MenuItem menuItem = new MenuItem(task.getTitre());
            menuItem.setOnAction(e->{
                //sendSms();

                accompagnementService.EnvoierAccompagnementPartie3(new Accompagnement(new Tasks(idTask),false,myUser,thisUser)) ;
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "ACCOMPAGNEMENT ENVOYER !!", ButtonType.OK);
                a.showAndWait();
                accompagnment_button.getItems().remove(menuItem);
                System.out.println("the idddddddddds"+idTask);

            });

            accompagnment_button.getItems().add(menuItem);
        }

    }

    public void setAccompagnment_button(javafx.event.ActionEvent event) {
        AccompagnementService accompagnementService = new AccompagnementService();
        List<Tasks> myTasks = accompagnementService.lister_tasks_for_user(myUser.getId()) ;
        //    accompagnment_button.
        for(Tasks task :myTasks){
            int idTask = task.getId();

            MenuItem menuItem = new MenuItem(task.getTitre());
            menuItem.setOnAction(e->{
                //sendSms();

                accompagnementService.EnvoierAccompagnementPartie3(new Accompagnement(new Tasks(idTask),false,myUser,thisUser)) ;
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "ACCOMPAGNEMENT ENVOYER !!", ButtonType.OK);
                a.showAndWait();
                accompagnment_button.getItems().remove(menuItem);
                System.out.println("the idddddddddds"+idTask);

            });

            accompagnment_button.getItems().add(menuItem);
        }

    }
}