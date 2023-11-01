package com.darkmoder.pokemonsearch;

import com.darkmoder.pokemonsearch.controller.PokemonController;
import com.darkmoder.pokemonsearch.model.PokemonDTO;
import com.darkmoder.pokemonsearch.model.entities.Pokemon;
import com.darkmoder.pokemonsearch.model.mapper.PokemonMapper;
import com.darkmoder.pokemonsearch.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PokemonControllerTest {

    @Mock
    private PokemonService pokemonService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PokemonMapper pokemonMapper;

    private PokemonController pokemonController;

    private final String URL = "https://pokeapi.co/api/v2/pokemon/";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonController = new PokemonController(pokemonService, restTemplate, pokemonMapper);
    }

    @Test
    public void testGetPokemonByName() {
        String name = "pikachu";
        PokemonDTO pokemonDTO = new PokemonDTO("pikachu", 4, 60);

        // Mocking the external API call with RestTemplate
        Mockito.when(restTemplate.getForObject(URL + name, PokemonDTO.class))
                .thenReturn(pokemonDTO);

        Pokemon pokemon = new Pokemon();
        Mockito.when(pokemonMapper.toPokemonEntity(pokemonDTO)).thenReturn(pokemon);

        ResponseEntity<?> response = pokemonController.getPokemonByName(name);

        Mockito.verify(pokemonService).savePokemonData(pokemon);
        Mockito.verify(pokemonMapper).toPokemonEntity(pokemonDTO);

        ResponseEntity<?> expectedResponse = ResponseEntity.ok(pokemonDTO);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetPokemonByNameNotFound() {
        String name = "doesnotexist";

        Mockito.when(restTemplate.getForObject(URL + name, PokemonDTO.class))
                .thenReturn(null);

        ResponseEntity<?> response = pokemonController.getPokemonByName(name);
        ResponseEntity<?> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("This pokemon was not found.");

        Mockito.verify(pokemonService, Mockito.never()).savePokemonData(Mockito.any()); // Ensure savePokemonData is not called

        Assertions.assertEquals(response, expected);
    }
}
