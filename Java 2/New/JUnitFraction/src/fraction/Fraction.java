package fraction;

public class Fraction implements Comparable<Fraction> {
    private int num, den;

    public Fraction(int num, int den) {
        int gcd = getGCD(num, den);
        this.num = num / gcd;
        this.den = den / gcd;
        if (num > 0 && den < 0) {
            this.num = -1 * (num / gcd);
            this.den = -1 * (den / gcd);
        }
    }

    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    @Override
    public String toString() {
        if (den < 0) {
            return -num + "/" + -den;
        }
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fraction) {
            Fraction that = (Fraction) obj;
            if (this.num == that.num && this.den == that.den) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Fraction that) {
        if (that != null) {
            if (this.num == that.num && this.den == that.den) {
                return 0;
            }
            if (this.den == that.den) {
                return this.num - that.num;
            }
            return (this.num * that.den) - (this.den * that.num);
        }
        return -1;
    }

    public int getGCD(int num, int den) {
        int num1 = Math.abs(num);
        int num2 = Math.abs(den);
        int remainder = 0;
        while (num2 != 0) {
            remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        if (num < 0 && den < 0) {
            return -1 * num1;
        }
        return num1;
    }

    public Fraction add(Fraction f1) {
        Fraction newFract = new Fraction(((this.num * f1.den) + (this.den * f1.num)), (this.den * f1.den));
        return newFract;
    }
}