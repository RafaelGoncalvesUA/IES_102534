package pt.ua.ies.TVQuotesPersistentAPI.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.ies.TVQuotesPersistentAPI.ErrorHandling.ResourceNotFoundException;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Show;
import pt.ua.ies.TVQuotesPersistentAPI.Services.ShowService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ShowsController {
    @Autowired
    private ShowService showService;
    
    @GetMapping("/shows")
    public List<Show> getShows() {
        return showService.findAllShowsWithQuote();
    }

    @PostMapping("/show")
    public Show addShow(@RequestBody Show show) {
        return showService.save(show);
    }

    @PostMapping("/shows")
    public List<Show> addShows(@RequestBody List<Show> shows) {
        return showService.saveAll(shows);
    }

    @DeleteMapping("/show/{slug}")
    public Map<String, Boolean> deleteQuote(@PathVariable(value = "slug") String showSlug)
         throws ResourceNotFoundException {
        Show show = showService.findBySlug(showSlug)
            .orElseThrow(() -> new ResourceNotFoundException("Show not found for this id :: " + showSlug));

        showService.delete(show);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
