import java.util.Scanner;

public class Dungeon {

    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {

        Hero theHero;
        Monster theMonster;
        do {

            theHero = chooseHero();
            theMonster = generateMonster();
            battle(theHero, theMonster);

        } while (playAgain());

    }

    private static Hero chooseHero() {

        System.out.println("Choose a hero:\n1. Warrior\n2. Sorceress\n3. Thief");
        int choice = kb.nextInt();
        switch (choice) {

            case 1:
                return new Warrior();

            case 2:
                return new Sorceress();

            case 3:
                return new Thief();

            default:
                System.out.println("invalid choice, returning Thief");
                return new Thief();

        }

    }

    private static Monster generateMonster() {

        int choice = (int) (Math.random() * 3) + 1;
        switch (choice) {

            case 1:
                return new Ogre();

            case 2:
                return new Gremlin();

            case 3:
                return new Skeleton();

            default:
                System.out.println("invalid choice, returning Skeleton");
                return new Skeleton();

        }

    }

    private static boolean playAgain() {

        System.out.println("Play again (y/n)?");
        String again = kb.next();
        return (again.toLowerCase().contains("y"));

    }

    private static void battle(Hero theHero, Monster theMonster) {

        char pause = 'p';
        System.out.println(theHero.getName() + " battles " + theMonster.getName());
        System.out.println("---------------------------------------------");
        while (theHero.isAlive() && theMonster.isAlive() && pause != 'q') {

            theHero.battleChoices(theMonster);
            if (theMonster.isAlive())
                theMonster.attack(theHero);

            System.out.print("\n-->q to quit, anything else to continue: ");
            pause = kb.next().charAt(0);

        }
        if (!theMonster.isAlive())
            System.out.println(theHero.getName() + " was victorious!");
        else if (!theHero.isAlive())
            System.out.println(theHero.getName() + " was defeated :-(");
        else//both are alive so user quit the game
            System.out.println("Quitters never win ;-)");

    }

}