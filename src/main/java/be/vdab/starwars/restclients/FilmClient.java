package be.vdab.starwars.restclients;

import be.vdab.starwars.dto.films.Film;

import java.util.Optional;

public interface FilmClient {
    Optional<Film> findById(long id);
}
