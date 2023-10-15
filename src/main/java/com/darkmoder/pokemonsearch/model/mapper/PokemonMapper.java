package com.darkmoder.pokemonsearch.model.mapper;

import com.darkmoder.pokemonsearch.model.PokemonDTO;
import com.darkmoder.pokemonsearch.model.entities.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class PokemonMapper {
    public abstract PokemonDTO toDTO(final Pokemon pokemon);
    public abstract Pokemon toPokemonEntity(final PokemonDTO pokemonDTO);
}
