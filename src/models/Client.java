package models;

import business.Business;
import daos.CourseDao;

public class Client {
    private int id_client;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    public Client(int _id, String _first_name, String _last_name, String _email, String _phone) {
        this.id_client = _id;
        this.setFirstName(_first_name);
        this.setLastName(_last_name);
        this.setMail(_email);
        this.setPhone(_phone);
    }

    /**
     * Create a course and add it to the database.
     */
    public void createCourse(Course _course) {
        Business.getInstance().addCourse(_course);
        new CourseDao().create(_course);
    }

    public void deleteCourse() {
        // TODO: Implement method.
    }

    @Override
    public String toString() {
        return this.getName() +
                "\n\t" + this.getMail() +
                "\n\t" + this.getPhone();
    }

    //region getters and setters
    public String getName() { return this.first_name + " " + this.last_name; }

    public String getFirstName() { return this.first_name; }
    public void setFirstName(String _name) { this.first_name = _name; }

    public String getLastName() { return this.last_name; }
    public void setLastName(String _name) { this.last_name = _name; }

    public String getMail() { return this.email; }
    public void setMail(String _mail) { this.email = _mail; }

    public String getPhone() { return this.phone; }
    public void setPhone(String _phone) { this.phone = _phone; }
    //endregion
}
