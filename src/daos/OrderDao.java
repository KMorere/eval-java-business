package daos;

import models.Client;
import models.Order;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao extends Dao<Order> {
    @Override
    public int create(Order obj) {
        String query = "INSERT INTO order_ (id_user, id_client, order_date, total) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setInt(1, obj.getUser().getID());
            record.setInt(2, obj.getClient().getID());
            record.setString(3, obj.getDate());
            record.setFloat(4, obj.getTotal());

            if (record.executeUpdate() > 0)
                System.out.println("Insertion.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public Order read(int id) {
        Order order = null;
        String query = "SELECT * FROM order_ " +
                "LEFT JOIN user ON order_.id_user = user.id_user " +
                "LEFT JOIN client ON order_.id_client = client.id_client " +
                "WHERE user.id_user = ?";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    User user = new User(set.getString("login"), set.getString("password"));
                    Client client = new Client(
                            set.getInt("id_client"),
                            set.getString("first_name"),
                            set.getString("last_name"),
                            set.getString("email"),
                            set.getString("phone")
                    );

                    order = new Order(
                            user,
                            client,
                            set.getString("order_date"),
                            set.getFloat("total")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public Order[] readAll() {
        List<Order> order = new ArrayList<>();
        String query = "SELECT * FROM order_ " +
                "LEFT JOIN user ON order_.id_user = user.id_user " +
                "LEFT JOIN client ON order_.id_client = client.id_client";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                while (set.next()) {
                    User user = new User(set.getString("login"), set.getString("password"));
                    Client client = new Client(
                            set.getInt("id_client"),
                            set.getString("first_name"),
                            set.getString("last_name"),
                            set.getString("email"),
                            set.getString("phone")
                    );

                    order.add(new Order(
                            user,
                            client,
                            set.getString("order_date"),
                            set.getFloat("total")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return order.toArray(new Order[0]);
    }

    @Override
    public boolean update(Order obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
