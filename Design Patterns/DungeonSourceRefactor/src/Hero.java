import java.util.Scanner;

/**
 * Title: Hero.java
 * <p>
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 * from DungeonCharacter.  A Hero has battle choices: regular attack and a
 * special skill which is defined by the classes derived from Hero.
 * <p>
 * class variables (all are directly accessible from derived classes):
 * chanceToBlock -- a hero has a chance to block an opponents attack
 * numTurns -- if a hero is faster than opponent, their is a possibility
 * for more than one attack per round of battle
 * <p>
 * class methods (all are public):
 * public Hero(String name, int hitPoints, int attackSpeed,
 * double chanceToHit, int damageMin, int damageMax,
 * double chanceToBlock)
 * public void readName()
 * public boolean defend()
 * public void subtractHitPoints(int hitPoints)
 * public void battleChoices(DungeonCharacter opponent)
 * <p>
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */


public abstract class Hero extends DungeonCharacter implements CharacterAbility{
    private double chanceToBlock;
    private int numTurns;

    //-----------------------------------------------------------------
//calls base constructor and gets name of hero from user
    public Hero(String character) {
        super(character);
        this.chanceToBlock = CharacterSheet.getChanceToBlock(character);
        readName();
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    /*-------------------------------------------------------
            readName obtains a name for the hero from the user

            Receives: nothing
            Returns: nothing

            This method calls: nothing
            This method is called by: hero constructor
            ---------------------------------------------------------*/
    public void readName() {
        System.out.print("Enter character name: ");
        setName(Dungeon.kb.next());
    }//end readName method

    /*-------------------------------------------------------
    defend determines if hero blocks attack

    Receives: nothing
    Returns: true if attack is blocked, false otherwise

    This method calls: Math.random()
    This method is called by: subtractHitPoints()
    ---------------------------------------------------------*/
    public boolean defend() {
        return Math.random() <= chanceToBlock;

    }//end defend method

    /*-------------------------------------------------------
    subtractHitPoints checks to see if hero blocked attack, if so a message
    is displayed, otherwise base version of this method is invoked to
    perform the subtraction operation.  This method overrides the method
    inherited from DungeonCharacter promoting polymorphic behavior

    Receives: hit points to subtract
    Returns: nothing

    This method calls: defend() or base version of method
    This method is called by: attack() from base class
    ---------------------------------------------------------*/
    public void subtractHitPoints(int hitPoints) {
        if (defend()) {
            System.out.println(getName() + " BLOCKED the attack!");
        } else {
            super.subtractHitPoints(hitPoints);
        }


    }//end method

    /*-------------------------------------------------------
    battleChoices will be overridden in derived classes.  It computes the
    number of turns a hero will get per round based on the opponent that is
    being fought.  The number of turns is reported to the user.  This stuff might
    go better in another method that is invoked from this one...

    Receives: opponent
    Returns: nothing

    This method calls: getAttackSpeed()
    This method is called by: external sources
    ---------------------------------------------------------*/
    public void battleChoices(DungeonCharacter opponent) {
        numTurns = getAttackSpeed() / opponent.getAttackSpeed();

        if (numTurns == 0)
            numTurns++;

        System.out.println("Number of turns this round is: " + numTurns);
        int choice;
        do {
            System.out.println("1. Attack Opponent");
            System.out.println("2. " + abilityName());
            System.out.print("Choose an option: ");
            choice = Dungeon.kb.nextInt();

            switch (choice) {
                case 1:
                    attack(opponent);
                    break;
                case 2:
                    performAbility(opponent);
                    break;
                default:
                    System.out.println("invalid choice!");
            }//end switch

            setNumTurns(getNumTurns() - 1);
            if (getNumTurns() > 0)
                System.out.println("Number of turns remaining is: " + getNumTurns());

        } while (getNumTurns() > 0 && getHitPoints() > 0 && opponent.getHitPoints() > 0);

    }//end battleChoices

}//end Hero class