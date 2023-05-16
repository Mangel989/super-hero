package com.challenger.superhero.Model.Repository;

import com.challenger.superhero.Model.Entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Integer> {
    @Query( "FROM SuperHero s " +
            "WHERE (s.name LIKE %:name%)")
    List<SuperHero> findByName(String name);

}
