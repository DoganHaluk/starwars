package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;

public interface GebruikerService {
    Gebruiker findByEmail(String email);
}
