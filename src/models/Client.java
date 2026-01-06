package models;

import business.Business;

public class Client {
    private int id_client;

    /**
     * Create a course and add it to the database.
     */
    public void createCourse(Course _course) {
        new Business().addCourse(_course);
        // TODO: Add the course to the database.
    }

    public void deleteCourse() {
        // TODO: Implement method.
    }
}
