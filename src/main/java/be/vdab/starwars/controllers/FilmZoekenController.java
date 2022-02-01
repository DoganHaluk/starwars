package be.vdab.starwars.controllers;

import be.vdab.starwars.domain.Score;
import be.vdab.starwars.exceptions.FilmNietGevondenException;
import be.vdab.starwars.forms.ScoreForm;
import be.vdab.starwars.restclients.FilmClient;
import be.vdab.starwars.restclients.KarakterClient;
import be.vdab.starwars.services.GebruikerService;
import be.vdab.starwars.services.ScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("filmZoeken")
class FilmZoekenController {
    private final FilmClient filmClient;
    private final KarakterClient karakterClient;
    private final ScoreService scoreService;
    private final GebruikerService gebruikerService;

    FilmZoekenController(FilmClient filmClient, KarakterClient karakterClient, ScoreService scoreService, GebruikerService gebruikerService) {
        this.filmClient = filmClient;
        this.karakterClient = karakterClient;
        this.scoreService = scoreService;
        this.gebruikerService = gebruikerService;
    }

    @GetMapping
    public ModelAndView filmForm() {
        return new ModelAndView("filmzoeken");
    }

    @GetMapping("{id}")
    public ModelAndView getFilm(@PathVariable long id, Principal principal) {
        var modelAndView = new ModelAndView("filmzoeken");

        if (filmClient.findById(id).isEmpty()) {
            modelAndView.addObject("nietGevonden", "Film is niet gevonden!");
            return modelAndView;
        }

        var score = scoreService.findScoreByFilmIdAndGebruiker(id, gebruikerService.findByEmail(principal.getName()));
        if (score != null) {
            modelAndView.addObject("score", score);
        }

        filmClient.findById(id).ifPresent(film -> modelAndView
                .addObject("film", film)
                .addObject("scoreForm", new ScoreForm(0)));
        var karakters = new ArrayList<>();
        for (String karakter : filmClient.findById(id).orElseThrow(FilmNietGevondenException::new).getResult().getProperties().getCharacters()) {
            karakters.add(karakterClient.findByURI(karakter));
        }
        modelAndView.addObject("karakters", karakters);

        return modelAndView;
    }

    @PostMapping("/{id}/score")
    public String setScore(@PathVariable long id, @Valid ScoreForm form, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            return "redirect:/filmZoeken/" + id;
        }
        var gebruiker = gebruikerService.findByEmail(principal.getName());
        var score = new Score(id, form.getScore(), gebruiker);
        scoreService.save(score);
        return "redirect:/filmZoeken/" + id;
    }
}
