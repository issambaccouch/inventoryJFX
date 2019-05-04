/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entites.CatProd;
import static produit.Produit.from;
import static Presentation.ListProdController.produit;
import Services.CatProdService;
import Services.ProductService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class FormProdEditController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField nomp;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXComboBox<String> catProd;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton ajoutButton;
    @FXML
    private JFXButton retour;
    @FXML
    private ImageView imgv;
    @FXML
    private Text errorPrice;
    @FXML
    private Text errorCat;
    @FXML
    private Text errorName;
    @FXML
    private Text errorImg;
    @FXML
    private Text errorDesc;

    private ArrayList<String> listFiles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ajoutButton.setDisable(true);
        listFiles = new ArrayList<>();
        listFiles.add("*.PNG");
        listFiles.add("*.png");
        listFiles.add("*.JPEG");
        listFiles.add("*.jpeg");
        listFiles.add("*.JPG");
        listFiles.add("*.jpg");
        listFiles.add("*.BMP");
        listFiles.add("*.bmp");
        listFiles.add("*.GIF");
        listFiles.add("*.gif");

        File F = new File(produit.getImagep());
        Image im1 = new Image(F.toURI().toString());
        imgv.setImage(im1);

        System.out.println("image " + produit.getImagep());
        CatProdService service = new CatProdService();
        ArrayList<CatProd> categories = service.getAllCatProd();
        for (CatProd categorie : categories) {
            catProd.getItems().add(categorie.getNomcp());
        }

        errorCat.setText("");
        errorDesc.setText("");
        errorImg.setText("");
        errorPrice.setText("");
        errorName.setText("");

        description.setText(produit.getDescription());
        nomp.setText(produit.getNomp());
        prix.setText(Double.toString(produit.getPrix()));
        description.setText(produit.getDescription());
        System.out.println(produit.getCatProd().getNomcp());
        catProd.setValue(produit.getCatProd().getNomcp());

    }

    @FXML
    private void ajout(ActionEvent event) {
        System.out.println(from);
        String name = nomp.getText();
        String prixString = prix.getText();
        String categ = catProd.getValue();
        String descriptionString = description.getText();

        if (name.equals("")) {
            errorName.setText("Veuillez saisir un nom !");
            return;
        }
        if (catProd.getItems().isEmpty()) {
            errorCat.setText("Veuillez choisir une catégorie!");
            return;
        }
        if (prixString.equals("")) {
            errorPrice.setText("Veuillez saisir un prix");
            return;
        }
        Double price;
        try {
            price = Double.parseDouble(prixString);
        } catch (NumberFormatException ex) {
            errorPrice.setText("Veuillez saisir un prix correcte!");
            return;
        }

        if (descriptionString.equals("")) {
            errorDesc.setText("Veuillez saisir une description");
            return;
        }

        CatProdService serviceCateg = new CatProdService();
        CatProd cat = serviceCateg.getCategoryByName(categ);
        System.out.println(cat);

        produit.setCatProd(cat);
        produit.setDescription(descriptionString);
        produit.setNomp(name);
        produit.setPrix(price);
        produit.setIduser(2);
        Image image = imgv.getImage();
        String file = image.impl_getUrl();
        file = file.replace("file:/", "");
        file = file.replace("%20", " ");
        System.out.println("F1 url " + file);
        produit.setImagep(file);

        ProductService serviceProduct = new ProductService();
        serviceProduct.updateProduct(produit);

        System.out.println(produit);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Produit mis à jour avec succès");
        alert.showAndWait();

        
        try {
            Parent root = null;
            if(from.equals("Admin"))
                root = FXMLLoader.load(getClass().getResource("/Presentation/ListProd.fxml"));
            else if(from.equals("User"))
                root = FXMLLoader.load(getClass().getResource("/Presentation/FormClientProducts.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = null;
            if(from.equals("Admin"))
                root = FXMLLoader.load(getClass().getResource("/Presentation/ListProd.fxml"));
            else if(from.equals("User"))
                root = FXMLLoader.load(getClass().getResource("/Presentation/FormClientProducts.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void imageBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));

        File f = fc.showOpenDialog(null);
        if (f != null) {
            Image im = new Image(f.toURI().toString());
            imgv.setImage(im);
            ajoutButton.setDisable(false);
        } else {
            ajoutButton.setDisable(true);
        }
    }

}
