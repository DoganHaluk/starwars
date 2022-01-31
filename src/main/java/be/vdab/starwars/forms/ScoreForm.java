package be.vdab.starwars.forms;

import org.hibernate.validator.constraints.Range;

public class ScoreForm {
    @Range(min = 1, max = 10)
    private final int score;

    public ScoreForm(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
