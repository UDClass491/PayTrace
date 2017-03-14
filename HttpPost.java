
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;



/**
 *
 * @author klawon
 */
public class HttpPost {  
    /**
     * this is the URL to set the post to, remember to include the HTTPS://
     */
    private final String _urlString;
    
    /**
     * 
     * @param urlString the sting of the URL, including https://
     */
    public  HttpPost(String urlString) {
        _urlString = urlString;
    }
    
    /**
     * sendPost()
     *   This is a simple method that makes a "POST" request to the URL using the urlParameters   
     * @param urlParameters from PayTrace API 
     * @throws  expection  generic exception from various objects like  URL, HTTPsUrlConnection, DataStreametc
     *            If one get ones of these exception you must quit for they are unrecoverable 
     */
    private  void sendPost(String urlParameters) throws Exception {
        URL url = new URL(_urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        //add reuqest header
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


        // Send post request
        connection.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + _urlString);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                       response.append(inputLine);
            }
        }

        //print result
        System.out.println(response.toString());

    }

    public static void main(String[] args) throws Exception {
        String PaytraceURLString = "https://paytrace.com/api/default.pay";
        HttpPost https = new HttpPost(PaytraceURLString);

        System.out.println("Testing - Send Http POST request");
        https.sendPost(GateWayParms.getParmsToCreateSalesTrans());
    }
    
}
