package com.darkmoder.pokemonsearch.repository;

import com.darkmoder.pokemonsearch.model.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> { }
