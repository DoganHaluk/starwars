package be.vdab.starwars.controllers;

import be.vdab.starwars.restclients.FilmClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("filmZoeken")
class FilmZoekenController {
    private final FilmClient client;

    FilmZoekenController(FilmClient client) {
        this.client = client;
    }

    @GetMapping
    public ModelAndView filmForm() {
        return new ModelAndView("filmzoeken");
    }

    @GetMapping("{id}")
    public ModelAndView getFilm(@PathVariable long id) {
        var modelAndView = new ModelAndView("filmzoeken");
        client.findById(id).ifPresent(film -> modelAndView.addObject(film));
        return modelAndView;
    }
}
