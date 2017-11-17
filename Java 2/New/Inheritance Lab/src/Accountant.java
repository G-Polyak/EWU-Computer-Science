public class Accountant extends Employee {

    private static final double SUPPLEMENT = 0.0;
    private double parking;

    public Accountant(String name, double parking) {
        super(name, SUPPLEMENT);
        this.parking = parking;
    }

    @Override
    public void reportSalary() {
        System.out.println("I am an accountant named " + getName() + ". I make " +
                getSalary() + " plus a parking " + "allowance of " + getParking());
    }

    public double getParking() {
        return parking;
    }

    public void setParking(double parking) {
        this.parking = parking;
    }
}
