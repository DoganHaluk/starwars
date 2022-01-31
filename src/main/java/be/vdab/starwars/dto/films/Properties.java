package be.vdab.starwars.dto.films;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Properties {
    @JsonProperty("title")
    private String title;
    @JsonProperty("director")
    private String director;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("characters")
    private List<String> characters;

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }
}
