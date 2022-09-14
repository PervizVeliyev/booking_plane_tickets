package entity;

import dao.Identifiable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable, Identifiable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int counter = 1;
    private final int id;
    private final Flight flight;
    private List<Passenger> passengers;


    public Booking(Flight flight, List<Passenger> passengers) {
        this.id = counter++;
        this.flight = flight;
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && flight.equals(booking.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight);
    }

    @Override
    public String toString() {
        return "id:%d\nAirline Code:%s\nPassengers:%s".formatted(id, flight.getAirline().getDesignator().concat(String.valueOf(flight.getCode())), passengers);
    }
}
