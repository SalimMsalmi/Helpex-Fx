/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import PIGui.PageArticleController;
import PIClass.Article;
import PIServices.ServiceArticle;
import pidev.MainGui;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeArticlesClientController implements Initializable {
   @FXML
    private JFXTextField idart;
    @FXML
    private JFXTextField calc;

    @FXML
    private FontAwesomeIconView btnactualiser;
    @FXML
    private FontAwesomeIconView btnchercher;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnback;
    @FXML
    private TableView<Article> coltabab;

    @FXML
    private TableColumn<Article, String> ColTitre;
    @FXML
    private TableColumn<Article, String> ColAut;
    @FXML
    private TableColumn<Article, Date> ColDate;
    @FXML
    private TableColumn<Article, String> ColDesc;
    @FXML
    private TableColumn<Article, Integer> ColVue;
    @FXML
    private TableColumn<Article, String> ColCat;
    

    @FXML
    private TableColumn<Article, String> Colimg;
    private Stage primaryStage;
    @FXML
    private JFXButton btn_aff;
    @FXML
    private JFXButton like;
    @FXML
    private JFXButton btn_calc;

    ObservableList<Article> ArtList = FXCollections.observableArrayList();
    FilteredList<Article> filter = new FilteredList<>(ArtList, e -> true);
    SortedList<Article> sort = new SortedList<>(filter);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("PageArticle.fxml"));
//    PageArticleController dpc =  loader.getController();
//    PageArticleController pac = new PageArticleController();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void aff() {
        try {
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            Object item = coltabab.getSelectionModel().getSelectedItem();
            Article art = (Article) item;
            stage.setScene(new Scene(root));

            PageArticleController dpc = loader.getController();
            dpc.setLab_aut(art.getAuteur_art());
            dpc.setLab_tit(art.getTitre_art());
            dpc.setLab_desc(art.getDescription_art());
            dpc.setLab_cat(art.getNomcat());
            dpc.load_pic(art.getPhoto());
            
            java.sql.Date d = art.getDate_art();
            String str1 = d.toString();
            dpc.setLab_date(str1);
            
            String str = Integer.toString(art.getLikes());
            dpc.setLab_like(str);
            dpc.setLab_img(art.getPhoto());

            stage.show();
            MainGui.stg.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherArt();
//        coltabab.setEditable(true);
//        ColTitre.setCellFactory(TextFieldTableCell.forTableColumn());
//        ColDesc.setCellFactory(TextFieldTableCell.forTableColumn());

        ContextMenu ContArticle = new ContextMenu();
//        MenuItem DeleteItem = new MenuItem("Supprimer");
        MenuItem LikeItem = new MenuItem("Like ☺ ");
        MenuItem AfficheArte = new MenuItem("Afficher Article");

        AfficheArte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                        System.out.println(art.getNomcat());
//                        System.out.println("broooo : "+pac.getLab_aut());
//                        pac.setLab_cat("bro");
//                        dpc.setLbprenom(tprenom.getText());
//                        pac.setLab_tit( art.getTitre_art());
//                        pac.setLab_cat(art.getNomcat());
//                        System.out.println(art.toString());
                aff();

//                        dpc.setLab_aut(art.getAuteur_art());
            }
        }
        );

//        DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//               
//                Object item = coltabab.getSelectionModel().getSelectedItem();
//                Article art = (Article) item;
//                ServiceArticle s = new ServiceArticle();
//                System.out.println(art.toString());
//                s.supprimer(art);
//
//                AfficherArt();
//
//            }
//        }
//        );
        
        LikeItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                Object item = coltabab.getSelectionModel().getSelectedItem();
                Article art = (Article) item;
                ServiceArticle s = new ServiceArticle();
                System.out.println(art.toString());
                s.Likes(art);

                AfficherArt();

            }
        }
        );
        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (ContArticle.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        ContArticle.getItems().add(AfficheArte);
        ContArticle.getItems().add(LikeItem);
//        ContArticle.getItems().add(DeleteItem);

        ContArticle.setOnShowing(event);
        ContArticle.setOnHiding(event);
        coltabab.setContextMenu(ContArticle);
        //Object item = tableView_Publication.getSelectionModel().getSelectedItem();

    }

