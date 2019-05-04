/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class ContactSmsController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField msg;
    @FXML
    private JFXTextField numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void send(ActionEvent event) {
        String num = numTel.getText();
        String content = msg.getText();

        if (num.length() != 8) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un numéro de téléphone!");
            alert.showAndWait();
            return;
        }

        long numero = 0;
        try {
            numero = Long.parseLong(num);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un numéro de téléphone valide (8 chiffres)!");
            alert.showAndWait();
            return;
        }

        if (content.equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un contenue");
            alert.showAndWait();
            return;
        }

        SMS smsText = new SMS();
        //smsText.SendSMS(/*username*/, /*mots de passe*/, content + "\n mon numéro est : " + numero, "+21651910254", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

        Notifications notificationBuilder = Notifications.create()
                .title("Succès")
                .text("Message envoyé avec succès")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.show();
        System.out.println("Sms has been sent successfully");
    }

}
