package be.vdab.starwars.controllers;

import be.vdab.starwars.forms.FilmForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("filmZoeken")
class FilmZoekenController {
    @GetMapping
    public ModelAndView filmForm(){
        return new ModelAndView("filmzoeken").addObject(new FilmForm(0));
    }
}
