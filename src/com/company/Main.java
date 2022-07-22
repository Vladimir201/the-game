package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Hero hero = new Hero();
        hero.life = 200;
        hero.damage = 1;
        hero.armor = 1;
        hero.attack = 1;
        hero.experience = 0;
        hero.level = 1;
        hero.movedanj = 0;

        Monster skeleton = new Monster();
        skeleton.name = "Skeleton";
        skeleton.life = 20;
        skeleton.damage = 3;
        skeleton.givesExperience = 100;

        Monster skeletonMage = new Monster();
        skeletonMage.name = "Skeleton Mage";
        skeletonMage.life = 30;
        skeletonMage.damage = 4;
        skeletonMage.givesExperience = 150;

        Monster megaOgr = new Monster();
        megaOgr.name = "Mega Ogr";
        megaOgr.life = 50;
        megaOgr.damage = 5;
        megaOgr.givesExperience = 300;

        Room room = new Room(skeleton);
        Room room1 = new Room(skeletonMage);
        Room room2 = new Room(megaOgr);


        Room[] map = {room, room1, room2};

        while (true) {

            System.out.println("для того что бы герой походил надо написать 1");
            String s = scanner.nextLine();
            if (s.equals("1")) {
                System.out.println("герой походил");
                hero.distance++;
                System.out.println("герой прошел " + hero.distance + " шагов");
                if (hero.distance > 3) {
                    System.out.println("перед вами данжен, для того чтобы зайти в данжен нажмите 2");
                    s = scanner.nextLine();

                    if (s.equals("2")) {
                        while (true) {
                            System.out.println("герой находится в данжене, для хождению по данжену нажмите 3");
                            s = scanner.nextLine();
                            if (s.equals("3")) {
                                System.out.println("Hero passed");
                                hero.movedanj++;
                                System.out.println(hero.movedanj);
                                System.out.println("the hero walked " + hero.movedanj + " the steps");

                                if (hero.movedanj == 2) {
                                    System.out.println("you met a skeleton, press 4 to hit");

                                    startFight(random,hero,map,s);
                                }
                            }
                            if (hero.movedanj == 4) {

                                System.out.println("You met a SkeletonMage, press 4 to hit ");

                                startFight(random,hero,map,s);
                            }
                            if (hero.movedanj == 6) {
                                System.out.println("Вы встретили мега Огра Fedorar!!! Он хочет уничтожить вас!!!" +
                                        "Press 4 to hit this monster");

                                startFight(random,hero,map,s);
                            }
                        }
                    }
                }
            }
            else if (s.equals("0")) {
                System.out.println("вы вышли");
                break;
            } else {
                System.out.println("нет такого действия");
            }
        }
    }

    private  static void parameterHero(Hero hero, Monster monster){
        hero.experience += monster.givesExperience;
        hero.numberDanjen ++;
        if (hero.experience >= 100) {
            hero.experience -= 100;
            hero.level++;
            hero.damage++;
            hero.attack++;
            hero.armor++;
            hero.life+=10;
            System.out.println("Поздравляем! Вы победили монстра!");
            System.out.println(" Lvl " + hero.level +
                               " Ex " + hero.experience +
                                " защита " + hero.armor +
                                 " атака " + hero.attack +
                                 " жизни " + hero.life);
        }
    }

    private static void startFight(Random random, Hero hero, Room[] map, String s){
        if (s.equals("4")) {
            while (true) {
                boolean isFightGoing = fight(random, hero, map);
                if (!isFightGoing){
                    break;
                }
            }
        }
    }
    private static boolean fight(Random random, Hero hero, Room[] map ){

        hitHero(random, hero, map[hero.numberDanjen].monster, 5);
        hitMonster(random, hero, map[hero.numberDanjen].monster, 6);

        if (hero.life <= 0 ){
            return false;
        }

        if (map[hero.numberDanjen].monster.life <= 0) {
            parameterHero(hero, map[hero.numberDanjen].monster);
            return false;
        }
        return true;
    }

    private static void hitMonster(Random random, Hero hero, Monster monster, int chance) {
        int chanceToHitHero = random.nextInt();
        if (chanceToHitHero > chance) {
            monster.life = monster.life - (hero.attack + hero.damage);
            System.out.println("У монстра осталось " + monster.life + " жизней");
        }
    }

    private static void hitHero(Random random, Hero hero, Monster monster, int chance) {
        int chanceToHit = random.nextInt(10);
        if (chanceToHit > chance) {

            int damage = monster.damage - hero.armor;
            hero.life = hero.life - damage;
            System.out.println("монстер " + monster.name + " бьет героя на " + damage);
            System.out.println("герой имеет" +hero.life + " жизней" );
        }
    }
}