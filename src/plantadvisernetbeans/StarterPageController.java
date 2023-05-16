/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantadvisernetbeans;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;


/**
 * FXML Controller class
 *
 * @author marou
 */
public class StarterPageController implements Initializable {

    @FXML
    private AnchorPane rout;

    @FXML
    private Button start;

    @FXML
    void navigatePage() throws IOException {
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(plantadvisernetbeans.StarterPageController.class.getResource("recommendationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage2.setScene(scene);
        stage2.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
