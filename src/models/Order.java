package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id_user;
    private int id_client;
    private String date;
    private float total;
    private User user;
    private Client client;

    public Order(User _user, Client _client) {
        this.user = _user;
        this.client = _client;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.setDate(LocalDateTime.now().format(format));
    }

    public Order(User _user, Client _client, String _date, float _total) {
        this.user = _user;
        this.client = _client;

        this.setDate(_date);
        this.setTotal(_total);
    }

    public String getDate() { return this.date; }
    public void setDate(String _date) { this.date = _date; }

    public float getTotal() { return this.total; }
    public void setTotal(float _total) { this.total = _total; }

    public Client getClient() { return this.client; }
    public void setClient(Client _client) { this.client = _client; }

    public User getUser() { return this.user; }
    public void setUser(User _user) { this.user = _user; }
}
