package entity;

import dao.Identifiable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable, Identifiable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String login;
    private final String password;
    private List<Booking> bookings;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login.toLowerCase();
        this.password = password;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    public List<Booking> getBookings() {
        if(bookings == null) return new ArrayList<>();
        return bookings;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
