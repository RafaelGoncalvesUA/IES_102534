package pt.ua.ies.TVQuotesPersistentAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Show;
import pt.ua.ies.TVQuotesPersistentAPI.Repositories.ShowRepository;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> findAllShowsWithQuote() {
        return showRepository.findAllShowsWithQuote();
    }

    public Show save(Show show) {
        return showRepository.save(show);
    }

    public List<Show> saveAll(List<Show> shows) {
        return showRepository.saveAll(shows);
    }

    public Optional<Show> findBySlug(String showSlug) {
        return showRepository.findBySlug(showSlug);
    }

    public void delete(Show show) {
        showRepository.delete(show);
    }
}