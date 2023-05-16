package com.challenger.superhero.Controller;

import com.challenger.superhero.Exceptions.FailedVerificationException;
import com.challenger.superhero.Exceptions.NotFoundException;
import com.challenger.superhero.Model.Domain.SuperHeroDto;
import com.challenger.superhero.Service.SuperHeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Superheros")
public class SuperHeroController {
    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @PostMapping
    @technical.test.superheroes.Annotations.Count
    public ResponseEntity<SuperHeroDto> save(@RequestBody SuperHeroDto dto) throws FailedVerificationException, NotFoundException {
        return ResponseEntity.ok().body(superHeroService.add(dto));
    }

    @GetMapping
    public ResponseEntity<List<SuperHeroDto>> findAll() {
        return new ResponseEntity<>(superHeroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroDto> findById(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok().body(superHeroService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperHeroDto> edit(@RequestBody SuperHeroDto dto, @PathVariable int id) throws FailedVerificationException, NotFoundException {
        dto.setId(id);
        return ResponseEntity.ok().body(superHeroService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuperHeroDto> delete(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok().body(superHeroService.delete(id));
    }

    @GetMapping("/filterByName")
    public ResponseEntity<List<SuperHeroDto>> filterByName(@RequestParam String name) throws NotFoundException {
        return new ResponseEntity<>(superHeroService.findByName(name), HttpStatus.OK);
    }

}
