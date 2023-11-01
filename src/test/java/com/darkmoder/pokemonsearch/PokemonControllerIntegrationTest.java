package com.darkmoder.pokemonsearch;

import com.darkmoder.pokemonsearch.model.PokemonDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetPokemonByName() {
        String name = "pikachu";

        ResponseEntity<PokemonDTO> responseEntity = restTemplate.getForEntity("/api/v1/pokemon/" + name, PokemonDTO.class);

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        PokemonDTO responseBody = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, statusCode, "Expected OK status");
        Assertions.assertNotNull(responseBody, "Should not be null");
        Assertions.assertEquals(name, responseBody.name(), "Should be pikachu");
    }
}
