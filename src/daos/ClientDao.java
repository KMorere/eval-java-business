package daos;

import models.Client;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class ClientDao extends Dao<Client> {
    @Override
    public int create(Client obj) {
        int id_client = 0;
        String query = "INSERT INTO client (id_client, first_name, last_name, email, phone) " +
                "VALUES (NULL, ?, ?, ?, ?)";
        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getFirstName());
            record.setString(2, obj.getLastName());
            record.setString(3, obj.getMail());
            record.setString(4, obj.getPhone());

            id_client = record.executeUpdate();

            if (id_client > 0)
                System.out.println("Insertion at id : " + id_client);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id_client;
    }

    @Override
    public Client read(int id) {
        Client client = null;
        String query = "SELECT * FROM client " +
                "WHERE client.id_client = ?";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    client = new Client(
                            set.getInt("id_course"),
                            set.getString("first_name"),
                            set.getString("last_name"),
                            set.getString("email"),
                            set.getString("phone")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public Client[] readAll() {
        ArrayList<Client> client = new ArrayList<>();
        String query = "SELECT * FROM client " +
                "WHERE client.id_client = ?";

        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    client.add(new Client(
                            set.getInt("id_course"),
                            set.getString("first_name"),
                            set.getString("last_name"),
                            set.getString("email"),
                            set.getString("phone")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return client.toArray(new Client[0]);
    }

    @Override
    public boolean update(Client obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
