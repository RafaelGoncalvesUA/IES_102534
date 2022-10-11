package pt.ua.ies.TVQuotesAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Quote {
    @GetMapping("/quote")
    public QuoteObject quote() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://movie-quote-api.herokuapp.com/v1/quote/").openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONObject obj = new JSONObject(response.toString());
            String quote = obj.getString("quote");
            String role = obj.getString("role");
            String show = obj.getString("show");
            boolean contain_adult_lang = obj.getBoolean("contain_adult_lang");
            return new QuoteObject(quote, role, show, contain_adult_lang);

        } else {
            System.err.println("GET request not worked");
            System.exit(1);
        }
        return null;
    }
}