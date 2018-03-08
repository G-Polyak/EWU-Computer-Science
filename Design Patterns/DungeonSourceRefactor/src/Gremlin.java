

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 *
 * @author
 * @version 1.0
 */

public class Gremlin extends Monster {

    public Gremlin() {
        super("Gremlin");

    }//end constructor

    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " jabs his kris at " +
                opponent.getName() + ":");
        super.attack(opponent);

    }//end override of attack


}//end class Gremlin