package pt.ua.ies.TVQuotesPersistentAPI.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Quote;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{
    Optional<List<Quote>> findByShow(String show);
}