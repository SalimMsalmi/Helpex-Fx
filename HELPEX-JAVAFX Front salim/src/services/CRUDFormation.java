/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Centre;
import entities.Formation;
import interfaces.InterfaceFormation;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CRUDFormation implements InterfaceFormation{
 Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    @Override
    public void ajouterFormation(Formation f) {
   try {
                           //String reqq="INSERT INTO `centre`(`nom_centre`, `adresse_centre`, `email_centre`, `telephone_centre`, `site_web_centre`) VALUES ('"+c.getNomCentre()+"','"+c.getAdresseCentre()+"','"+c.getEmailCentre()+"','"+c.getTelephoneCentre()+"','"+c.getSiteWebCentre()+"')";

       
                    String req="INSERT INTO `formation`(`nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree`, `id_centre_id`) VALUES ('"+f.getNomFormation()+"','"+f.getDescriptionFormation()+"','"+f.getCoutFormation()+"','"+f.getNombreDePlace()+"','"+f.getDuree()+"','"+f.getIdCentre().getId()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("formation ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("formation non ajouter");        }    }

    @Override
    public void modifierFormation(Formation f, String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {
  try {
   String requete4 = " UPDATE formation SET " + "  nom_formation= ?, description_formation = ?, cout_formation  = ?, Nombre_de_place = ?, duree = ? WHERE id= " + f.getId();
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete4);
            pst.setString(1, nomFormation);
            pst.setString(2, descriptionFormation);
            pst.setFloat(3, coutFormation);
            pst.setInt(4, NombreDePlace);
            pst.setString(5, duree);
            pst.executeUpdate();
            System.out.println("formation modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimerFormation(Formation f) {
  try {
        String req = "DELETE FROM formation WHERE id='"+f.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("formation supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression de la formation");
    }    }

    @Override
    public List<Formation> afficherFormation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void generatePDF(Formation formation) {

    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("Formation-" + formation.getNomFormation() + ".pdf"));
        document.open();

        // Ajouter le titre
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph title = new Paragraph("Formation : " + formation.getNomFormation(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter les détails de la formation dans une table
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        PdfPCell cell;

        // Ajouter les informations de la formation dans les cellules de la table
        cell = new PdfPCell(new Paragraph("Description"));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(formation.getDescriptionFormation()));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Coût"));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.valueOf(formation.getCoutFormation())));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Nombre de place"));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.valueOf(formation.getNombreDePlace())));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Durée"));
        cell.setPadding(10);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(formation.getDuree()));
        cell.setPadding(10);
        table.addCell(cell);

        document.add(table);

        // Ajouter la date et l'heure de génération du PDF
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //Paragraph date = new Paragraph("Date et heure de génération du PDF : " + LocalDate.now().format(formatter));
        //date.setAlignment(Element.ALIGN_RIGHT);
        //document.add(date);

        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
