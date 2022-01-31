package be.vdab.starwars.dto.people;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
