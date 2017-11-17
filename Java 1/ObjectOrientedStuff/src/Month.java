//George Polyak
//Month Class

public class Month {

    private int monthNum;

    public Month() {
        this.monthNum = 1;
    }

    public Month(int monthNum) {
        this.monthNum = monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public int getMonthNum() {
        return this.monthNum;
    }

    @Override
    public String toString() {

        switch (this.monthNum) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "";

    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        } else if(!(obj instanceof Month)) {
            return false;
        }
        Month that = (Month) obj;
        if(this.monthNum == that.monthNum) {
            return true;
        }
        return false;

    }

}