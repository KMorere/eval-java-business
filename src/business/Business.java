package business;

import models.*;

import java.util.ArrayList;

public class Business {
    private static final ArrayList<Course> courses = new ArrayList<>();

    /**
     * Add a course to the list.
     * @param _course Course to add.
     */
    public void AddCourse(Course _course) { courses.add(_course); }

    /**
     * Display the name, length and description of all courses.
     */
    public void DisplayCourses() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < getBiggestChar(); i++)
            line.append("-");
        line.append(" | ");
        for (int i = 0; i < 5; i++)
            line.append("-");
        line.append(" | ");
        for (int i = 0; i < 20; i++)
            line.append("-");
        System.out.println(line);

        for(Course course : courses) {
            System.out.println(String.format(course.toStringF(getBiggestChar()), course.getName(), course.getLength()));
        }
    }

    /**
     * Find the longest course name.
     * @return Return the length of the longest course name.
     */
    public int getBiggestChar() {
        int size = 0;
        for (Course course : courses) {
            if (course.getName().length() > size)
                size = course.getName().length();
            }
        return size;
    }
}
