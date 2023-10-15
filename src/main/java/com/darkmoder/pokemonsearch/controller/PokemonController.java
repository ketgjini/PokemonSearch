package com.darkmoder.pokemonsearch.controller;

import com.darkmoder.pokemonsearch.model.PokemonDTO;
import com.darkmoder.pokemonsearch.model.mapper.PokemonMapper;
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
    private final PokemonService pokemonService;
    private final RestTemplate restTemplate;
    private final PokemonMapper mapper;

    public PokemonController(final PokemonService pokemonService, final RestTemplate restTemplate, final PokemonMapper mapper) {
        this.pokemonService = pokemonService;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @GetMapping(path = "/pokemon/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPokemonByName(@PathVariable String name){

        final PokemonDTO pokemon = restTemplate.getForObject(API_URL + name, PokemonDTO.class);
        if(pokemon != null) {
            pokemonService.savePokemonData(mapper.toPokemonEntity(pokemon));
            return ResponseEntity.ok(pokemon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This pokemon was not found.");
    }
}
