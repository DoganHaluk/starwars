package be.vdab.starwars.restclients;

import be.vdab.starwars.exceptions.FilmNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultKarakterClientTest {
    private final DefaultKarakterClient karakterClient;
    private final DefaultFilmClient filmClient;

    DefaultKarakterClientTest(DefaultKarakterClient karakterClient, DefaultFilmClient filmClient) {
        this.karakterClient = karakterClient;
        this.filmClient = filmClient;
    }

    @Test
    void findDeEertsteKarakter() {
        assertThat(karakterClient.findByURI(filmClient
                .findById(1)
                .get()
                .getResult()
                .getProperties()
                .getCharacters()
                .stream()
                .findFirst()
                .orElseThrow(FilmNietGevondenException::new)))
                .isEqualTo("Luke Skywalker");
    }
}