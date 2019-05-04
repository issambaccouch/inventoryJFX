/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import static Presentation.FormClientProductsController.affiche;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class ShowProductController implements Initializable {

    @FXML
    private ImageView imageP;
    @FXML
    private Text nameP;
    @FXML
    private Text catP;
    @FXML
    private Text price;
    @FXML
    private Text descP;
    @FXML
    private JFXButton btn;
    @FXML
    private Text expired;
    @FXML
    private Text views;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            System.out.println(affiche);
            nameP.setText(affiche.getNomp());
            price.setText(affiche.getPrix() + "$");
            descP.setText(affiche.getDescription());
            catP.setText(affiche.getCatProd().getNomcp());

            File F = new File(affiche.getImagep());
            Image im1 = new Image(F.toURI().toString());
            imageP.setImage(im1);
            views.setText(Integer.toString(affiche.getNbreViews()));

            String sDate1 = affiche.getDate_exp();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

            if (date1.compareTo(new Date()) > 0) {
                System.out.println("not yet");
                btn.setDisable(false);
                expired.setText("");
            } else if (date1.compareTo(new Date()) < 0) {
                System.out.println("old !!");
                expired.setText("Cet article a expiré");
                btn.setDisable(true);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ShowProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void contactV(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ContactSms.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void retour(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
    }

}
