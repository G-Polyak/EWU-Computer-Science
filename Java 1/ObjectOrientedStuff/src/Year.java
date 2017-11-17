//George Polyak
//Year Class

public class Year {

    private int yearNum;

    public Year() {
        this.yearNum = 1970;
    }

    public Year(int yearNum) {
        this.yearNum = yearNum;
    }

    public void setYearNum(int yearNum) {
        this.yearNum = yearNum;
    }

    public int getYearNum() {
        return this.yearNum;
    }

    @Override
    public String toString() {
        return "" + this.yearNum;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (!(obj instanceof Year)) {
            return false;
        }
        Year that = (Year) obj;
        if (this.yearNum == that.yearNum) {
            return true;
        }
        return false;

    }

}