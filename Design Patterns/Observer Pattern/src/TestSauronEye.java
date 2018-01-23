public class TestSauronEye {

    public static void main(String[] args) {

        EyeOfSauron eye = new EyeOfSauron();
        BadGuy saruman = new BadGuy(eye, "Saruman");
        BadGuy witchKing = new BadGuy(eye, "Witch King");
        int numHobbits = 1, numElves = 1, numDwarves = 2, numMen = 0;
        eye.setEnemies(numHobbits, numElves, numDwarves, numMen);
        //message should be displayed from Saruman and Angmar that they know about 1 hobbit, 1 elf, 2 dwarves
        saruman.defeated(); //Saruman is no longer registered with the Eye
        numHobbits = 4;
        numElves = 2;
        numDwarves = 2;
        numMen = 100;
        eye.setEnemies(numHobbits, numElves, numDwarves, numMen);
        //only the Witch King reports on the enemies

    }//end main

}//end class