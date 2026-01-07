import models.*;
import business.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    private static boolean connected = false;
    private static final String VIEW = "view";
    private static final String ADD = "add";

    private static Cart cart = new Cart();
    private static Course selectedCourse = null;
    private static User currentUser;

    public static void main(String[] args) {
        //tests();
        Business.getInstance().start_init();

        start();
    }

    private static void start() {
        System.out.println("=====[Welcome]=====");
        System.out.println("Select an operation :"+
                "\n\t 0. Leave."+
                "\n\t 1. Log-in."+
                "\n\t 2. Enter as Guest.");

        switch(scan.nextInt()) {
            case 0: // Leave.
                System.out.println("Leaving...");
                break;
            case 1: // Log-in.
                startLogin();
                break;
            case 2: // Guest mode.
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
            case 0: // Leave.
                System.out.println("Leaving...");
                break;
            case 1: // View Course.
                selectCourse();
                break;
            case 2: // View Cart.
                cart.displayContent();
                if (!cart.getBag().isEmpty()) {
                    System.out.println("Start order ?" +
                            "\n\t 0. Return." +
                            "\n\t 1. Yes.");
                    if (scan.hasNextInt() && scan.nextInt() == 1) {
                        // Todo: Start order.
                        if (!connected) {
                            System.out.println("You must log-in before starting an order.");
                            startLogin();
                        }
                    }
                }
                break;
            case 3: // Log-in.
                if (!connected) startLogin();
                break;
            default:
                break;
        }
    }

    /**
     * Course selection by the user.
     */
    private static void selectCourse() {
        System.out.println("Select an operation :"+
                "\n\t 0. Return."+
                "\n\t 1. View all."+
                "\n\t 2. View remote."+
                "\n\t 3. View on-site."+
                "\n\t 4. Filter with keyword.");

        String input = "";
        List<Course> newCourses = new ArrayList<>();
        update: while (!input.equals("0")) {
            // Check the user's input to see which operation they're picking.
            if (scan.hasNextInt()) {
                switch (scan.nextInt()) {
                    case 0: // Leave.
                        System.out.println("Returned.");
                        break update;
                    case 1: // View all.
                        newCourses = Business.getInstance().displayCourses();
                        break;
                    case 2: // View remote.
                        newCourses = Business.getInstance().displayByType("Remote");
                        break;
                    case 3: // View on-site.
                        newCourses = Business.getInstance().displayByType("On-site");
                        break;
                    case 4: // View with filter.
                        System.out.println("Keyword : ");
                        String key = scan.next();
                        newCourses = new Business().displayCoursesf(key);
                        break;
                    default:
                        break;
                }
            }
            else {
                // View all information of a course.
                input = scan.nextLine();
                // Check if the user wants to view and add.
                viewCourse(input, newCourses);
                addCourse(input, selectedCourse);
            }
        }

        update();
    }

    /**
     * View all information of a selected course.
     */
    private static void viewCourse(String _input, List<Course> _courses) {
        if (_input.regionMatches(true, 0, VIEW, 0, VIEW.length())) {
            // Filter the input to see which course they want.
            int inp = Integer.parseInt(_input.chars()           // Checking all characters.
                    .filter(Character::isDigit)                 // Filtering out all non-digits.
                    .mapToObj(c -> String.valueOf((char)c)) // Converting each character to a String.
                    .collect(Collectors.joining()));            // Joining characters in a single String.

            if (inp > 0 && inp < _courses.size()) {
                System.out.println(_courses.get(inp - 1));
                selectedCourse = _courses.get(inp - 1);
            }
        }
    }

    /**
     * Add a course to the cart.
     */
    private static void addCourse(String _input, Course _course) {
        if (_input.regionMatches(true, 0, ADD, 0, ADD.length())) {
            // Adding the course if one is selected.
            if (selectedCourse != null) {
                System.out.println("Added "+_course);
                cart.addCourse(_course);
            }
        }
    }

    /**
     * Handle login and account creation.
     */
    private static void startLogin() {
        if(!connected) {
            String login = "";
            String password = "";

            System.out.println("Input your login :");
            login = scan.next();

            System.out.println("Input your password :");
            password = scan.next();

            User tempUser = new User(login, password);

            if (Business.getInstance().checkUser(tempUser)) {
                System.out.println("Login successful");
                connected = true;
                currentUser = tempUser;
            } else {
                System.out.println("Login un-successful, create account ?" +
                        "\n\t 0. Yes." +
                        "\n\t 1. No.");
                if (scan.hasNextInt() && scan.nextInt() == 0) {
                    Business.getInstance().createUser(tempUser);
                    currentUser = tempUser;
                }
            }
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