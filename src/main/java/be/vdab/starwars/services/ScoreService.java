package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;
import be.vdab.starwars.projections.GemiddeldeScoresPerFilm;

import java.util.List;

public interface ScoreService {
    Score findScoreByFilmIdAndGebruiker(long filmId, Gebruiker gebruiker);

    void save(Score score);

    List<GemiddeldeScoresPerFilm> findGemiddeldeScores();
}
