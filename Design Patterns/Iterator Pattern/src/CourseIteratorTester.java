import course.*;

import java.util.Iterator;

public class CourseIteratorTester {

    public static void main(String[] args) {

        Course c1 = new Course("CSCD999");
        c1.addStudent(new Student("name1", 0, 4));
        c1.addStudent(new Student("name2", 1, 4));
        c1.addStudent(new Student("name3", 2, 4));
        c1.addStudent(new Student("name4", 3, 4));
        c1.addStudent(new Student("name5", 4, 4));
        c1.addStudent(new Student("name6", 5, 4));

        for (Student stu : c1) {
            System.out.println(stu.getName() + "\t" + stu.getID() + "\t" + stu.getGPA());
        }
        System.out.println("\n\tGRADE CHANGE\t\n");
        Iterator<Student>  iterator = c1.iterator();
        while (iterator.hasNext()) {

            Student stu = iterator.next();
            stu.setGPA(stu.getGPA() / 1.75); //1.75 is a random number with no special value
            System.out.println(stu.getName() + "\t" + stu.getID() + "\t" + stu.getGPA());

        }

    }

}
