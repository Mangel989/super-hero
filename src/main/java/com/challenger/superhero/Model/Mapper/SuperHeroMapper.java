package com.challenger.superhero.Model.Mapper;

import com.challenger.superhero.Model.Domain.SuperHeroDto;
import com.challenger.superhero.Model.Entity.SuperHero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuperHeroMapper {
    SuperHero superHeroDTOToSuperHeroEntity(SuperHeroDto superHeroDTO);
    SuperHeroDto superHeroEntityToSuperHeroDTO(SuperHero superHero);

}
