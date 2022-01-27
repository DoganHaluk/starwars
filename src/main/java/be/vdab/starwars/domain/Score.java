package be.vdab.starwars.domain;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long filmId;
    private int score;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gebruikerId")
    private Gebruiker gebruiker;

    public Score(long id, long filmId, int score, Gebruiker gebruiker) {
        this.id = id;
        this.filmId = filmId;
        this.score = score;
        setGebruiker(gebruiker);
    }

    protected Score() {
    }

    public long getId() {
        return id;
    }

    public long getFilmId() {
        return filmId;
    }

    public int getScore() {
        return score;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        if (!gebruiker.getScores().contains(this)) {
            this.gebruiker = gebruiker;
        }
    }
}
