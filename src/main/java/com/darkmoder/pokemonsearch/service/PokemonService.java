package com.darkmoder.pokemonsearch.service;

import com.darkmoder.pokemonsearch.repository.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private PokemonRepository pokemonRepository;

    public PokemonService(final PokemonRepository pokemonRepository) { this.pokemonRepository = pokemonRepository; }

}
