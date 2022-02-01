package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.repositories.GebruikerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultGebruikerService implements GebruikerService {
    private final GebruikerRepository gebruikerRepository;

    public DefaultGebruikerService(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Gebruiker findByEmail(String email) {
        return gebruikerRepository.findByEmail(email);
    }
}
