/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entites.CatProd;
import Entites.Products;
import Services.CatProdService;
import Services.ProductService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static produit.Produit.from;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class FormProdAddController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField nomp;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXDatePicker date_exp;
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

    private ArrayList<String> listFiles;
    @FXML
    private Text errorPrice;
    @FXML
    private Text errorCat;
    @FXML
    private Text errorDate;
    @FXML
    private Text errorName;
    @FXML
    private Text errorImg;
    @FXML
    private Text errorDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ajoutButton.setDisable(true);
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

        File F = new File("C:/Users/Tryvl/Desktop/5edma/Produit/src/resources/img.jpg");
        Image im1 = new Image(F.toURI().toString());
        imgv.setImage(im1);

        CatProdService service = new CatProdService();
        ArrayList<CatProd> categories = service.getAllCatProd();
        for (CatProd categorie : categories) {
            catProd.getItems().add(categorie.getNomcp());
        }

        errorCat.setText("");
        errorDate.setText("");
        errorDesc.setText("");
        errorImg.setText("");
        errorPrice.setText("");
        errorName.setText("");
    }

    @FXML
    private void ajout(ActionEvent event) throws ParseException {

        String name = nomp.getText();
        String prixString = prix.getText();
        String categ = catProd.getValue();
        String descriptionString = description.getText();
        LocalDate date = date_exp.getValue();

        System.out.println("Date " + date.toString());

        if (date.isBefore(LocalDate.now())) {
            errorDate.setText("Veuillez saisir une date valide");
            return;
        } else if (date.isEqual(LocalDate.now())) {
            errorDate.setText("Veuillez saisir une date valide");
            return;
        }

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
            errorPrice.setText("Veuillez saisir un prix corecte!");
            return;
        }

        if (descriptionString.equals("")) {
            errorDesc.setText("Veuillez saisir une description");
            return;
        }

        /*
        String sDate1 = data.get(i).getDate_exp();
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

                if (date1.compareTo(new Date()) < 0) {
                    Label label = new Label("Article expiré");

                    label.setStyle("-fx-text-fill:red");
                    element.getChildren().addAll(text, imgv, prix, label, btnView);
                } else {
                    Label label = new Label("");
                    element.getChildren().addAll(text, imgv, prix, label, btnView);
                }
         */
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());

        if (date1.compareTo(new Date()) < 0) {
            errorDate.setText("Veuillez saisir une date valide");
            return;
        } else {
            errorDate.setText("");
        }

        CatProdService serviceCateg = new CatProdService();
        CatProd cat = serviceCateg.getCategoryByName(categ);
        System.out.println(cat);

        Products produit = new Products();
        produit.setCatProd(cat);

        produit.setDate_exp(date.toString());

        produit.setDescription(descriptionString);
        produit.setNomp(name);
        produit.setPrix(price);
        produit.setIduser(2); //////////////user Id
        Image image = imgv.getImage();
        String file = image.impl_getUrl();
        file = file.replace("file:/", "");
        file = file.replace("%20", " ");
        System.out.println("F1 url " + file);
        produit.setImagep(file);

        ProductService serviceProduct = new ProductService();
        serviceProduct.addProduct(produit);

        System.out.println(produit);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Produit ajouté avec succès");
        alert.showAndWait();

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = null;
            if (from.equals("Admin")) {
                root = FXMLLoader.load(getClass().getResource("/Presentation/ListProd.fxml"));
            } else if (from.equals("User")) {
                root = FXMLLoader.load(getClass().getResource("/Presentation/FormClientProducts.fxml"));
            }

            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
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
