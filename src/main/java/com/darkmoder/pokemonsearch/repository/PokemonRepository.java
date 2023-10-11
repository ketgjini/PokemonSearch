package com.darkmoder.pokemonsearch.repository;

import com.darkmoder.pokemonsearch.model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel,Long> { }
