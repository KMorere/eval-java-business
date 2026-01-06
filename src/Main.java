import models.*;
import business.Business;

public class Main {
    public static void main(String[] args) {
        tests();
    }

    public static void tests() {
        Course testCourse = new Course(
                "Java Python",
                "Training course to learn the basics of Java and Python.",
                20,
                Course.CourseType.REMOTE,
                19.99f
        );
        new Course(
                "Egg",
                "Egg.",
                100,
                Course.CourseType.REMOTE,
                0.01f
        );
        new Course(
                "OIIA OIIA",
                "I like turtles.",
                1,
                Course.CourseType.ONSITE,
                0.00f
        );

        System.out.println(testCourse);
        
        Business business = new Business();
        business.displayCourses();
        business.displayCoursesf("Java");
        business.displayByType("Remote");
        //System.out.println(new CourseDao().read(1));
    }
}