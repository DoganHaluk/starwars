package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;

public interface ScoreService {
    Score findScoreByFilmIdAndGebruiker(long filmId, Gebruiker gebruiker);

    void save(Score score);
}
