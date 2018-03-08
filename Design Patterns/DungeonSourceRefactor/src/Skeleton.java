

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */

public class Skeleton extends Monster {

    public Skeleton() {
        super("Skeleton");

    }//end constructor

    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " slices his rusty blade at " +
                opponent.getName() + ":");
        super.attack(opponent);

    }//end override of attack


}//end class Skeleton