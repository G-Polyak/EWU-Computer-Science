//George Polyak
//CSCD 211-02

//No Extra Credit Attempted

import java.util.Random;

public class HeroesVersusMonsters {

    public static void main(String args[]) {

        do {

            Hero player = chooseHero();
            Monster NPC = getMonster();
            Interface.println(player.getName() + " battles " + NPC.getName());
            battle(player, NPC);

        } while (playAgain());

    }

    public static Hero chooseHero() {

        Interface.println("1. Warrior");
        Interface.println("2. Sorceress");
        Interface.println("3. Thief");
        Interface.print("Choose a hero: ");

        int choice;
        do {

            choice = Interface.readInt();
            if (choice < 1 || choice > 3) {
                Interface.print("Invalid Choice. Try again: ");
            }

        } while (choice < 1 || choice > 3);

        switch (choice) {

            case 1:
                return new Warrior();
            case 2:
                return new Sorceress();
            case 3:
                return new Thief();

        }
        return null;

    }

    public static Monster getMonster() {

        switch (randInt(1, 3)) {

            case 1:
                return new Ogre();
            case 2:
                return new Gremlin();
            case 3:
                return new Skeleton();

        }
        return null;

    }

    public static void battle(Hero player, Monster NPC) {

        char quit = 'a';
        Interface.println("");

        do {

            player.battleChoices(NPC);
            if (NPC.getHp() > 0) {
                NPC.battleChoices(player);
            }

            if (NPC.getHp() <= 0) Interface.println(player.getName() + " stands victorious");
            else if (player.getHp() <= 0) Interface.println(player.getName() + " was defeated");

        } while ((player.getHp() > 0) && (NPC.getHp() > 0));

    }

    public static boolean playAgain() {

        do {

            Interface.print("Play again (y/n)? ");
            String again = Interface.readString();
            if (again.toLowerCase().startsWith("y")) return true;
            if (again.toLowerCase().startsWith("n")) return false;
            Interface.println("Invalid choice");

        } while (true);

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;

    }

}