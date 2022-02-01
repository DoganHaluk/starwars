package be.vdab.starwars.controllers;

import be.vdab.starwars.projections.GemiddeldeScoresPerFilm;
import be.vdab.starwars.services.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gemiddeldeScores")
public class GemiddeldeScoresController {
    private final ScoreService scoreService;

    public GemiddeldeScoresController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    List<GemiddeldeScoresPerFilm> findGemiddeldeScores(){
        return scoreService.findGemiddeldeScores();
    }
}
