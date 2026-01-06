import models.*;
import business.Business;

import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    private static boolean connected = false;

    public static void main(String[] args) {
        tests();

        start();
    }

    private static void start() {
        System.out.println("=====[Welcome]=====");
        System.out.println("Select an operation :"+
                "\n\t 0. Leave."+
                "\n\t 1. Log-in."+
                "\n\t 2. Enter as Guest.");

        switch(scan.nextInt()) {
            case 0:
                System.out.println("Leaving...");
                break;
            case 1:
                connected = true;
                throw new RuntimeException("ERROR : FEATURE NOT IMPLEMENTED.");
            case 2:
                System.out.println("Update");
                update();
                break;
            default:
                break;
        }
    }

    /**
     * User operation of the program after connection.
     */
    private static void update() {
        System.out.println("Select an operation :"+
                "\n\t 0. Leave."+
                "\n\t 1. View courses."+
                "\n\t 2. View shopping cart."+
                ((connected)?"":"\n\t 3. Log-in."));

        switch(scan.nextInt()) {
            case 0:
                System.out.println("Leaving...");
                break;
            case 1:
                selectCourse();
                break;
            case 2:
                throw new RuntimeException("ERROR : FEATURE NOT IMPLEMENTED.");
            default:
                break;
        }
    }

    /**
     * Course selection by the user.
     */
    private static void selectCourse() {
        System.out.println("Select an operation :"+
                "\n\t 0. Leave."+
                "\n\t 1. View all."+
                "\n\t 2. View remote."+
                "\n\t 3. View on-site."+
                "\n\t 4. Filter with keyword.");

        switch(scan.nextInt()) {
            case 0:
                System.out.println("Leaving...");
                break;
            case 1:
                new Business().displayCourses();
                break;
            case 2:
                new Business().displayByType("Remote");
                break;
            case 3:
                new Business().displayByType("On-site");
                break;
            case 4:
                System.out.println("Keyword : ");
                String key = scan.next();
                new Business().displayCoursesf(key);
                break;
            default:
                break;
        }
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