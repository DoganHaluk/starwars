package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;
import be.vdab.starwars.projections.GemiddeldeScoresPerFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findScoreByFilmIdAndGebruiker(long filmId, Gebruiker gebruiker);

    @Query("select distinct(s.filmId) as filmId, avg(s.score) as gemiddelde from Score s group by s.filmId order by s.filmId")
    List<GemiddeldeScoresPerFilm> findGemiddeldeScoresPerFilm();
}
