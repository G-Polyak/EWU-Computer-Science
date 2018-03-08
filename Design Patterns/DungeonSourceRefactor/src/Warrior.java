

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */


public class Warrior extends Hero {

    private final int BLOW_MODIFIER = 76;

    public Warrior() {

        super("Warrior");


    }//end constructor


    private void crushingBlow(DungeonCharacter opponent) {
        if (Math.random() <= .4) {
            int blowPoints = (int) (Math.random() * BLOW_MODIFIER) + 100;
            System.out.println(getName() + " lands a CRUSHING BLOW for " + blowPoints
                    + " damage!");
            opponent.subtractHitPoints(blowPoints);
        }//end blow succeeded
        else {
            System.out.println(getName() + " failed to land a crushing blow");
            System.out.println();
        }//blow failed

    }//end crushingBlow method

    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " swings a mighty sword at " +
                opponent.getName() + ":");
        super.attack(opponent);
    }//end override of attack method

    @Override
    public void performAbility(DungeonCharacter opponent) {
        crushingBlow(opponent);
    }

    @Override
    public String abilityName() {
        return "Crushing blow";
    }
}//end Hero class