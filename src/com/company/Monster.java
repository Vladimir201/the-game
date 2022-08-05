package com.company;

public class Monster {

    public Monster(Integer life, Integer damage, Integer givesExperience, String name){

        this.life = life;
        this.damage = damage;
        this.givesExperience = givesExperience;
        this.name = name;
    }
    public int life;

    public String name;

    public int damage;

    public int givesExperience;

}
