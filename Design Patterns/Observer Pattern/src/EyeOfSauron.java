import java.util.Observable;

public class EyeOfSauron extends Observable {

    private int numHobbits;
    private int numElves;
    private int numDwarves;
    private int numMen;

    public void setEnemies(int numHobbits, int numElves, int numDwarves, int numMen) {

        this.numHobbits = numHobbits;
        this.numElves = numElves;
        this.numDwarves = numDwarves;
        this.numMen = numMen;
        setChanged();
        notifyObservers(this);

    }

    public int getNumHobbits() {
        return numHobbits;
    }

    public int getNumElves() {
        return numElves;
    }

    public int getNumDwarves() {
        return numDwarves;
    }

    public int getNumMen() {
        return numMen;
    }

}
