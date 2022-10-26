package pt.ua.ies.TVQuotesPersistentAPI.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Quote;
import pt.ua.ies.TVQuotesPersistentAPI.Services.QuoteService;
import pt.ua.ies.TVQuotesPersistentAPI.ErrorHandling.ResourceNotFoundException;

@RestController
public class QuotesController {
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public Quote getRandomQuote() 
        throws ResourceNotFoundException {
        Quote randomQuote = quoteService.getRandomQuote();
        if (randomQuote == null)
            throw new ResourceNotFoundException("No quotes found");
        return randomQuote;
    }

    @PostMapping("/quote")
    public Quote addQuote(@Valid @RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @PostMapping("/quotes")
    public List<Quote> addQuotes(@Valid @RequestBody List<Quote> quotes) {
        return quoteService.saveAll(quotes);
    }

    @DeleteMapping("/quote/{id}")
    public Map<String, Boolean> deleteQuote(@PathVariable(value = "id") Long quoteId)
         throws ResourceNotFoundException {
        Quote quote = quoteService.findById(quoteId)
            .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + quoteId));

        quoteService.delete(quote);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getQuoteByShow(@RequestParam String show)
        throws ResourceNotFoundException {
        List<Quote> quotes = quoteService.findByShow(show)
            .orElseThrow(() -> new ResourceNotFoundException("No quotes for this show :: " + show));
        return ResponseEntity.ok().body(quotes);
    }
}