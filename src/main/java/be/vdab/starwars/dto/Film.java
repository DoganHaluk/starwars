package be.vdab.starwars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Film {
    @JsonProperty("title")
    private String title;
    /*@JsonProperty("director")
    private String director;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("characters")
    private String[] characters;*/

    public String getTitle() {
        return title;
    }

    /*public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String[] getCharacters() {
        return characters;
    }*/
}
