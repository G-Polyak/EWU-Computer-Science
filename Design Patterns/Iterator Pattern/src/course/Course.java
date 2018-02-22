package course;

import java.util.ArrayList;
import java.util.Iterator;

public class Course implements Iterable<Student> {

    private ArrayList<Student> students;
    private String courseName;
    private int totalStudents;

    public Course(String courseName) {

        students = new ArrayList<Student>();
        this.courseName = courseName;
        totalStudents = 0;

    }

    public void addStudent(Student stu) {
        students.add(stu);
        totalStudents++;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public Iterator<Student> iterator() {
        return new CourseIterator();
    }

    private class CourseIterator implements Iterator<Student> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < totalStudents;
        }

        @Override
        public Student next() {
            if (hasNext()) {
                return students.get(currentIndex++);
            }
            throw new IllegalStateException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
