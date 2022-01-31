package be.vdab.starwars.controllers;

import be.vdab.starwars.exceptions.FilmNietGevondenException;
import be.vdab.starwars.restclients.FilmClient;
import be.vdab.starwars.restclients.KarakterClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("filmZoeken")
class FilmZoekenController {
    private final FilmClient filmClient;
    private final KarakterClient karakterClient;

    FilmZoekenController(FilmClient filmClient, KarakterClient karakterClient) {
        this.filmClient = filmClient;
        this.karakterClient = karakterClient;
    }

    @GetMapping
    public ModelAndView filmForm() {
        return new ModelAndView("filmzoeken");
    }

    @GetMapping("{id}")
    public ModelAndView getFilm(@PathVariable long id) {
        var modelAndView = new ModelAndView("filmzoeken");
        if (filmClient.findById(id).isEmpty()){
            modelAndView.addObject("nietGevonden", "Film is niet gevonden!");
            return modelAndView;
        }

        filmClient.findById(id).ifPresent(film -> modelAndView.addObject(film));
        var karakters = new ArrayList<>();
        for(String karakter : filmClient.findById(id).orElseThrow(FilmNietGevondenException::new).getResult().getProperties().getCharacters()){
            karakters.add(karakterClient.findByURI(karakter));
        }
        modelAndView.addObject("karakters", karakters);
        return modelAndView;
    }
}
