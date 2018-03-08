

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */

public class Thief extends Hero {

    private final double MIN_CHANCE = 0.4;
    private final double MAX_CHANCE = 0.9;

    public Thief() {
        super("Thief");


    }//end constructor

    private void surpriseAttack(DungeonCharacter opponent) {
        double surprise = Math.random();
        if (surprise <= MIN_CHANCE) {
            System.out.println("Surprise attack was successful!\n" +
                    getName() + " gets an additional turn.");
            setNumTurns(getNumTurns() + 1);
            attack(opponent);
        }//end surprise
        else if (surprise >= MAX_CHANCE) {
            System.out.println("Uh oh! " + opponent.getName() + " saw you and" +
                    " blocked your attack!");
        } else
            attack(opponent);


    }//end surpriseAttack method

    @Override
    public void performAbility(DungeonCharacter opponent) {
        surpriseAttack(opponent);
    }

    @Override
    public String abilityName() {
        return "Surprise attack";
    }
}