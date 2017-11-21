//George Polyak
//Date Class

public class Date implements Comparable<Date> {

    private Month m;
    private Day d;
    private Year y;

    public Date() {
        this.m = new Month(1);
        this.d = new Day(1);
        this.y = new Year(1970);
    }

    public Date(int monthNum, int dayNum, int yearNum) {
        this.m = new Month(monthNum);
        this.d = new Day(dayNum);
        this.y = new Year(yearNum);
    }

    public void setDate(int monthNum, int dayNum, int yearNum) {
        this.m = new Month(monthNum);
        this.d = new Day(dayNum);
        this.y = new Year(yearNum);
    }

    public String getDate() {
        return this.m + " " + this.d + " " + this.y;
    }

    @Override
    public String toString() {
        return this.m + " " + this.d + " " + this.y;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        } else if(!(obj instanceof Date)) {
            return false;
        }
        Date that = (Date) obj;
        if(this.m == that.m && this.d == that.d && this.y == that.y) {
            return true;
        }
        return false;

    }

    @Override
    public int compareTo(Date that) {

        if(this.y == that.y) {
            if(this.m == that.m) {
                if(this.d == that.d) {
                    return 0;
                }
                return this.d.getDayNum() - that.d.getDayNum();
            }
            return this.m.getMonthNum() - that.m.getMonthNum();
        }
        return this.y.getYearNum() - that.y.getYearNum();

    }

    public static boolean isValid(int monthNum, int dayNum, int yearNum) {

        if(yearNum < 0) {
            return false;
        }
        if(monthNum < 0 || monthNum > 12) {
            return false;
        }
        switch (monthNum) {
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                return dayNum > 0 && dayNum < 31;
            case 2:
                return dayNum > 0 && dayNum < 28;
            case 4:case 6:case 9:case 11:
                return dayNum > 0 && dayNum < 30;
        }
        return false;

    }

}