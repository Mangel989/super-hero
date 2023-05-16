package com.challenger.superhero.Model.Domain;

import lombok.Data;

@Data
public class SuperHeroDto {
    private Integer id;
    private String name;
    private String superPower;
    private String alterEgo;
}
