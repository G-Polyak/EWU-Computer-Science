package sample;

import java.io.Serializable;

public class TextWithRGB implements Serializable{

    public String text;
    public double r;
    public double g;
    public double b;

    public TextWithRGB(String text, double r, double g, double b) {

        this.text = text;
        this.r = r;
        this.g = g;
        this.b = b;

    }

    @Override
    public String toString() {
        return text + "\nRed: " + r + "\nGreen: " + g + "\nBlue: " + b;
    }

}
