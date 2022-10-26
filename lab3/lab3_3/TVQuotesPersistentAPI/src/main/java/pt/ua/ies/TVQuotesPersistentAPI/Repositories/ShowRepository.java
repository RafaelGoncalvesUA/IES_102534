package pt.ua.ies.TVQuotesPersistentAPI.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ua.ies.TVQuotesPersistentAPI.POJOs.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{
    @Query("SELECT s FROM Show s WHERE s.slug IN (SELECT q.show FROM Quote q)")
    List<Show> findAllShowsWithQuote();
    Optional<Show> findBySlug(String showSlug);
}