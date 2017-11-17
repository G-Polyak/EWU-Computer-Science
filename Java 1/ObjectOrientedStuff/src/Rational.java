//George Polyak
//Rational Object Class

import java.util.*;

public class Rational implements Comparable<Rational>
{

    private int num, den;

    public Rational(int num, int den)
    {
        this.num = num;
        this.den = den;
        int gcd = getGCD();
        this.num = num / gcd;
        this.den = den / gcd;
    }

    public Rational()
    {
        this.num = 1;
        this.den = 1;
    }

    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDen(int den) {
        this.den = den;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Rational)
        {
            Rational that = (Rational)obj;
            if(this.num == that.num && this.den == that.den)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Rational that)
    {
        if(that != null)
        {
            if(this.num == that.num && this.den == that.den)
            {
                return 0;
            }
            if(this.den == that.den)
            {
                return this.num - that.num;
            }
            return (this.num * that.den) - (this.den * that.num);
        }
        return -1;
    }

    public int getGCD()
    {
        int num1 = Math.abs(num);
        int num2 = Math.abs(den);
        int remainder = 0;
        while(num2 != 0)
        {
            remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        if(num < 0 && den >= 0)
        {
            return -1 * num1;
        }
        if(den < 0 && num >= 0)
        {
            return -1 * num1;
        }
        return num1;
    }

    public static Rational add(Rational r1, Rational r2)
    {
        Rational newRat = new Rational(((r1.num * r2.den) + (r1.den * r2.num)), (r1.den * r2.den));
        return newRat;

    }

    public static Rational sub(Rational r1, Rational r2)
    {
        Rational newRat = new Rational(((r1.num * r2.den) - (r1.den * r2.num)), (r1.den * r2.den));
        return newRat;
    }

}