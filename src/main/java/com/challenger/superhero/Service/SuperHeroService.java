package com.challenger.superhero.Service;

import com.challenger.superhero.Exceptions.ErrorMessages;
import com.challenger.superhero.Exceptions.FailedVerificationException;
import com.challenger.superhero.Exceptions.NotFoundException;
import com.challenger.superhero.Model.Domain.SuperHeroDto;
import com.challenger.superhero.Model.Entity.SuperHero;
import com.challenger.superhero.Model.Mapper.SuperHeroMapper;
import com.challenger.superhero.Model.Repository.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("SuperHeroService")
public class SuperHeroService {

    private final SuperHeroRepository repository;
    private final SuperHeroMapper mapper;

    public SuperHeroService(SuperHeroRepository repository, SuperHeroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SuperHeroDto add(SuperHeroDto dto) throws FailedVerificationException, NotFoundException {
        if (Objects.isNull(dto)
                || Objects.isNull(dto.getName())
                || Objects.isNull(dto.getSuperPower()))
            throw new FailedVerificationException(ErrorMessages.REGISTER_NOT_FOUND);

        SuperHero entity = new SuperHero();


        entity.setName(dto.getName());
        entity.setSuperPower(dto.getSuperPower());
        entity.setAlterEgo(dto.getAlterEgo());

        repository.save(entity);

        return mapper.superHeroEntityToSuperHeroDTO(entity);
    }

    public SuperHeroDto edit(SuperHeroDto dto, Integer id) throws FailedVerificationException, NotFoundException {
        if (Objects.isNull(dto) || Objects.isNull(id))
            throw new FailedVerificationException(ErrorMessages.REGISTER_NOT_FOUND);

        SuperHero entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        if (Objects.nonNull(dto.getName()))
            entity.setName(dto.getName());
        if (Objects.nonNull(dto.getSuperPower()))
            entity.setSuperPower(dto.getSuperPower());
        if (Objects.nonNull(dto.getAlterEgo()))
            entity.setAlterEgo(dto.getAlterEgo());

        repository.save(entity);

        return mapper.superHeroEntityToSuperHeroDTO(entity);
    }

    public List<SuperHeroDto> findAll() {
        return repository.findAll().stream().map(mapper::superHeroEntityToSuperHeroDTO).collect(Collectors.toList());
    }

    public SuperHeroDto findById(int id) throws NotFoundException {
        SuperHero superHero = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        return mapper.superHeroEntityToSuperHeroDTO(superHero);
    }
    public SuperHeroDto delete(int id) throws NotFoundException{
        SuperHero entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        repository.delete(entity);
        return mapper.superHeroEntityToSuperHeroDTO(entity);
    }
    public List<SuperHeroDto> findByName(String name) throws NotFoundException{
        List<SuperHero> list = repository.findByName(name);

        return list.stream().map(mapper::superHeroEntityToSuperHeroDTO).collect(Collectors.toList());
    }

}
