package models;

import business.Business;
import daos.UserDao;

public class User {
    private int id_user;
    private String login;
    private String password;

    public User(int _id, String _login, String _password) {
        this.id_user = _id;
        this.setLogin(_login);
        this.setPassword(_password);

        //Business.getInstance().createUser(this);
    }

    public User(String _login, String _password) {
        this.setLogin(_login);
        this.setPassword(_password);
    }

    // region getters and setters
    public String getLogin() { return this.login; }
    public void setLogin(String _login) { this.login = _login; }

    public String getPassword() { return this.password; }
    public void setPassword(String _password) { this.password = _password; }
    //endregion
}
