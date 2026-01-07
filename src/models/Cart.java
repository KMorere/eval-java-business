package models;

import business.Business;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Course> bag = new ArrayList<>();
    private float total;

    public List<Course> getBag() { return this.bag; }
    public float getTotal() { return this.total; }
    public void setTotal(float _total) { this.total = _total; }

    /**
     * Add a course to the bag.
     * @param _course Course to add.
     * @return Return the full bag.
     */
    public List<Course> addCourse(Course _course) {
        if (this.getBag().contains(_course)) {
            System.out.println("The cart already contains this element.");
            return this.getBag();
        }
        this.bag.add(_course);
        return this.getBag();
    }

    /**
     * Remove a course from the bag.
     * @param _index Index of the course to remove from the list.
     * @return Return the full bag.
     */
    public List<Course> removeCourse(int _index) {
        this.bag.remove(_index);
        return this.getBag();
    }

    /**
     * Validate the content of the cart and proceed to payment if connected.
     */
    public void startOrder() {
        // TODO: Create an order on the database if connected.
    }

    /**
     * Display the content of the bag.
     */
    public void displayContent() {
        if (!getBag().isEmpty()) {
            System.out.println("Displaying content :");
            Business.getInstance().displayCourses(this.getBag());
        } else {
            System.out.println("Empty cart");
            System.out.println("Ain't nobody here but us chickens...");
        }
    }
}
