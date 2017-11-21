import java.util.Random;

public class Warrior extends Hero {


    public Warrior() {
        super("Warrior", 125, 4, 35, 60, .8, .2);
    }

    @Override
    public void specSkill(DungeonCharacter c) {

        Random randGen = new Random();
        boolean canAttack = randGen.nextDouble() <= .4;

        if (canAttack) {

            int dmg = HeroesVersusMonsters.randInt(75, 175);
            c.setHp(c.getHp() - dmg);
            Interface.println(getName() + "'s Crushing Blow on " + c.getName() + " succeeded!");
            Interface.println(c.getName() + " was hit for " + dmg + " points of damage!");
            if (c.getHp() > 0) {
                Interface.println(c.getName() + " now has " + c.getHp() + " HP");
            }

        } else {
            Interface.println(getName() + "'s Crushing Blow on " + c.getName() + " failed!");
        }

        if (c.getHp() <= 0) {
            c.setHp(0);
            Interface.println(c.getName() + " has been killed!");
        }

    }

    public void battleChoices(DungeonCharacter c) {

        super.battleChoices(c);
        do {

            Interface.println("");
            Interface.println("1. Attack Opponent");
            Interface.println("2. Attempt Crushing Blow");
            Interface.print("Choose an option: ");
            int choice;
            do {

                choice = Interface.readInt();
                switch (choice) {

                    case 1:
                        Interface.println("You attack " + c.getName());
                        attack(c);
                        break;

                    case 2:
                        Interface.println("You attempt to land a Crushing Blow on " + c.getName());
                        specSkill(c);
                        break;

                    default:
                        Interface.print("Invalid Choice. Try again: ");

                }

            } while ((choice != 1) && (choice != 2));

            this.remainingTurns -= 1;
            if (this.remainingTurns > 0) {
                Interface.println(this.remainingTurns + " turns remaining");
            }

        } while (this.remainingTurns > 0);

    }

}