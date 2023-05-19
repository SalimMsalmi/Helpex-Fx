import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;

public class Exemple {
    public static final String ACCOUNT_SID = System.getenv("AC5a8aebca0ead5f90b81f948c9f9c12c2");
    public static final String AUTH_TOKEN = System.getenv("c1534113c9053a2f99ecdc19f4c8523b");
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure


    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ValidationRequest validationRequest = ValidationRequest.creator(
                        new com.twilio.type.PhoneNumber("+21621339405"))
                .setFriendlyName("My Home Phone Number")
                .create();

        System.out.println(validationRequest.getFriendlyName());
    }
}