/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entites.CatProd;
import Entites.Products;
import static Presentation.ListProdController.produit;
import Services.CatProdService;
import Services.ProductService;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static produit.Produit.from;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class FormClientProductsController implements Initializable {

    @FXML
    private Tab allTab;
    @FXML
    private Tab mineTab;
    @FXML
    private AnchorPane anchoreProducts;

    public static Products affiche = new Products();
    private Pagination pagination;
    @FXML
    private JFXTextField search;

    private static ObservableList<Products> dataProducts;
    @FXML
    private TableColumn<Products, String> nom;
    @FXML
    private TableColumn<Products, Double> prix;
    @FXML
    private TableColumn<Products, String> desc;
    @FXML
    private TableColumn<Products, String> cat;
    @FXML
    private TableColumn<Products, String> date;
    @FXML
    private Button sup;
    @FXML
    private Button modif;
    @FXML
    private Button ajout;
    @FXML
    private JFXTextField search1;

    ProductService es = new ProductService();
    private static ObservableList<Products> CatListActive;
    @FXML
    private TableView<Products> tv;
    public static Products produitClient = new Products();
    public static Products produitAffichage = new Products();
    ProductService service = new ProductService();
    @FXML
    private Tab mineTab1;
    @FXML
    private TableView<Products> tvAttente;
    @FXML
    private TableColumn<Products, String> nomAttente;
    @FXML
    private TableColumn<Products, Double> prixAttente;
    @FXML
    private TableColumn<Products, String> descAttente;
    @FXML
    private TableColumn<Products, String> catAttente;
    @FXML
    private TableColumn<Products, String> dateAttente;
    private static ObservableList<Products> CatListAttente;
    @FXML
    private Button supAttente;
    @FXML
    private Button modifAttente;
    @FXML
    private Tab mineTab2;
    @FXML
    private TableView<Products> tvRefuser;
    @FXML
    private TableColumn<Products, String> nomRefuser;
    @FXML
    private TableColumn<Products, Double> prixRefuser;
    @FXML
    private TableColumn<Products, String> descRefuser;
    @FXML
    private TableColumn<Products, String> catRefuser;
    @FXML
    private TableColumn<Products, String> dateRefuser;
    private static ObservableList<Products> CatListRefuser;
    @FXML
    private Button supRefuser;
    @FXML
    private Button modifRefuser;
    @FXML
    private ComboBox<String> combobox;

    CatProdService serviceCat = new CatProdService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProductService es = new ProductService();
        List<Products> products = es.getAllProducts();
        List<Products> productsActive = new ArrayList<>();
        List<Products> productsAttente = new ArrayList<>();
        List<Products> productsRefuser = new ArrayList<>();
        for (Products p : products) {
            if (p.getEtatpr() == 1) {
                productsActive.add(p);
            }
            if (p.getEtatpr() == -1 && p.getIduser() == 2) {
                productsRefuser.add(p);
            } else if (p.getEtatpr() == 0 && p.getIduser() == 2) {
                productsAttente.add(p);
            }
        }

        initData();
        LoadData();

        search.setOnAction((event) -> {

            List<Products> Nvproduits = new ArrayList<>();
            if (combobox.getValue().equals("") || combobox.getValue().equals("Tous")) {
                Nvproduits = es.findByName(search.getText());
            } else {
                List<Products> productsSearch = es.findByName(search.getText());
                CatProd cat = serviceCat.getCategoryByName(combobox.getValue());
                for(Products p : productsSearch)
                {
                    if(p.getCatProd().getIdcp() == cat.getIdcp())
                      Nvproduits.add(p);  
                }
            }

            List<Products> productsActive2 = new ArrayList<>();
            productsActive.removeAll(productsActive);
            for (Products p : Nvproduits) {
                if (p.getEtatpr() == 1) {
                    productsActive.add(p);
                }

                anchoreProducts.getChildren().clear();

                int count = (productsActive.size() / 3);
                if (productsActive.size() % 3 != 0) {
                    count += 1;
                }
                pagination = new Pagination(count, 0);
                pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex, productsActive));

                AnchorPane anchor = new AnchorPane();
                AnchorPane.setTopAnchor(pagination, 10.0);
                AnchorPane.setRightAnchor(pagination, 10.0);
                AnchorPane.setBottomAnchor(pagination, 10.0);
                AnchorPane.setLeftAnchor(pagination, -10.0);
                anchor.getChildren().add(pagination);
                anchoreProducts.getChildren().add(anchor);
            }
        });
        int count = (productsActive.size() / 3);
        if (productsActive.size() % 3 != 0) {
            count += 1;
        }
        pagination = new Pagination(count, 0);
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex, productsActive));

        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().addAll(pagination);
        anchoreProducts.getChildren().add(anchor);
        //}

        supRefuser.setDisable(true);
        supAttente.setDisable(true);
        modifAttente.setDisable(true);
        modifRefuser.setDisable(true);

        ArrayList<CatProd> categories = serviceCat.getAllCatProd();
        combobox.getItems().add("Tous");
        for (CatProd cat : categories) {
            combobox.getItems().add(cat.getNomcp());
        }

    }

    @FXML
    private void productsTab(Event event) {

    }

    @FXML
    private void myProductsTab(Event event) {
        initData();
        LoadData();

        System.out.println("clicked me");
        sup.setDisable(true);
        modif.setDisable(true);

        ArrayList<String> possStrings = new ArrayList<>();
        for (int i = 0; i < es.getAllProducts().size(); i++) {
            possStrings.add(es.getAllProducts().get(i).getNomp());
        }
        TextFields.bindAutoCompletion(search1, possStrings);

        FilteredList<Products> filteredData = new FilteredList<>(CatListActive, p -> true);
        search1.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) -> {
            filteredData.setPredicate(products -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare destination of every excursion with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (products.getNomp().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Products> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(sortedData);

    }

    public HBox createPage(int pageIndex, List<Products> data) {
        HBox box = new HBox(30);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < data.size(); i++) {
            try {
                VBox element = new VBox();

                File F = new File(data.get(i).getImagep());
                Image im1 = new Image(F.toURI().toString());
                ImageView imgv = new ImageView();
                imgv.setImage(im1);

                imgv.setFitHeight(240);
                imgv.setFitWidth(220);

                System.out.println("img " + data.get(i).getImagep());
                Label text = new Label("Nom : " + data.get(i).getNomp());
                Label prix = new Label("Prix : " + data.get(i).getPrix());
                Button btnView = new Button("Visualiser");
                btnView.setStyle("");
                btnView.setStyle("-fx-text-fill: white; -fx-background-color: #4285F4");
                btnView.setPrefHeight(26);
                btnView.setPrefWidth(180);
                text.setStyle("-fx-font-size:16px; -fx-text-fill:blue");
                prix.setStyle("-fx-font-size:15px");

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
                Products produit = data.get(i);
                btnView.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            affiche = new Products();
                            affiche = service.getById(produit.getIdpr());
                            service.incrementViews(produit);
                            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/showProduct.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            System.out.println("Unable to open " + ex.getMessage());
                        }
                    }
                });
                box.getChildren().add(element);
            } catch (ParseException ex) {
                Logger.getLogger(FormClientProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return box;
    }

    public int itemsPerPage() {
        return 3;
    }

    @FXML
    private void selected(MouseEvent event) {
        modif.setDisable(false);
        sup.setDisable(false);

        int id = tv.getSelectionModel().getSelectedItem().getIdpr();
        produit = service.getById(id);
        from = "User";
    }

    @FXML
    private void sup(ActionEvent event) {
        int idpr = tv.getSelectionModel().getSelectedItem().getIdpr();
        es.deleteProduct(es.getById(idpr));
        CatListActive.clear();
        LoadData();
        sup.setDisable(true);
    }

    @FXML
    private void modif(ActionEvent event) {
        try {
            //int idpr = tv.getSelectionModel().getSelectedItem().getIdpr();

            //FormProdEditController.setIdpr(idpr);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/FormProdEdit.fxml"));
            Parent root;
            root = loader.load();
            modif.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        from = "User";
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/FormProdAdd.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void initData() {
        nom.setCellValueFactory(new PropertyValueFactory<Products, String>("nomp"));
        prix.setCellValueFactory(new PropertyValueFactory<Products, Double>("prix"));
        desc.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        cat.setCellValueFactory(new PropertyValueFactory<Products, String>("catProd"));
        date.setCellValueFactory(new PropertyValueFactory<Products, String>("date_exp"));

        nomAttente.setCellValueFactory(new PropertyValueFactory<Products, String>("nomp"));
        prixAttente.setCellValueFactory(new PropertyValueFactory<Products, Double>("prix"));
        descAttente.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        catAttente.setCellValueFactory(new PropertyValueFactory<Products, String>("catProd"));
        dateAttente.setCellValueFactory(new PropertyValueFactory<Products, String>("date_exp"));

        nomRefuser.setCellValueFactory(new PropertyValueFactory<Products, String>("nomp"));
        prixRefuser.setCellValueFactory(new PropertyValueFactory<Products, Double>("prix"));
        descRefuser.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        catRefuser.setCellValueFactory(new PropertyValueFactory<Products, String>("catProd"));
        dateRefuser.setCellValueFactory(new PropertyValueFactory<Products, String>("date_exp"));
    }

    private void LoadData() {
        List<Products> products = es.getAllProducts();

        List<Products> productsActive = new ArrayList<>();
        List<Products> productsAttente = new ArrayList<>();
        List<Products> productsRefuser = new ArrayList<>();

        System.out.println("Products to show for user");
        for (Products p : products) {

            System.out.println(p);
            if (p.getEtatpr() == 0 && p.getIduser() == 2) {
                productsAttente.add(p);
            } else if (p.getEtatpr() == 1 && p.getIduser() == 2) { //USER --------------------
                productsActive.add(p);
            } else if (p.getEtatpr() == -1 && p.getIduser() == 2) { //USER --------------------
                productsRefuser.add(p);
            }

        }

        CatListActive = FXCollections.observableArrayList(productsActive);
        CatListAttente = FXCollections.observableArrayList(productsAttente);
        CatListRefuser = FXCollections.observableArrayList(productsRefuser);
        tv.setItems(CatListActive);
        tvAttente.setItems(CatListAttente);
        tvRefuser.setItems(CatListRefuser);
    }

    @FXML
    private void selectedAttente(MouseEvent event) {
        supAttente.setDisable(false);
        modifAttente.setDisable(false);

        int id = tvAttente.getSelectionModel().getSelectedItem().getIdpr();
        produit = service.getById(id);
        from = "User";

    }

    @FXML
    private void selectedRefuser(MouseEvent event) {
        modifRefuser.setDisable(false);
        supRefuser.setDisable(false);
        int id = tvRefuser.getSelectionModel().getSelectedItem().getIdpr();
        produit = service.getById(id);
        from = "User";
    }

    @FXML
    private void supAttente(ActionEvent event) {
        int idpr = tvAttente.getSelectionModel().getSelectedItem().getIdpr();
        es.deleteProduct(es.getById(idpr));
        CatListAttente.clear();
        LoadData();
        supAttente.setDisable(true);
    }

    @FXML
    private void modifAttent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/FormProdEdit.fxml"));
            Parent root;
            root = loader.load();
            modifAttente.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void supRef(ActionEvent event) {
        int idpr = tvRefuser.getSelectionModel().getSelectedItem().getIdpr();
        es.deleteProduct(es.getById(idpr));
        CatListRefuser.clear();
        LoadData();
        supRefuser.setDisable(true);
    }

    @FXML
    private void modifRef(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/FormProdEdit.fxml"));
            Parent root;
            root = loader.load();
            modifRefuser.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void categorySearch(ActionEvent event) {

        String category = combobox.getValue();

        ArrayList<Products> produits = new ArrayList<>();
        if (category.equals("Tous")) {
            produits = es.getAllProducts();

        } else {
            CatProd cat = serviceCat.getCategoryByName(category);
            produits = es.searchByCategory(cat);
        }

        anchoreProducts.getChildren().clear();
        ArrayList<Products> productsActive = new ArrayList<>();

        for (Products p : produits) {
            if (p.getEtatpr() == 1) {
                productsActive.add(p);
            }
        }
        anchoreProducts.getChildren().clear();

        int count = (productsActive.size() / 3);
        if (productsActive.size() % 3 != 0) {
            count += 1;
        }
        pagination = new Pagination(count, 0);
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex, productsActive));

        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().addAll(pagination);
        anchoreProducts.getChildren().add(anchor);
    }

}
