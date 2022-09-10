import entity.Airline;
import entity.Airport;
import entity.Flight;
import org.junit.Assert;
import org.junit.Test;
import service.FlightService;
import utility.FlightsGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightServiceTest {
    FlightService flightService = new FlightService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\test-database\\test-flights.txt");
    List<Flight> flightList = FlightsGenerator.generator(20);
    Flight flight1 = new Flight(Airport.BAKU, Airport.BARCELONA, LocalDateTime.now(), Duration.ofDays(5), Airline.AMERICAN_AIRLINE, 657, 100);

    @Test
    public void saveAll() {
        flightService.saveAll(flightList);
        Assert.assertEquals(false, flightService.getAllFlights().isEmpty());
    }

    @Test
    public void savePositiveCase() {
        Assert.assertEquals(true, flightService.save(flight1));
    }

    @Test
    public void saveNegativeCase() {
        Assert.assertEquals(false, flightService.save(null));
    }

    @Test
    public void getAllFlights() {
        flightService.saveAll(flightList);
        Assert.assertEquals(flightList, flightService.getAllFlights());
    }

    @Test
    public void removePositiveCase() {
        flightService.save(flight1);
        Assert.assertEquals(true, flightService.remove(flight1.getId()));
    }

    @Test
    public void removeNegativeCase() {
        Assert.assertEquals(false, flightService.remove(flight1.getId()));
    }
}