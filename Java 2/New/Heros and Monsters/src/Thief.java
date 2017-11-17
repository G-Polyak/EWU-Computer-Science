import java.util.Random;

public class Thief extends Hero {

    public Thief() {
        super("Thief", 75, 6, 20, 40, .8, .4);
    }

    @Override
    public void specSkill(DungeonCharacter c) {

        Random randGen = new Random();
        if (randGen.nextDouble() <= .4) {

            if (randGen.nextDouble() <= .2) {
                Interface.println(c.getName() + " saw you and avoided your attack!");
            } else {

                Interface.println("Surprise attack succeeded!");
                Interface.println(this.name + " gets an additional turn");
                this.remainingTurns += 1;
                attack(c);

            }

        } else {
            attack(c);
        }

    }

    public void battleChoices(DungeonCharacter c) {

        super.battleChoices(c);
        do {

            Interface.println("");
            Interface.println("1. Attack Opponent");
            Interface.println("2. Attempt Surprise Attack");
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
                        Interface.println("You attempt to land a Surprise Attack on " + c.getName());
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