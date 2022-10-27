package pt.ua.ies.TVQuotesPersistentAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Quote;
import pt.ua.ies.TVQuotesPersistentAPI.Repositories.QuoteRepository;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    public Optional<Quote> getRandomQuote() {
        return quoteRepository.getRandomQuote();
    }

    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Quote> saveAll(List<Quote> quotes) {
        return quoteRepository.saveAll(quotes);
    }

    public void delete(Quote quote) {
        quoteRepository.delete(quote);
    }

    public Optional<Quote> findById(Long id) {
        return quoteRepository.findById(id);
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Optional<Quote> getRandomBySlug(String slug) {
        return quoteRepository.getRandomBySlug(slug);
    }
}
