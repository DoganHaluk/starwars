package be.vdab.starwars.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultFilmClientTest {
    private final DefaultFilmClient filmClient;

    DefaultFilmClientTest(DefaultFilmClient filmClient) {
        this.filmClient = filmClient;
    }

    @Test
    void findBestaandeFilm() {
        assertThat(filmClient.findById(1))
                .hasValueSatisfying(film -> assertThat(film.getResult().getProperties().getTitle()).isEqualTo("A New Hope"));
    }

    @Test
    void findOnbestaandeFilm() {
        assertThat(filmClient.findById(-1)).isEmpty();
    }
}
