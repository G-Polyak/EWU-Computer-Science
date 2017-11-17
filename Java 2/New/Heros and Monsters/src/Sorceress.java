public class Sorceress extends Hero {


    public Sorceress() {
        super("Sorceress", 75, 5, 25, 45, .7, .3);
    }

    @Override
    public void specSkill(DungeonCharacter c) {

        int hpHealed = HeroesVersusMonsters.randInt(30, 50);
        Interface.println(this.name + " has regained " + hpHealed + " HP");
        this.setHp(this.getHp() + hpHealed);
        Interface.println(this.name + " now has " + this.getHp() + " HP");

    }

    public void battleChoices(DungeonCharacter c) {

        super.battleChoices(c);
        do {

            Interface.println("");
            Interface.println("1. Attack Opponent");
            Interface.println("2. Cast a Healing Spell");
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
                        Interface.println("You cast a Healing Spell on yourself");
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