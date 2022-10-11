package pt.ua.ies.TVQuotesAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pt.ua.ies.TVQuotesAPI.POJO.QuoteObject;

@RestController
public class QuoteForShow {
    @GetMapping("/quotes")
    public QuoteObject quote(@RequestParam(value = "slug", defaultValue = "god-father") String slug) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://movie-quote-api.herokuapp.com/v1/shows/" + slug).openConnection();
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
            Gson gson = new Gson();
            return gson.fromJson(response.toString(), QuoteObject.class);
        } else {
            System.err.println("GET request not worked");
            System.exit(1);
        }
        return null;
    }
}