public class Programmer extends Employee {

    private static final double SUPPLEMENT = 20000.0;
    private boolean busPass;

    public Programmer(String name, boolean busPass) {
        super(name, SUPPLEMENT);
        this.busPass = busPass;
    }

    @Override
    public void reportSalary() {
        System.out.println("I am a programmer named " + getName() + ". I make " +
                getSalary() + " and I" + ((getBusPass())?
                " get a bus pass.":" do not get a bus pass."));
    }

    public boolean getBusPass() {
        return busPass;
    }

    public void setBusPass(boolean busPass) {
        this.busPass = busPass;
    }
}
