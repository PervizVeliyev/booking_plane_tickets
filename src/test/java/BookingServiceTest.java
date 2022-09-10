import entity.*;
import org.junit.Assert;
import org.junit.Test;
import service.BookingService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class BookingServiceTest {
    BookingService bookingService = new BookingService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\test-database\\test-booking.txt");
    Flight flight1 = new Flight(Airport.BAKU, Airport.BARCELONA, LocalDateTime.now(), Duration.ofDays(5), Airline.AMERICAN_AIRLINE, 657, 100);
    Flight flight2 = new Flight(Airport.BAKU, Airport.BARCELONA, LocalDateTime.now(), Duration.ofDays(8), Airline.EMIRATES_AIRLINE, 123, 101);
    Passenger passenger1 = new Passenger("Emil", "Tehmezov");
    Booking booking1 = new Booking(flight1, List.of(passenger1));
    Booking booking2 = new Booking(flight2, List.of(passenger1));

    @Test
    public void get() {
        bookingService.makeBooking(booking1);
        bookingService.makeBooking(booking2);
        Assert.assertEquals(booking1, bookingService.get(booking1.getId()).get());
    }

    @Test
    public void makeBookingPositiveCase() {
        Booking booking2 = new Booking(flight2, List.of(passenger1));
        Assert.assertTrue(bookingService.makeBooking(booking2));
    }

    @Test
    public void makeBookingNegativeCase() {
        Assert.assertFalse(bookingService.makeBooking(null));
    }

    @Test
    public void removePositiveCase() {
        bookingService.makeBooking(booking1);
        bookingService.makeBooking(booking2);
        Assert.assertTrue(bookingService.remove(booking1.getId()));
    }

    @Test
    public void removeNegativeCase() {
        bookingService.makeBooking(booking2);
        Assert.assertFalse(bookingService.remove(booking1.getId()));
    }
}