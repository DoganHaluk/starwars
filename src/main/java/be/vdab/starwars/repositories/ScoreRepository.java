package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findScoreByFilmIdAndGebruiker(long filmId, Gebruiker gebruiker);
}
