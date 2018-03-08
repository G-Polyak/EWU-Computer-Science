

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */


public abstract class Monster extends DungeonCharacter {
    private double chanceToHeal;
    private int minHeal, maxHeal;

    //-----------------------------------------------------------------
    public Monster(String character) {
        super(character);
        this.chanceToHeal = CharacterSheet.getChanceToHeal(character);
        this.maxHeal = CharacterSheet.getMaxHeal(character);
        this.minHeal = CharacterSheet.getMinHeal(character);

    }//end monster construcotr

    //-----------------------------------------------------------------
    private void heal() {
        boolean canHeal;
        int healPoints;

        canHeal = (Math.random() <= chanceToHeal) && (getHitPoints() > 0);

        if (canHeal) {
            healPoints = (int) (Math.random() * (maxHeal - minHeal + 1)) + minHeal;
            addHitPoints(healPoints);
            System.out.println(getName() + " healed itself for " + healPoints + " points.\n"
                    + "Total hit points remaining are: " + getHitPoints());
            System.out.println();
        }//end can heal


    }//end heal method

    //-----------------------------------------------------------------
    public void subtractHitPoints(int hitPoints) {
        super.subtractHitPoints(hitPoints);
        heal();

    }//end method


}//end Monster class