package be.vdab.starwars.restclients;

import be.vdab.starwars.dto.people.Karakter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class DefaultKarakterClient implements KarakterClient {
    private final WebClient client;

    DefaultKarakterClient(WebClient.Builder builder) {
        client = builder.build();
    }

    @Override
    public String findByURI(String karakterURI) {
        try {
            return client.get()
                    .uri(karakterURI)
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
