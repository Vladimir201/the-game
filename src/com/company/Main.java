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

        Monster monkeyKing = new Monster();
        monkeyKing.name = " Jack";
        monkeyKing.life = 100;
        monkeyKing.damage = 8;
        monkeyKing.givesExperience = 300;

        Monster lolita = new Monster();
        lolita.name = "Lara Mara Fara Tara";
        lolita.life = 75;
        lolita.damage = 7;
        lolita.givesExperience = 300;

        Monster bloodMaster = new Monster();
        bloodMaster.name = "Mega Ogr";
        bloodMaster.life = 50;
        bloodMaster.damage = 5;
        bloodMaster.givesExperience = 300;

        Monster beetle = new Monster();
        beetle.name = "Juja";
        beetle.life = 40;
        beetle.damage = 5;
        beetle.givesExperience = 300;

        Monster dragon = new Monster();
        dragon.name = "Manini Munyanya";
        dragon.life = 150;
        dragon.damage = 15;
        dragon.givesExperience = 300;

        Monster leftHand = new Monster();
        leftHand.name = "Marfa";
        leftHand.life = 40;
        leftHand.damage = 5;
        leftHand.givesExperience = 300;

        Room room = new Room(1, skeleton);
        Room room1 = new Room(2,skeletonMage);
        Room room2 = new Room(3,megaOgr);
        Room room3 = new Room(4,leftHand);
        Room room4 = new Room(5,monkeyKing);
        Room room5 = new Room(6,dragon);
        Room room6 = new Room(7,lolita);
        Room room7 = new Room(8,bloodMaster);
        Room room8 = new Room(9,beetle);

        Room[] map = {room, room1, room2, room3, room4, room5, room6, room7, room8};


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
                            //room
                            Room num = map[hero.numberDanjen];

                            s = scanner.nextLine();
                            if (s.equals("3")) {
                                System.out.println("Hero passed");
                                hero.movedanj++;
                                System.out.println(hero.movedanj);
                                System.out.println("the hero walked " + hero.movedanj + " the steps");

                                if(hero.movedanj == num.num){
                                    System.out.println("you met a monster, press 4 to hit");

                                    if (!startFight(random,hero,num,s)){
                                        hero.numberDanjen++;
                                    }
                                }
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
        System.out.println(" номер данжена параметр героя " +hero.numberDanjen);
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

    private static boolean startFight(Random random, Hero hero, Room room, String s){
        if (s.equals("4")) {
            while (true) {
                boolean isFightGoing = fight(random, hero, room);
                if (!isFightGoing){
                   return  true;
                }
            }
        } else {
            return false;
            }
    }
    private static boolean fight(Random random, Hero hero, Room room ){

        hitHero(random, hero, room.monster, 5);
        hitMonster(random, hero, room.monster, 6);

        if (hero.life <= 0 ){
            return false;
        }

        if (room.monster.life <= 0) {
            parameterHero(hero, room.monster);
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