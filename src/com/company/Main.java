package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static Configuration configuration = new Configuration();

    public static void main(String[] args) {

        Hero hero = configuration.preparedHero();
        Room[] map = configuration.preparedWorld();

        while (true) {
            if (map.length == hero.numberDanjen) {
                System.out.println("Congratulation! you are win!");
                break;
            }
            System.out.println("для того что бы герой походил надо написать 1");
            String s = scanner.nextLine();
            if (s.equals("1")) {
                System.out.println("герой походил");
                hero.distance++;
                System.out.println("герой прошел " + hero.distance + " шагов");
                if (hero.distance > 1) {
                    System.out.println("перед вами данжен, для того чтобы зайти в данжен нажмите 2");
                    s = scanner.nextLine();

                    if (s.equals("2")) {
                        while (true) {
                            System.out.println(" Герой находится в данжене, для хождению по комнатам и раздече люлей 4 ");
                            System.out.println(" Если хотите пропустить комнату и раздачу люлей нажмите 3 ");
                            if (map.length == hero.numberDanjen) {
                                System.out.println("Congratulation! you are win!");
                                break;
                            }
                            Room num = map[hero.numberDanjen];

                            s = scanner.nextLine();
                            if (s.equals("4")) {
                                System.out.println("Hero passed");
                                hero.movedanj++;
                                hero.numberDanjen++;
                                System.out.println(hero.movedanj);
                                System.out.println(" Герой прошел по данжену  " + hero.movedanj + " шагов ");

                                if (num.num == hero.movedanj) {

                                    System.out.println(" Вы зашли в комнату для того что бы подраться нажмите 4 ");

                                    if (!startFight(hero, num)) {

                                        System.out.println(hero.numberDanjen);
                                        System.out.println(hero.movedanj);
                                        System.out.println(num.num);
                                    }
                                }
                            } else if (s.equals("3")) {
                                hero.numberDanjen++;
                                hero.movedanj++;
                                num.num = hero.movedanj;
                                System.out.println(hero.numberDanjen);
                                System.out.println(hero.movedanj);
                                System.out.println(num.num);
                                System.out.println(" Герой походил по данжену " + hero.movedanj + " шагов ");
                            }
                        }
                    }
                }
            } else if (s.equals("0")) {
                System.out.println("вы вышли");
                break;
            } else {
                System.out.println("нет такого действия");
            }
        }
    }


    private static void parameterHero(Hero hero, Monster monster) {
        hero.experience += monster.givesExperience;

        System.out.println(" Номер данжена параметр героя " + hero.numberDanjen);
        for (int i = 0; i < 100; i++) {
            if (hero.experience >= 100) {
                hero.experience -= 100;
                hero.level++;
                hero.damage++;
                hero.attack++;
                hero.armor++;
                hero.life += 10;
                System.out.println("Поздравляем! Вы победили монстра!");
                System.out.println(" Lvl " + hero.level +
                        " Ex " + hero.experience +
                        " защита " + hero.armor +
                        " атака " + hero.attack +
                        " жизни " + hero.life);
            }
        }
    }

    private static boolean startFight(Hero hero, Room room) {

        while (true) {
            boolean isFightGoing = fight(hero, room);
            if (!isFightGoing) {
                return true;
            }
        }
    }

    private static boolean fight(Hero hero, Room room) {

        for (int i = 0; i < room.monsters.length; i++) {
            if (room.monsters[i].life > 0) {
                hitHero(hero, room.monsters[i], 5);
            }
        }
        // посчитать количество живых монстров и поменять колличество живых монстров room.monsters.length
        System.out.println("количество монстров " + room.monsters.length + " . Введите на клавиатуре от 0 до " + (room.monsters.length - 1));

        int choice = Integer.parseInt(scanner.nextLine());
        hitMonster(hero, room.monsters[choice], 6);
        System.out.println(" У " + room.monsters[choice].name + " осталось жизней " + room.monsters[choice].life);
        if (room.monsters[choice].life <= 0) {
            room.monsters[choice].life = 0;
        }


        if (hero.life <= 0) {
            return false;
        }

        boolean lifeAllMonstersIsPositive = true;
        for (int i = 0; i < room.monsters.length; i++) {
            if (room.monsters[i].life > 0) {
                lifeAllMonstersIsPositive = false;
                break;
            }
        }
        if (lifeAllMonstersIsPositive) {
            for (int i = 0; i < room.monsters.length; i++) {
                parameterHero(hero, room.monsters[i]);
            }
            return false;
        }
        return true;
    }

    private static void hitMonster(Hero hero, Monster monster, int chance) {
        int chanceToHitHero = random.nextInt();
        if (chanceToHitHero > chance) {
            monster.life = monster.life - (hero.attack + hero.damage);
            System.out.println(" У " + monster.name + " осталось " + monster.life + " жизней ");
        }
    }

    private static void hitHero(Hero hero, Monster monster, int chance) {
        int chanceToHit = random.nextInt(10);
        if (chanceToHit > chance) {

            int damage = monster.damage - hero.armor;
            hero.life = hero.life - damage;
            System.out.println(" Монстр " + monster.name + " бьет героя на " + damage);
            System.out.println(" У героя осталось " + hero.life + " жизней ");
        }
    }
}