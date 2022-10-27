package pt.ua.ies.TVQuotesPersistentAPI.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Quote;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{
    @Query(value = "SELECT * FROM tbl_quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandomQuote();


    @Query(value = "SELECT * FROM tbl_quote WHERE show_slug = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandomBySlug(String slug);
}