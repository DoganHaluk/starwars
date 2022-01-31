package be.vdab.starwars.restclients;

import be.vdab.starwars.dto.people.Karakter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class DefaultKarakterClient implements KarakterClient {
    private final WebClient client;
    private final String karakterURI;

    DefaultKarakterClient(WebClient.Builder builder, @Value("${swapi.karakter}") String karakterURI) {
        client = builder.build();
        this.karakterURI = karakterURI;
    }

    @Override
    public String findById(long id) {
        try {
            return client.get()
                    .uri(karakterURI, uriBuilder -> uriBuilder.build(id))
                    .retrieve()
                    .bodyToMono(Karakter.class)
                    .block()
                    .getResult()
                    .getProperties()
                    .getName();
        } catch (WebClientResponseException.NotFound ex) {
            return null;
        }
    }
}
