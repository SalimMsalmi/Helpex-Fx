/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ApiController implements Initializable {

    @FXML
    private Label States;
    @FXML
    private Label positive;
    @FXML
    private Label hospitalized;
    @FXML
    private Label deaths;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            URL Url = new URL("https://api.covidtracking.com/v1/us/current.json");

            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                try (Scanner scanner = new Scanner(Url.openStream())) {
                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                }

                System.out.println(informationString);
                 JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
               // System.out.println(dataObject.get(0));

                JSONObject countryData = (JSONObject) dataObject.get(0);

                deaths.setText(String.valueOf(countryData.get("date")));
                States.setText(String.valueOf(countryData.get("states")));
                positive.setText(String.valueOf(countryData.get("positive")));
                hospitalized.setText(String.valueOf(countryData.get("hospitalizedCurrently")));

                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
}
