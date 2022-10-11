package pt.ua.ies.TVQuotesAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import pt.ua.ies.TVQuotesAPI.POJO.ShowObject;

@RestController
public class Shows {
    @GetMapping("/shows")
    public List<ShowObject> shows() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://movie-quote-api.herokuapp.com/v1/shows/").openConnection();
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
            Type showListType = new TypeToken<ArrayList<ShowObject>>(){}.getType();
            ArrayList<ShowObject> results = gson.fromJson(response.toString(), showListType);
            return results;
        } else {
            System.err.println("GET request not worked");
            System.exit(1);
        }
        return null;
    }
}