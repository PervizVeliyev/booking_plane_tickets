package entity;

import dao.Identifiable;
import utility.Adjuster;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Flight implements Serializable,Identifiable {
    private static int counter = 1;
    private final int id;
    private final Airport airportFrom;
    private final Airport airportTo;
    private final LocalDateTime time;
    private final Duration duration;
    private final Airline airline;
    private final int code;
    private int capacity;


    public Flight(Airport airportFrom, Airport airportTo, LocalDateTime time, Duration duration, Airline airline, int code, int capacity) {
        this.code = code;
        this.id = counter++;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.time = time;
        this.duration = duration;
        this.airline = airline;
        this.capacity = capacity;
    }

    public Airport getAirportFrom() {
        return airportFrom;
    }

    public Airport getAirportTo() {
        return airportTo;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Duration getDuration() {
        return duration;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCode() {
        return code;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && airline == flight.airline && code == flight.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airline, code);
    }

    @Override
    public String toString() {
        return MessageFormat.format("   {4}{5}     |  {0}--->   {1} | {2} |  {3}|    {6}", Adjuster.airportAdjuster(airportFrom), Adjuster.airportAdjuster(airportTo), time, Adjuster.durationAdjuster(duration), airline.getDesignator(), code, capacity);
    }
}
