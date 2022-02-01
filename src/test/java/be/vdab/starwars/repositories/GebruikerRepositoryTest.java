package be.vdab.starwars.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql("/insertGebruiker.sql")
class GebruikerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final GebruikerRepository repository;

    GebruikerRepositoryTest(GebruikerRepository repository) {
        this.repository = repository;
    }

    private long idVanGebruiker() {
        return jdbcTemplate.queryForObject("select id from gebruikers where email = 'test@test'", Long.class);
    }

    @Test
    void findByEmail() {
        assertThat(repository.findByEmail("test@test").getId()).isEqualTo(idVanGebruiker());
    }
}
