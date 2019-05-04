/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entites.CatProd;
import Services.CatProdService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class FormCatAddController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton retour1;
    @FXML
    private Text errorName;
    @FXML
    private JFXTextField nameCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        errorName.setText("");
    }

    @FXML
    private void retour(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        String name = nameCat.getText();
        if (name.equals("")) {
            errorName.setText("Veuillez saisir un nom!");
            return;
        }

        CatProd categorie = new CatProd();
        categorie.setNomcp(name);

        CatProdService service = new CatProdService();
        if (service.uniqueCatgeory(name)) {
            service.addCategorie(categorie);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Catégorie ajoutée avec succès");
            alert.showAndWait();
        } else {
            errorName.setText("Ce nom est déjà enregistrer!");
            return;
        }

    }

}
