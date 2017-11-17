public abstract class Employee {

    private final static double BASE_SALARY = 40000.0;
    private String name;
    private double supplement;

    public Employee(String name, double supplement)
    {
        this.name = name;
        this.supplement = supplement;
    }

    public abstract void reportSalary();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.supplement + BASE_SALARY;
    }

}
