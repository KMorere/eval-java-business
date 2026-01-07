package business;

import daos.CourseDao;
import daos.UserDao;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Business {
    private static Business instance = null;

    public static Business getInstance() {
        if (instance == null)
            instance = new Business();
        return instance;
    }

    private static final ArrayList<Course> courses = new ArrayList<>();

    public void start_init() {
        getCourses();
    }

    /**
     * Add a course to the list.
     * @param _course Course to add.
     */
    public void addCourse(Course _course) { courses.add(_course); }

    //region daos
    public Course getCourse(int _id) {
        return new CourseDao().read(_id);
    }

    public Course[] getCourses() {
        return new CourseDao().readAll();
    }

    public User getUser(int _id) {
        return new UserDao().read(_id);
    }

    public User[] getUsers() {
        return new UserDao().readAll();
    }
    //endregion

    //region display
    /**
     * Display the name, length and description of all courses.
     */
    public List<Course> displayCourses() {
        System.out.println("Displaying all courses");
        System.out.println(chainStringBuilder("-", String.valueOf(courses.size()).length()) +
                " | " + createLine());

        for(int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.printf("%-"+String.valueOf(courses.size()).length()+"s | ", i+1);
            System.out.println(String.format(course.toStringF(getBiggestChar()[0]),
                    course.getName(), course.getLength()));
        }
        System.out.println();

        return courses;
    }

    /**
     * Display all the courses containing the specified keyword.
     * @param _key The keyword used to filter through courses.
     */
    public List<Course> displayCoursesf(String _key) {
        System.out.println("Displaying courses with filter : " + _key);
        System.out.println(chainStringBuilder("-", String.valueOf(courses.size()).length()) +
                " | " + createLine());
        List<Course> newCourses = courses
                .stream()
                .filter(c -> c.getDescription().toLowerCase().contains(_key.toLowerCase()))
                .collect(Collectors.toList());

        for(int i = 0; i < newCourses.size(); i++) {
            Course course = newCourses.get(i);
            System.out.printf("%-"+String.valueOf(newCourses.size()).length()+"s | ", i+1);
            System.out.println(String.format(course.toStringF(getBiggestChar()[0]),
                    course.getName(), course.getLength()));
        }
        System.out.println();

        return newCourses;
    }

    /**
     * Display all the courses matching the key.
     * @param _key THe keyword used to filter through courses.
     */
    public List<Course> displayByType(String _key) {
        System.out.println("Displaying all "+ _key.toLowerCase() +" courses");
        System.out.println(chainStringBuilder("-", String.valueOf(courses.size()).length()) +
                " | " + createLine());
        List<Course> newCourses = courses
                .stream()
                .filter(c -> c.getType().getName().equals(_key))
                .collect(Collectors.toList());

        for(int i = 0; i < newCourses.size(); i++) {
            Course course = newCourses.get(i);
            System.out.printf("%-"+String.valueOf(newCourses.size()).length()+"s | ", i+1);
            System.out.println(String.format(course.toStringF(getBiggestChar()[0]),
                    course.getName(), course.getLength()));
        }
        System.out.println();

        return newCourses;
    }

    /**
     * Create a line for the courses console display.
     */
    private String createLine() {
        return chainStringBuilder("-", getBiggestChar()[0]) +
                " | " +
                chainStringBuilder("-", 5) +
                " | " +
                chainStringBuilder("-", getBiggestChar()[1]);
    }

    /**
     * Repeat '_str' '_size' amount of times.
     * @param _str String to repeat.
     * @param _size Amount of loops.
     * @return The created chain.
     */
    private String chainStringBuilder(String _str, int _size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _size; i++)
            sb.append(_str);
        return sb.toString();
    }
//endregion

    /**
     * Find the longest course name and description.
     * @return Return the length of the longest course name and description.
     */
    private int[] getBiggestChar() {
        int nameSize = 0;
        int descSize = 0;
        for (Course course : courses) {
            if (course.getName().length() > nameSize)
                nameSize = course.getName().length();
            if (course.getDescription().length() > descSize)
                descSize = course.getDescription().length();
            }
        if (descSize > 20)
            descSize = 20;
        return new int[]{nameSize, descSize};
    }
}
