package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;
import be.vdab.starwars.projections.GemiddeldeScoresPerFilm;
import be.vdab.starwars.repositories.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultScoreService implements ScoreService {
    private final ScoreRepository scoreRepository;

    public DefaultScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Score findScoreByFilmIdAndGebruiker(long filmId, Gebruiker gebruiker) {
        return scoreRepository.findScoreByFilmIdAndGebruiker(filmId, gebruiker);
    }

    @Override
    public void save(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public List<GemiddeldeScoresPerFilm> findGemiddeldeScores() {
        return scoreRepository.findGemiddeldeScoresPerFilm();
    }
}
