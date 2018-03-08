public class CharacterSheet {

    public static String getName(String character) {

        switch (character) {

            case "Gremlin":
                return "Gnarltooth the Gremlin";
            case "Ogre":
                return "Oscar the Ogre";
            case "Skeleton":
                return "Sargath the Skeleton";
            case "Sorceress":
                return character;
            case "Thief":
                return character;
            case "Warrior":
                return character;

        }
        return "";

    }

    public static int getHP(String character) {

        switch (character) {

            case "Gremlin":
                return 70;
            case "Ogre":
                return 200;
            case "Skeleton":
                return 100;
            case "Sorceress":
                return 75;
            case "Thief":
                return 75;
            case "Warrior":
                return 125;

        }
        return -1;

    }

    public static int getAttackSpeed(String character) {

        switch (character) {

            case "Gremlin":
                return 5;
            case "Ogre":
                return 2;
            case "Skeleton":
                return 3;
            case "Sorceress":
                return 5;
            case "Thief":
                return 6;
            case "Warrior":
                return 4;

        }
        return -1;

    }

    public static double getChanceToHit(String character) {

        switch (character) {

            case "Gremlin":
                return 0.8;
            case "Ogre":
                return 0.6;
            case "Skeleton":
                return 0.8;
            case "Sorceress":
                return 0.7;
            case "Thief":
                return 0.8;
            case "Warrior":
                return 0.8;

        }
        return -1.0;

    }

    public static double getChanceToHeal(String character) {

        switch (character) {

            case "Gremlin":
                return 0.4;
            case "Ogre":
                return 0.1;
            case "Skeleton":
                return 0.3;

        }
        return -1.0;

    }

    public static int getDamageMin(String character) {

        switch (character) {

            case "Gremlin":
                return 15;
            case "Ogre":
                return 30;
            case "Skeleton":
                return 30;
            case "Sorceress":
                return 25;
            case "Thief":
                return 20;
            case "Warrior":
                return 35;

        }
        return -1;

    }

    public static int getDamageMax(String character) {

        switch (character) {

            case "Gremlin":
                return 30;
            case "Ogre":
                return 50;
            case "Skeleton":
                return 50;
            case "Sorceress":
                return 50;
            case "Thief":
                return 40;
            case "Warrior":
                return 60;

        }
        return -1;

    }

    public static int getMinHeal(String character) {

        switch (character) {

            case "Gremlin":
                return 20;
            case "Ogre":
                return 30;
            case "Skeleton":
                return 30;

        }
        return -1;

    }

    public static int getMaxHeal(String character) {

        switch (character) {

            case "Gremlin":
                return 40;
            case "Ogre":
                return 50;
            case "Skeleton":
                return 50;

        }
        return -1;

    }

    public static double getChanceToBlock(String character) {

        switch (character) {

            case "Sorceress":
                return 0.3;
            case "Thief":
                return 0.5;
            case "Warrior":
                return 0.2;

        }
        return -0.1;

    }

}
