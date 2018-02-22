package course;

public class Student {

    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        //enforce unique ids here
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

}
