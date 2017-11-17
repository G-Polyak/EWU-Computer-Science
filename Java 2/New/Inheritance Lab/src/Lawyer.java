public class Lawyer extends Employee {

    private static final double SUPPLEMENT = 30000.0;
    private int options;

    public Lawyer(String name, int options) {
        super(name, SUPPLEMENT);
        this.options = options;
    }

    @Override
    public void reportSalary() {
        System.out.println("I am a lawyer named " + getName() + ". I get " +
                getSalary() + " and I have " + getOptions() + " shares of stock.");
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
    }
}