//    @FXML
//    public void changeTitle(TableColumn.CellEditEvent edittedCell) {
//        Article articleSelected = coltabab.getSelectionModel().getSelectedItem();
//        articleSelected.setTitre_art(edittedCell.getNewValue().toString());
//        ServiceArticle s = new ServiceArticle();
//        s.modifier(articleSelected);
//    }
//
//    @FXML
//    public void changeDesc(TableColumn.CellEditEvent edittedCell) {
//        Article articleSelected = coltabab.getSelectionModel().getSelectedItem();
//        articleSelected.setDescription_art(edittedCell.getNewValue().toString());
//        ServiceArticle s = new ServiceArticle();
//        s.modifier(articleSelected);
//    }

    @FXML
    private void AfficherArt() {
        //ObservableList<Article> ArtList = FXCollections.observableArrayList();  
        // ColArt.setCellValueFactory(new PropertyValueFactory<>("id_art"));
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
        ColAut.setCellValueFactory(new PropertyValueFactory<>("auteur_art"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_art"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
        ColVue.setCellValueFactory(new PropertyValueFactory<>("vues"));
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("img"));

        ServiceArticle srec = new ServiceArticle();
        srec.afficher().forEach(e -> {
            ArtList.add(e);
        });
        coltabab.setItems(ArtList);
    }

//    @FXML
//    private void rechercher(ActionEvent event) {
//        ServiceArticle rt = new ServiceArticle();
//
//        //ObservableList<Article> ArtList = FXCollections.observableArrayList();
//       // ColArt.setCellValueFactory(new PropertyValueFactory<>("id_art"));
//        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
//        ColAut.setCellValueFactory(new PropertyValueFactory<>("auteur_art"));
//        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_art"));
//        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
//        ColVue.setCellValueFactory(new PropertyValueFactory<>("vues"));
//        ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
//
//        List old = rt.afficherArticleByCategorie(tfrech.getText());
//        ArtList.addAll(old);
//        System.out.println(ArtList);
//        coltabab.setItems(ArtList);
//
//    }
    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticleClient.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void triDate(ActionEvent event) {
        try {
            ObservableList<Article> ArtList = FXCollections.observableArrayList();
            //ColArt.setCellValueFactory(new PropertyValueFactory<>("id_art"));
            ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
            ColAut.setCellValueFactory(new PropertyValueFactory<>("auteur_art"));
            ColDate.setCellValueFactory(new PropertyValueFactory<>("date_art"));
            ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_art"));
            ColVue.setCellValueFactory(new PropertyValueFactory<>("vues"));
            ColCat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
            Colimg.setCellValueFactory(new PropertyValueFactory<>("img"));
            

            ServiceArticle srec = new ServiceArticle();
            srec.trierArticleByDate().forEach(e -> {
                ArtList.add(e);
            });
            coltabab.setItems(ArtList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    @FXML
//    private void LikeCom(ActionEvent event) {
//
//        ServiceArticle s = new ServiceArticle();
//        s.Likes(Integer.parseInt(idart.getText()));
//        JOptionPane.showMessageDialog(null, "Likesss !!");
//    }
//    @FXML
//    private void CalculerPh(ActionEvent event) {
//        int b = 0;
//        ServiceArticle s1 = new ServiceArticle();
//        b = s1.nombrePHoto(Integer.parseInt(idart.getText()));
//
//        String str = String.valueOf(b);
//        calc.setText(str);
//    }

//    @FXML
//    void recherche_released(KeyEvent event) {
    @FXML
    private void search_released(KeyEvent event) {
if (tfrech.getText().isEmpty()) {
            // JOptionPane.showMessageDialog(null, "Remplir le champs vide");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir le champs vide ! ");
            al.showAndWait();

        } else if ( tfrech.getText().matches(".*[0-9].*")||tfrech.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {
        
        tfrech.setOnKeyReleased(e -> {
            tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(Article -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Article.getNomcat().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(coltabab.comparatorProperty());
            coltabab.setItems(sort);
        });
    }
    } 
}
