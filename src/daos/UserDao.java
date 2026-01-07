package daos;

import models.Course;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class UserDao extends Dao<User> {
    @Override
    public int create(User obj) {
        int id_user = 0;
        String query = "INSERT INTO user_ (id_user, login, password) " +
                "VALUES (NULL, ?, ?)";
        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getLogin());
            record.setString(2, obj.getPassword());

            id_user = record.executeUpdate();

            if (id_user > 0)
                System.out.println("Insertion at id : " + id_user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id_user;
    }

    @Override
    public User read(int id) {
        User user = null;
        String query = "SELECT * FROM user_ " +
                "WHERE user.id_user = ?";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    user = new User(
                            set.getInt("id_course"),
                            set.getString("login"),
                            set.getString("password")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User[] readAll() {
        ArrayList<User> user = new ArrayList<>();
        String query = "SELECT * FROM user_";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                while (set.next()) {
                    user.add(new User(
                            set.getInt("id_user"),
                            set.getString("login"),
                            set.getString("password")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user.toArray(new User[0]);
    }

    @Override
    public boolean update(User obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
