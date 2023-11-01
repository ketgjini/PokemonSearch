package com.darkmoder.pokemonsearch;

import com.darkmoder.pokemonsearch.model.entities.Pokemon;
import com.darkmoder.pokemonsearch.repository.PokemonRepository;
import com.darkmoder.pokemonsearch.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PokemonServiceTest {
    @Mock
    private PokemonRepository pokemonRepository;

    private PokemonService pokemonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonService = new PokemonService(pokemonRepository);
    }

    @Test
    public void testSavePokemonDataSuccess() {
        Pokemon pokemon = new Pokemon("Pikachu", 4, 60);
        Mockito.when(pokemonService.savePokemonData(pokemon)).thenReturn(pokemon);

        Pokemon saved = pokemonService.savePokemonData(pokemon);
        Mockito.verify(pokemonRepository).save(pokemon);
        Assertions.assertEquals(pokemon, saved, "Should match");
    }
}
