package be.vdab.starwars.restclients;

import be.vdab.starwars.dto.Film;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
public class DefaultFilmClient implements FilmClient {
    private final WebClient client;
    private final String filmURI;

    DefaultFilmClient(WebClient.Builder builder, @Value("${swapi.film}") String filmURI) {
        client = builder.build();
        this.filmURI = filmURI;
    }

    @Override
    public Optional<Film> findById(long id) {
        try {
            return Optional.ofNullable(client.get()
                    .uri(filmURI, uriBuilder -> uriBuilder.build(id))
                    .retrieve()
                    .bodyToMono(Film.class)
                    .block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}
