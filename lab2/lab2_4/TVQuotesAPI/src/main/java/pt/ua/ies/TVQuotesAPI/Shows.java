package pt.ua.ies.TVQuotesAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
            JSONArray shows = new JSONArray(response.toString());
            List<ShowObject> results = new ArrayList<ShowObject>();
            for (int i = 0 ; i < shows.length(); i++) {
                JSONObject show = shows.getJSONObject(i);
                String name = show.getString("name");
                String slug = show.getString("slug");
                results.add(new ShowObject(name, slug));
            }
            return results;
        } else {
            System.err.println("GET request not worked");
            System.exit(1);
        }
        return null;
    }
}