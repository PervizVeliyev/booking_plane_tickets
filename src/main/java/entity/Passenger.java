package entity;

import dao.Identifiable;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

public class Passenger implements Serializable, Identifiable {
    private static int counter = 1;
    private final int id;
    private final String firstName;
    private final String lastName;

    public Passenger(String firstName, String lastName) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id && firstName.equals(passenger.firstName) && lastName.equals(passenger.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1}", firstName, lastName);
    }
}
