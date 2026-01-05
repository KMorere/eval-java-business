package daos;

import models.Course;
import java.sql.*;
import java.util.Map;

public class CourseDao extends Dao<Course> {

    @Override
    public int create(Course obj) {
        int id_course = 0;
        String query = "INSERT INTO course (id_course, name, description, length, type, price, id_client) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getName());
            record.setString(2, obj.getDescription());
            record.setInt(3, obj.getLength());
            record.setString(4, obj.getType());
            record.setFloat(5, obj.getPrice());
            record.setInt(6, obj.getClient());

            id_course = record.executeUpdate();

            if (id_course > 0)
                System.out.println("Insertion at id : " + id_course);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id_course;
    }

    @Override
    public Course read(int id) {
        Course course = null;
        String query = "SELECT * FROM course " +
                "LEFT JOIN client " +
                "ON course.id_client = client.id_client " +
                "WHERE course.id_course = ?";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    course = new Course(
                            set.getInt("id_course"),
                            set.getString("name"),
                            set.getString("description"),
                            set.getInt("length"),
                            set.getString("type"),
                            set.getFloat("price"),
                            set.getInt("id_client")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public Course[] readAll() {
        return new Course[0];
    }

    @Override
    public boolean update(Course obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
