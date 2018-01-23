import java.util.Observable;
import java.util.Observer;

public class BadGuy implements Observer {

    private EyeOfSauron eye;
    private String name;

    public BadGuy(EyeOfSauron eye, String name) {

        this.eye = eye;
        this.name = name;
        eye.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof EyeOfSauron) {
            eye = (EyeOfSauron) o;
        }
        if (arg instanceof EyeOfSauron) {
            eye = (EyeOfSauron) arg;
        }
        System.out.println(name + " knows about " + eye.getNumHobbits() + " hobbits, " + eye.getNumElves() +
                " elves, " + eye.getNumDwarves() + " dwarves, and " + eye.getNumMen() + " men.");

    }

    public void defeated() {

        eye.deleteObserver(this);
        System.out.println(name + " has been defeated.");

    }

}
