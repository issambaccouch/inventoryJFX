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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.System.exit;
import static produit.Produit.from;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class ListProdController implements Initializable {

    @FXML
    private TableView<Products> tv;
    @FXML
    private Button sup;
    @FXML
    private Button modif;
    ProductService es = new ProductService();

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
    private AnchorPane MainAnchore;
    @FXML
    private JFXTextField search;
    @FXML
    private Button ajout;

    private static ObservableList<Products> CatList;
    private static ObservableList<Products> CatListActive;

    public static Products produit = new Products();
    private ProductService service = new ProductService();
    @FXML
    private MenuItem ajouterCat;
    @FXML
    private AnchorPane MainAnchore1;
    @FXML
    private TableColumn<Products, String> nom1;
    @FXML
    private TableColumn<Products, Double> prix1;
    @FXML
    private TableColumn<Products, String> desc1;
    @FXML
    private TableColumn<Products, String> cat1;
    @FXML
    private TableColumn<Products, String> date1;
    @FXML
    private Button sup1;
    @FXML
    private Button modif1;
    @FXML
    private JFXTextField search1;
    @FXML
    private TableView<Products> tabAttente;
    @FXML
    private Button confirmer;
    @FXML
    private Button refuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initData();
        try {
            LoadData();
        } catch (ParseException ex) {
        }

        sup.setDisable(true);
        sup1.setDisable(true);
        modif.setDisable(true);
        modif1.setDisable(true);
        confirmer.setDisable(true);
        refuser.setDisable(true);

        ArrayList<String> possStrings = new ArrayList<>();
        ArrayList<String> possStringsAttente = new ArrayList<>();
        for (int i = 0; i < es.getAllProducts().size(); i++) {
            if (es.getAllProducts().get(i).getEtatpr() == 1) {
                possStrings.add(es.getAllProducts().get(i).getNomp());
            } else {
                possStringsAttente.add(es.getAllProducts().get(i).getNomp());
            }
        }
        TextFields.bindAutoCompletion(search, possStrings);
        TextFields.bindAutoCompletion(search1, possStringsAttente);

        FilteredList<Products> filteredData = new FilteredList<>(CatListActive, p -> true);
        search.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) -> {
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

        FilteredList<Products> filteredDataAttente = new FilteredList<>(CatList, p -> true);
        search1.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) -> {
            filteredDataAttente.setPredicate(products -> {
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

        SortedList<Products> sortedDataAttente = new SortedList<>(filteredDataAttente);
        sortedDataAttente.comparatorProperty().bind(tabAttente.comparatorProperty());
        tabAttente.setItems(sortedDataAttente);

    }

    private void initData() {
        nom.setCellValueFactory(new PropertyValueFactory<Products, String>("nomp"));
        prix.setCellValueFactory(new PropertyValueFactory<Products, Double>("prix"));
        desc.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        cat.setCellValueFactory(new PropertyValueFactory<Products, String>("catProd"));
        date.setCellValueFactory(new PropertyValueFactory<Products, String>("date_exp"));

        nom1.setCellValueFactory(new PropertyValueFactory<Products, String>("nomp"));
        prix1.setCellValueFactory(new PropertyValueFactory<Products, Double>("prix"));
        desc1.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        cat1.setCellValueFactory(new PropertyValueFactory<Products, String>("catProd"));
        date1.setCellValueFactory(new PropertyValueFactory<Products, String>("date_exp"));
    }

    private void LoadData() throws ParseException {
        List<Products> products = es.getAllProducts();

        List<Products> productsActive = new ArrayList<>();
        List<Products> productsAttente = new ArrayList<>();

        for (Products p : products) {
            System.out.println(p);
            String sDate1 = p.getDate_exp();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

            if (date1.compareTo(new Date()) > 0) {
                System.out.println("not yet");
            } else if (date1.compareTo(new Date()) < 0) {
            }

            System.out.println(date1);
            if (p.getEtatpr() == 0) {
                productsAttente.add(p);
            } else if (p.getEtatpr() == 1) {
                productsActive.add(p);
            }
        }

        CatList = FXCollections.observableArrayList(productsAttente);
        CatListActive = FXCollections.observableArrayList(productsActive);
        tv.setItems(CatListActive);
        tabAttente.setItems(CatList);
    }

    @FXML
    private void newCat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/FormCatAdd.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void newProd(ActionEvent event) throws IOException {
        from = "Admin";
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/FormProdAdd.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(ActionEvent event) {
        exit(0);
    }

    @FXML
    private void clicAbout(ActionEvent event) {
    }

    @FXML
    private void sup(ActionEvent event) throws ParseException {
        int idpr = tv.getSelectionModel().getSelectedItem().getIdpr();
        es.deleteProduct(es.getById(idpr));
        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

    @FXML
    private void modif(ActionEvent event) {
        from = "Admin";
        try {
            System.out.println(tv.getSelectionModel().getSelectedItem());
            int idpr = tv.getSelectionModel().getSelectedItem().getIdpr();

            produit = tv.getSelectionModel().getSelectedItem();
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
        from = "Admin";
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/FormProdAdd.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void selected(MouseEvent event) {
        sup.setDisable(false);
        modif.setDisable(false);
        int id = tv.getSelectionModel().getSelectedItem().getIdpr();
        produit = service.getById(id);
        from = "Admin";
        System.out.println(produit);
    }

    @FXML
    private void Activer(ActionEvent event) throws ParseException {
        Products p = tabAttente.getSelectionModel().getSelectedItem();
        ProductService service = new ProductService();
        service.activerProduit(p);
        confirmer.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Produit activé avec succès");
        alert.showAndWait();

        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

    @FXML
    private void selectedAttente(MouseEvent event) {
        confirmer.setDisable(false);
        modif1.setDisable(false);
        sup1.setDisable(false);
        refuser.setDisable(false);
    }

    @FXML
    private void refresh(ActionEvent event) throws ParseException {
        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

    @FXML
    private void refresh2(ActionEvent event) throws ParseException {
        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

    @FXML
    private void sup1(ActionEvent event) throws ParseException {
        int idpr = tabAttente.getSelectionModel().getSelectedItem().getIdpr();
        es.deleteProduct(es.getById(idpr));
        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

    @FXML
    private void modif1(ActionEvent event) {
        from = "Admin";
        try {
            System.out.println(tabAttente.getSelectionModel().getSelectedItem());
            int idpr = tabAttente.getSelectionModel().getSelectedItem().getIdpr();

            produit = tabAttente.getSelectionModel().getSelectedItem();
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
    private void tabAttenteT(Event event) {

    }

    @FXML
    private void stats(ActionEvent event) {
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Statistiques");
        stage.setWidth(500);
        stage.setHeight(500);

        CatProdService service = new CatProdService();
        ArrayList<Products> produits = es.getAllProducts();
        ArrayList<CatProd> categories = service.getAllCatProd();

        Map map = new HashMap();

        for (CatProd cat : categories) {
            int i = 0;
            for (Products p : produits) {

                if (p.getCatProd().getNomcp().equals(cat.getNomcp())) {
                    i++;
                }
                map.put(cat.getNomcp(), i);

            }
        }

        Set set = map.entrySet();//Converting to Set so that we can traverse  
        Iterator itr = set.iterator();

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();

        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            pieChartData.add(new PieChart.Data(entry.getKey().toString(), (int) entry.getValue()));
        }
        
        pieChartData.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ",data.pieValueProperty(), " "
                        )
                )
        );

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistiques des catégories");

        //chart.setLabelLineLength(10);
        // chart.setLegendSide(Side.LEFT);
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void refuser(ActionEvent event) throws ParseException {
        Products p = tabAttente.getSelectionModel().getSelectedItem();
        ProductService service = new ProductService();
        service.refuserProduit(p);
        refuser.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Produit refusé avec succès");
        alert.showAndWait();

        CatList.clear();
        CatListActive.clear();
        LoadData();
    }

}
