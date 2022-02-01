package be.vdab.starwars.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertGebruiker.sql", "/insertScore.sql"})
public class ScoreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final ScoreRepository repository;

    public ScoreRepositoryTest(ScoreRepository repository) {
        this.repository = repository;
    }

    @Test
    void findGemiddeldeScoresPerFilm() {
        assertThat(repository.findGemiddeldeScoresPerFilm()).hasSize(6);
    }
}
