package com.company;

public class Configuration {

    public  Hero preparedHero(){
        Hero hero = new Hero();
        hero.life = 500;
        hero.damage = 10;
        hero.armor = 1;
        hero.attack = 1;
        hero.experience = 0;
        hero.level = 1;
        hero.movedanj = 0;
        hero.experienceNeedForLvUp = 100;
        return hero;
    }

    public  Room[] preparedWorld() {
        Monster monster = new Monster(20, 2, 100, "Skeleton");
        Monster monster1 = new Monster(30, 3, 150, "Skeleton Mage");
        Monster monster2 = new Monster(25, 2, 125, "Dog");
        Monster monster3 = new Monster(35, 3, 135, "Loshadka");
        Monster monster4 = new Monster(40, 4, 125, "Ogr");
        Monster monster5 = new Monster(50, 5, 300, "Mega Ogr");
        Monster monster6 = new Monster(85, 7, 200, "Monkey");
        Monster monster7 = new Monster(100, 8, 300, "Monkey King");
        Monster monster8 = new Monster(40, 5, 100, "Left Hand");
        Monster monster9 = new Monster(40, 5, 100, "Right Hand");
        Monster monster10 = new Monster(100, 6, 150, "Red Draconian");
        Monster monster11 = new Monster(110, 7, 170, "Black Draconian");
        Monster monster12 = new Monster(125, 8, 200, "Blue Draconian");
        Monster monster13 = new Monster(40, 5, 75, "Beetles");
        Monster monster14 = new Monster(50, 6, 75, "Spiders");
        Monster monster15 = new Monster(250, 15, 300, "Dragon");
        Monster monster16 = new Monster(500, 20, 1500, "Titan");

        Monster[] firstRoom = {monster, monster1};
        Monster[] secondRoom = {monster2, monster3};
        Monster[] thirdRoom = {monster4, monster5};
        Monster[] fourthRoom = {monster6, monster7};
        Monster[] fifthRoom = {monster8, monster9};
        Monster[] sixthRoom = {monster10, monster11, monster12};
        Monster[] seventhRoom = {monster13, monster14};
        Monster[] eighthRoom = {monster15};
        Monster[] ninthRoom = {monster16};

        Room room = new Room(1, sixthRoom);
        Room room1 = new Room(2, secondRoom);
        Room room2 = new Room(3, thirdRoom);
        Room room3 = new Room(4, fourthRoom);
        Room room4 = new Room(5, fifthRoom);
        Room room5 = new Room(6, firstRoom);
        Room room6 = new Room(7, seventhRoom);
        Room room7 = new Room(8, eighthRoom);
        Room room8 = new Room(9, ninthRoom);

        Room[] map = {room, room1, room2, room3, room4, room5, room6, room7, room8};
        return map;
    }

}
