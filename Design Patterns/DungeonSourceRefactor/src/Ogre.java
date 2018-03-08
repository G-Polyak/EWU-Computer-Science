

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */

public class Ogre extends Monster {

    public Ogre() {
        super("Ogre");


    }//end constructor

    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " slowly swings a club toward's " +
                opponent.getName() + ":");
        super.attack(opponent);

    }//end override of attack


}//end Monster class