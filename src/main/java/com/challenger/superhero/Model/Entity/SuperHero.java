package com.challenger.superhero.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "super_hero")
@Data
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "super_power", columnDefinition = "VARCHAR(250)")
    private String superPower;

    @Column(name = "alter_ego", columnDefinition = "VARCHAR(100)")
    private String alterEgo;

}
