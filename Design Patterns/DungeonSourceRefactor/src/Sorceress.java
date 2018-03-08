

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */


public class Sorceress extends Hero {
    private final int MIN_ADD = 25;
    private final int MAX_ADD = 50;

    //-----------------------------------------------------------------
    public Sorceress() {
        super("Sorceress");


    }//end constructor

//-------------------------------------------------------------

    private void increaseHitPoints() {
        int hPoints;

        hPoints = (int) (Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
        addHitPoints(hPoints);
        System.out.println(getName() + " added [" + hPoints + "] points.\n"
                + "Total hit points remaining are: "
                + getHitPoints());
        System.out.println();

    }//end increaseHitPoints method

    //-----------------------------------------------------------------
    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " casts a spell of fireball at " +
                opponent.getName() + ":");
        super.attack(opponent);
    }//end override of attack method

    //-----------------------------------------------------------------

    @Override
    public void performAbility(DungeonCharacter opponent) { //nothing happens to this parm with sorceress only
        increaseHitPoints();
    }

    @Override
    public String abilityName() {
        return "Increase hit points";
    }
}//end class