package be.vdab.starwars.projections;

import java.math.BigDecimal;

public interface GemiddeldeScoresPerFilm {
    long getFilmId();

    BigDecimal getGemiddelde();
}
