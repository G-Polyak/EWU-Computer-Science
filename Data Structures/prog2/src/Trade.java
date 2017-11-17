
public class Trade {

    public int start;
    public int end;
    public int profit;

    public Trade(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    public String toString() {

        return "[" + start + "," + end + "," + profit +"]";

    }

}
