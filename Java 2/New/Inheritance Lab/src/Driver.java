public class Driver {

    public static void main(String args[]) {

        Programmer George = new Programmer("George", false);
        Programmer Will = new Programmer("Will E. Makit", true);
        Lawyer Ivana = new Lawyer("Ivana Killmen", 11);
        Lawyer Luke = new Lawyer("Luke N. Dimm", 0);
        Lawyer Eileen = new Lawyer("Eileen Dover", 100);
        Accountant Bill = new Accountant("Bill Cheatem", 17.00);
        Accountant Joe = new Accountant("Joe Kisonyou", 45.50);
        Accountant Seymore = new Accountant("Seymore Butts", 2.50);

        Employee[] emps = new Employee[8];

        emps[0] = new Programmer("George Polyak", false);
        emps[1] = new Programmer("Will E. Makit", true);

        emps[2] = new Accountant("Bill Cheatum", 17.0);
        emps[3] = new Accountant("Joe Kisonyou", 45.5);
        emps[4] = new Accountant("Seymore Butts", 2.5);

        emps[5] = new Lawyer("Ivana Killmen", 11);
        emps[6] = new Lawyer("Luke N. Dimm", 0);
        emps[7] = new Lawyer("Eileen Dover", 100);

        for (Employee e : emps) {
            e.reportSalary();
        }

    }

}
