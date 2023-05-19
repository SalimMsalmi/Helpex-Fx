/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.CaisseOrganisation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.JavaMail;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import services.CaisseOrganisationCRUD;

public class StripeController {
    public int caisseId;
    public CaisseOrganisation caisse;

    public void setCaisseId(int caisseId) {
        this.caisseId = caisseId;
    }

    
    @FXML
    public TextField montantText;
    public TextField numeroTel;
    @FXML
private TextField cardNumberField;

@FXML
private TextField nameField;

@FXML
private TextField expMonthField;

@FXML
private TextField expYearField;

@FXML
private TextField cvcField;

    public static final String ACCOUNT_SID = "AC35f3e6bdc59a86fd5d3763e8d3e093a6";
    public static final String AUTH_TOKEN = "b98f6255b040afaa8779af5c687286dd";

    public CaisseOrganisation getCaisse() {
        return caisse;
    }

    public void setCaisse(CaisseOrganisation caisse) {
        this.caisse = caisse;
    }
    
    @FXML
public void processPayment() throws StripeException {

    Stripe.apiKey = "sk_test_51MhCUgHv0arDT0U0P19vmMrNfUVhnrgr7oLZC6LOOXnbTEcciLDUPqrehv7UVbWDnCggUNFZegmbAuyK6wzwtEDI00F2fvASZc";
    if(controleDeSaisie()){
        String cardNumber = cardNumberField.getText();
        String telText = numeroTel.getText();
        int expMonth = Integer.parseInt(expMonthField.getText());
        int expYear = Integer.parseInt(expYearField.getText());
        String cvc = cvcField.getText();

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();

        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);

        Map<String, Object> chargeParams = new HashMap<>();

        int montant=Integer.parseInt(montantText.getText());
        chargeParams.put("amount", montant*100);
        chargeParams.put("currency", "eur");
        chargeParams.put("source", token.getId());
        chargeParams.put("description", "Paiement pour commande n°123");

        try {
            Charge charge = Charge.create(chargeParams);
            //sendSMS(montant,numeroTel.getText());
            JavaMail.sendMail("oussema.ayari2001@gmail.com");
            System.out.println("Paiement réussi : " );
            CaisseOrganisationCRUD caisseCRUD=new CaisseOrganisationCRUD();
        caisse=caisseCRUD.CaisseById(caisseId);
        int newmontant=(int) caisse.getMontant_caisse_org()+montant;
        caisseCRUD.modifierMontantCaisse(caisseId, newmontant);
        } catch (CardException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }else {
        System.out.println("erreur !!");
    }



}

public boolean controleDeSaisie(){
    boolean monthYear=false;
    if (testPattern("[0-9][0-9]",expMonthField.getText()) && testPattern("[0-9][0-9]",expYearField.getText())){
        int expMonth = Integer.parseInt(expMonthField.getText());
        int expYear = Integer.parseInt(expYearField.getText());
        if(expMonth>0 && expMonth <=12 && expYear>22)
            monthYear=true;
    }

    return  testPattern("[0-9]+",montantText.getText()) &&
            testPattern("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",cardNumberField.getText()) &&
            testPattern("[0-9][0-9][0-9]",cvcField.getText()) &&
            testPattern("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",numeroTel.getText()) &&
            monthYear ;

}
public boolean testPattern(String regex, String input){
    Pattern pattern=Pattern.compile(regex);
    Matcher matcher=pattern.matcher(input);

    return matcher.find();
}

public void sendSMS(int montant,String numeroTel) throws IOException {
    String endpointUrl = "https://api.twilio.com/2010-04-01/Accounts/AC35f3e6bdc59a86fd5d3763e8d3e093a6/Messages.json";
    String numeroTelephone="+216"+numeroTel;
    Map<String, String> parameters = new HashMap<>();
    parameters.put("To", "+21623398991");
    parameters.put("From", "+19103354023");
    parameters.put("Body", "paiement reussi de "+montant+" €");

    URL url = new URL(endpointUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    String username = "AC35f3e6bdc59a86fd5d3763e8d3e093a6";
    String password = "b98f6255b040afaa8779af5c687286dd";
    String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

    connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
    connection.setDoOutput(true);
    connection.setDoOutput(true);

    String requestBody = encodeFormData(parameters);
    OutputStream outputStream = connection.getOutputStream();
    byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
    outputStream.write(requestBodyBytes);
    outputStream.close();

    int responseCode = connection.getResponseCode();
    System.out.println("Response Code : " + responseCode);
}
    private static String encodeFormData(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "utf-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));
        }

        return sb.toString();
    }
public void afficherMsgErreur(){

}
}
    


