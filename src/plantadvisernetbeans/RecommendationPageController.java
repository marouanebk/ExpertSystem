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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import org.jpl7.*;

import java.util.Map;

/**
 * FXML Controller class
 *
 * @author marou
 */
public class RecommendationPageController implements Initializable {

    @FXML
    private ChoiceBox<String> climateChoiceBox;

    @FXML
    private ChoiceBox<String> humidityChoiceBox;

    @FXML
    private ChoiceBox<String> soilChoiceBox;

    private String tempo;
    @FXML
    private Pane Popup;

    @FXML
    private Label text;

    @FXML
    void Hide() {
        Popup.setVisible(false);
    }

    @FXML
    void Show() {
        Popup.setVisible(true);
    }

    void getRecommendation(String climate, String humdiity, String soil) {
        Query q = new Query("consult('./kb.pl')");
        q.hasSolution();

        Atom a = new Atom(climate);
        Compound p1 = new Compound("climate", new Term[]{a});
        Query q1 = new Query(new Compound("assertz", new Term[]{p1}));
        q1.hasSolution();
        Atom b = new Atom(humdiity);
        Compound p2 = new Compound("humidity", new Term[]{b});
        Query q2 = new Query(new Compound("assertz", new Term[]{p2}));
        q2.hasSolution();
        Atom c = new Atom(soil);
        Compound p3 = new Compound("soil", new Term[]{c});
        Query q3 = new Query(new Compound("assertz", new Term[]{p3}));
        q3.hasSolution();

        // Query for climate(X)
        Query qn = new Query("suggest_planting(X)");
        Map<String, Term> solution = qn.oneSolution();
        if (solution == null) {
            // Handle case when there is no solution
            text.setText("No recommended plant found.");
        } else {
            String temp = solution.get("X").toString();
            text.setText("We recommend: " + temp);
        }
//        text.setText(sollution.get("X").toString());
//         System.out.println(sollution.get("X"));

//        Map<String, Term>[] res = qn.allSolutions();
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i].get("X"));
//        }
    }

    @FXML
    void retrieveAndValidateChoices() {
        String climateChoice = climateChoiceBox.getValue();
        String humidityChoice = humidityChoiceBox.getValue();
        String soilChoice = soilChoiceBox.getValue();

        if (climateChoice != null && humidityChoice != null && soilChoice != null) {

//            text.setText("Banana");
            getRecommendation(climateChoice, humidityChoice, soilChoice);
            Show();
        } else {
            // Some choices are missing, print the missing ones
            tempo = "Missing choices";
            if (climateChoice == null) {
                tempo += " - Climate";
            }
            if (humidityChoice == null) {
                tempo += "- Humidity";
            }
            if (soilChoice == null) {
                tempo += "- Soil";
            }
            text.setText(tempo);
            Show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set text for each CheckBox item
        climateChoiceBox.getItems().addAll("hot", "cool", "warm");
        humidityChoiceBox.getItems().addAll("dry", "moist", "humid");
        soilChoiceBox.getItems().addAll("acidic", "alkaline", "sandy", "clayey", "rich_organic", "well_draining", "loamy");

        climateChoiceBox.setOnAction(event -> {
            System.out.println("Climate selected: " + climateChoiceBox.getValue());
        });

        humidityChoiceBox.setOnAction(event -> {
            System.out.println("Humidity selected: " + humidityChoiceBox.getValue());
        });

        soilChoiceBox.setOnAction(event -> {
            System.out.println("Soil selected: " + soilChoiceBox.getValue());
        });
    }
}
