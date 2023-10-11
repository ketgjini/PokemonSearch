package com.darkmoder.pokemonsearch.controller;

import com.darkmoder.pokemonsearch.model.PokemonDTO;
import com.darkmoder.pokemonsearch.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class PokemonController {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";
    private PokemonService pokemonService;
    private RestTemplate restTemplate;

    public PokemonController(final PokemonService pokemonService, final RestTemplate restTemplate) {
        this.pokemonService = pokemonService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/pokemon/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPokemonByName(@PathVariable String name){

        final PokemonDTO pokemon = restTemplate.getForObject(API_URL + name, PokemonDTO.class);
        if(pokemon != null) {
            return ResponseEntity.ok(pokemon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This pokemon was not found.");
    }
}
