package be.vdab.starwars.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class FilmForm {
    @NotBlank
    @NotEmpty
    @Positive
    private final int filmNummer;

    public FilmForm(int filmNummer) {
        this.filmNummer = filmNummer;
    }


    public int getFilmNummer() {
        return filmNummer;
    }
}
