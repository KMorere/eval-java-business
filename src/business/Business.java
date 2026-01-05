package business;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Business {
    private static final ArrayList<Course> courses = new ArrayList<>();

    /**
     * Add a course to the list.
     * @param _course Course to add.
     */
    public void addCourse(Course _course) { courses.add(_course); }

    /**
     * Display the name, length and description of all courses.
     */
    public void displayCourses() {
        System.out.println(createLine());

        for(Course course : courses) {
            System.out.println(String.format(course.toStringF(getBiggestChar()), course.getName(), course.getLength()));
        }
        System.out.println();
    }

    /**
     * Display all the courses containing the specified keyword.
     * @param key The keyword used to filter through courses.
     */
    public void displayCoursesf(String key) {
        System.out.println(createLine());
        List<Course> newCourses = courses
                .stream()
                .filter(c -> c.getDescription().contains(key))
                .collect(Collectors.toList());

        for(Course course : newCourses) {
            System.out.println(String.format(course.toStringF(getBiggestChar()), course.getName(), course.getLength()));
        }
        System.out.println();
    }

    public String createLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < getBiggestChar(); i++)
            line.append("-");
        line.append(" | ");
        for (int i = 0; i < 5; i++)
            line.append("-");
        line.append(" | ");
        for (int i = 0; i < 20; i++)
            line.append("-");
        return line.toString();
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
